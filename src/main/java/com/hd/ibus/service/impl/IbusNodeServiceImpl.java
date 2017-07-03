package com.hd.ibus.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hd.ibus.mapper.IbusNodeCustomMapper;
import com.hd.ibus.mapper.IbusNodeMapper;
import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.IbusNode;
import com.hd.ibus.pojo.vo.IbusNodeVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.IbusNodeService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.Helper;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.dataUtil.GatewayUDPListenerThread;
import com.hd.ibus.util.dataUtil.Mosaic;
import com.hd.ibus.util.dataUtil.UDPInitServlet;

@Service("ibusNodeService")
public class IbusNodeServiceImpl implements IbusNodeService {
	
	@Resource
	private IbusNodeMapper ibusNodeDao;
	
	@Resource
	private IbusNodeCustomMapper ibusNodeCustom;

	public String saveIbusNode(IbusNodeVo ibusNodeVo,IbusGatewayService ibusGatewayService) {
		IbusNode ibusNode = ibusNodeVo.getIbusNode();
		if(ibusNode.getNodeType()==71){
			ibusNode.setNodeType2(1);
		}else if(ibusNode.getNodeType()==81){
			ibusNode.setNodeType2(0);
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("gatewayId", ibusNode.getGatewayId());
		map.put("nodeAddr", ibusNode.getNodeAddress());
		IbusNode ibusNode2 = this.ibusNodeCustom.findOneByNodeAddr(map);
		if(ibusNode2!=null){
			return "address";
		}
		boolean result2 = 1==this.ibusNodeDao.insertSelective(ibusNode);
		if(result2){
			Helper.map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_tpMax",ibusNode.getTpMax());
			Helper.map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_vMix",ibusNode.getvMix());
			Helper.map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_vMax",ibusNode.getvMax());
			Helper.map.put(ibusNode.getGatewayId()+"_"+ibusNode.getNodeAddress()+"_iMax",ibusNode.getiMax());
		}
		return result2+"";
	}
	
	public String nodeControl(Integer gatewayId,Integer nodeAddr,IbusGatewayService ibusGatewayService){
		GatewayUDPListenerThread gt = null;
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			IbusGateway ibusGateway = ibusGatewayService.findById(gatewayId);
			map.put("gatewayId", gatewayId);
			map.put("nodeAddr", nodeAddr);
			IbusNode ibusNode = this.ibusNodeCustom.findOneByNodeAddr(map);
			int port_state_count = 0;
			while(!Mosaic.port_state){
				port_state_count++;
				Thread.sleep(200);
			
				//线程10秒未停止算超时，重新启动线程，返回outTime
				if (port_state_count == 50) {
					//端口被占用outTime1
					return "outTime1";
				}
			}
			
			Mosaic.port_state=false;
			String nodeADDR = nodeAddr+"";
			String port = ibusGateway.getGatewayPort()+"";
			String oneMore = "one";
			String param =  ibusNode.getTpMax()+","+ibusNode.getiMax()+","+ibusNode.getvMix()+","+ibusNode.getvMax();
			String type = "03";
			String nodeType = ibusNode.getNodeType()+"";
			System.out.println("、、、、、、、、、、、、、、、、、、port:"+port+",param"+param+","+",type:"+type+",nodeAddr:"+nodeADDR+"，nodeType:"+nodeType);
			gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap
					.get("gateway" + port);
			//判断该端口的监听线程是否存在
			if (gt != null) {
				//线程运行状态量设置为false,释放改socket占用的端口
				gt.working = false;
				int i = 0;
				//通过线程状态量判断线程释放停止
				while (gt.state) {
					i++;
					Thread.sleep(200);
					System.out.println("gt.state+" + gt.state);
					//线程10秒未停止算超时，重新启动线程，返回outTime
					if (i == 30) {
						//释放端口
						Mosaic.port_state=true;
						gt.working = true;
						//UDPInitServlet.threadPool.execute(gt);
						
						return "outTime";
					}
				}
				if(nodeADDR.length()==1){
					nodeADDR = "0"+nodeADDR;
				}
				String result = Mosaic.mosaicSendWords(nodeADDR + nodeType, type,
						oneMore, Integer.parseInt(port), param,null);
				if("".equals(result)||result==null||"error".equals(result)){
					Mosaic.port_state=true;
					gt.working = true;
					Thread.sleep(100);
					UDPInitServlet.threadPool.execute(gt);
					return "error1";
				}
				
				Mosaic.port_state=true;
				gt.working = true;
				Thread.sleep(100);
				UDPInitServlet.threadPool.execute(gt);
				
				return result;
			}else{
				Mosaic.port_state=true;
				// 返回操作结果
				return "Thread null";
			}
			
		} catch (Exception e) {
			Mosaic.port_state=true;
			gt.working = true;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			if(gt!=null){
				UDPInitServlet.threadPool.execute(gt);
			}
			e.printStackTrace();
			return "error";
		}
	}
	
	public String nodalPoint(Integer gatewayId,IbusGatewayService ibusGatewayService){
		
			List<Map> list = this.ibusNodeCustom.findNodeBygatewayId(gatewayId);
			String nodeAddr = "";
			for(int i=0;i<list.size();i++){
				Map ibusNode = list.get(i);
				if((Integer)ibusNode.get("node_address")<10){
					nodeAddr+="0"+ibusNode.get("node_address")+",";
				}else{
					nodeAddr+=ibusNode.get("node_address")+",";
				}
						
			}
			nodeAddr=nodeAddr.substring(0,nodeAddr.length()-1);
			String type = "80";
			String param = "";
			String oneMore = "more";
			
			IbusGateway ibusGateway = ibusGatewayService.findById(gatewayId);
			String port = ibusGateway.getGatewayPort()+"";
			String nodeCount = ibusGateway.getNodeCount()+"";
			

			GatewayUDPListenerThread gt = null;
			try {
				int port_state_count = 0;
				while(!Mosaic.port_state){
					port_state_count++;
					Thread.sleep(200);
				
					//线程10秒未停止算超时，重新启动线程，返回outTime
					if (port_state_count == 50) {
						return "outTime3";
					}
				}
				
				Mosaic.port_state=false;
			
				//var data ={port:port,oneMore:oneMore,param:param,type:type,nodeAddr:nodeAddr};
				String str = "port:"+port+",oneMore:"+oneMore+",param:"+param+",type:"+type+",nodeAddr:"+nodeAddr;
				System.out.println(str);
				gt = (GatewayUDPListenerThread) UDPInitServlet.threadMap
						.get("gateway" + port);
				//判断该端口的监听线程是否存在
				if (gt != null) {
					//线程运行状态量设置为false,释放改socket占用的端口
					gt.working = false;
					int i = 0;
					//通过线程状态量判断线程释放停止
					while (gt.state) {
						i++;
						Thread.sleep(200);
						System.out.println("gt.state+" + gt.state);
						gt.working = false;
						//线程10秒未停止算超时，重新启动线程，返回outTime
						if (i == 30) {
							Mosaic.port_state=true;
							gt.working = true;
							//UDPInitServlet.threadPool.execute(gt);
							return "outTime";
						}
					}
					if(nodeCount.length()<2){
						nodeCount = "0"+nodeCount;
					}
					System.out.println(nodeCount);
					System.out.println("****************************type:"+nodeCount+"04"+",oneMore:"+oneMore+",port:"+port+",nodeAddr:"+nodeAddr);
					//String adada2=Mosaic.mosaicSendWords("0004", "80", "more", 9001 ,"03,01");
					String result = Mosaic.mosaicSendWords(nodeCount+"04"  , type,oneMore, Integer.parseInt(port), nodeAddr,param);
					if("".equals(result)||result==null||"error".equals(result)||"error2".equals(result)){
						
						Mosaic.port_state=true;
						gt.working = true;
						UDPInitServlet.threadPool.execute(gt);
						return "error1";
					}
					Mosaic.port_state=true;
					gt.working = true;
					Thread.sleep(100);
					UDPInitServlet.threadPool.execute(gt);
					return result;
				}else{
					Mosaic.port_state=true;
					return "Threadnull";
				}
			} catch (Exception e) {
				Mosaic.port_state=true;
				gt.working = true;
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				if(gt!=null){
					UDPInitServlet.threadPool.execute(gt);
				}
				e.printStackTrace();
				return "error";
			}
		
		
		
	}

	public String updateIbusNode(IbusNodeVo ibusNodeVo) {
		IbusNode ibusNode = ibusNodeVo.getIbusNode();
		if(ibusNode.getNodeType()!=null){
			if(ibusNode.getNodeType()==71){
				ibusNode.setNodeType2(1);
			}else if(ibusNode.getNodeType()==81){
				ibusNode.setNodeType2(0);
			}
		}
		
		
		//boolean result = 1==this.ibusNodeDao.updateByPrimaryKeySelective(ibusNodeVo.getIbusNode());
		boolean result = 1==this.ibusNodeDao.updateByPrimaryKeySelective(ibusNode);
		return result+"";
	}

	public DataGridResultInfo findList(IbusNodeVo ibusNodeVo,Integer pageNow,Integer pageSize) {
		pageNow = pageNow == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_NOW) : pageNow;
		pageSize = pageSize == null ? PropertiesUtils.getIntValue(Config.CONFIG, Config.PAGE_SIZE) : pageSize;
		ibusNodeVo = ibusNodeVo == null ? new IbusNodeVo() : ibusNodeVo;

		PageBean pageBean = new PageBean(pageNow, pageSize);
		ibusNodeVo.setPageBean(pageBean);

		//List<IbusNode> groups = this.ibusNodeCustom.findList(ibusNodeVo);
		List<Map> groups = this.ibusNodeCustom.findList2(ibusNodeVo);
		
		
		Integer total = this.ibusNodeCustom.findTotal(ibusNodeVo);
			
		return new DataGridResultInfo(total, groups);
	}

	public IbusNode findById(Integer id) {
		return this.ibusNodeDao.selectByPrimaryKey(id); 
	}

	public String deleteIbusNode(Integer id) {
		boolean result = 1==this.ibusNodeDao.deleteByPrimaryKey(id);
		return result+"";
	}

	public List getNodeByGateway(int gatewayId) {
		
		return ibusNodeCustom.getNodeByGateway(gatewayId);
	}

	public List<IbusNode> findAll() {
		return this.ibusNodeCustom.findList(null);
	}

	public List<IbusNode> findListByGroupId(Integer groupId) {
		return this.ibusNodeCustom.findListByGroupId(groupId); 
	}

	public String updateByAddress(Integer nodeState, String nodes,Integer gatewayId) {
		String[] snode = nodes.split(",");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nodes", snode);
		map.put("nodeState", nodeState);
		map.put("gatewayId", gatewayId);
		boolean result = 1==this.ibusNodeCustom.updateByAddress(map)?true:false;
		return result+"";
	}
	
	public String updateIsOnlineByAddress(Integer nodeIsonline, String nodes,Integer gatewayId) {
		String[] snode = nodes.split(",");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("nodes", snode);
		map.put("nodeIsonline", nodeIsonline);
		map.put("gatewayId", gatewayId);
		boolean result = 1==this.ibusNodeCustom.updateIsOnlineByAddress(map)?true:false;
		return result + "";
	}

	public List<Map> findNodeByGatewayId(Integer gatewayId) {
		return this.ibusNodeCustom.findNodeBygatewayId(gatewayId);
	}

	public int insertFromExcel(Map map) {
		return this.ibusNodeCustom.insertFromExcel(map);
	}
	public int insertFromUrl(Map map) {
		return this.ibusNodeCustom.insertFromUrl(map);
	}
}

