package com.hd.ibus.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import javax.annotation.Resource;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.context.WebApplicationContext;

import com.hd.ibus.mapper.IbusGatewayCustomMapper;
import com.hd.ibus.mapper.IbusGatewayMapper;
import com.hd.ibus.pojo.IbusExist;
import com.hd.ibus.pojo.IbusGateway;
import com.hd.ibus.pojo.vo.IbusGatewayVo;
import com.hd.ibus.result.DataGridResultInfo;
import com.hd.ibus.service.IbusExistService;
import com.hd.ibus.service.IbusGatewayService;
import com.hd.ibus.service.IbusTpviService;
import com.hd.ibus.service.IbusWarnService;
import com.hd.ibus.util.Config;
import com.hd.ibus.util.DateUtils;
import com.hd.ibus.util.Helper;
import com.hd.ibus.util.PageBean;
import com.hd.ibus.util.PropertiesUtils;
import com.hd.ibus.util.activeMQ.AutoUDPPushServiceImpl;
import com.hd.ibus.util.activeMQ.PushService;
import com.hd.ibus.util.dataUtil.GatewayMQListenerThread;
import com.hd.ibus.util.dataUtil.GatewayUDPListenerThread;
import com.hd.ibus.util.dataUtil.UDPInitServlet;

@Service("ibusGatewayService")
public class IbusGatewayServiceImpl implements IbusGatewayService {

	@Resource
	private IbusGatewayMapper ibusGatewayDao;

	@Resource
	private IbusGatewayCustomMapper ibusGatewayCustom;

	public String saveIbusGateway2() {
		boolean result = false;
		IbusGateway ibusGateway = new IbusGateway();
		ibusGateway.setGatewayName("333");
		ibusGateway.setGatewayIp("192.168.0.142");
		ibusGateway.setGatewayPort(9028);
		ibusGateway.setIsOnline(1);
		ibusGateway.setProjectId(1);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tableName", "se1");
		map.put("auto_increment", 1000000);
		result = 1 == this.ibusGatewayDao.insertSelective(ibusGateway) ? true
				: false;
		boolean result2 = 0 == this.ibusGatewayCustom.createTable(map) ? true
				: false;
		if (result) {
			System.out.println("插入成功");
			TransactionAspectSupport.currentTransactionStatus()
					.setRollbackOnly();
			System.out.println("回滚成功");
			return "false";
		}
		return result + "";
	}

	public String saveIbusGateway(IbusGatewayVo ibusGatewayVo,
			IbusExistService ibusExistService, IbusTpviService ibusTpviService,
			IbusWarnService ibusWarnService) {
		boolean result = false;
		String tableName = "";
		IbusGateway ibusGateway = this.ibusGatewayCustom
				.findOneByGatewayPort(ibusGatewayVo.getIbusGateway()
						.getGatewayPort());
		if (ibusGateway != null) {
			System.out.println("端口已存在");
			return "port";
		}
		try {
			// 插入网关
			result = 1 == this.ibusGatewayDao.insertSelective(ibusGatewayVo
					.getIbusGateway()) ? true : false;
			System.out.println("---" + result);
			//throw new Exception("33");
		} catch (Exception e) {
			ibusGateway = this.ibusGatewayCustom
					.findOneByGatewayPort(ibusGatewayVo.getIbusGateway()
							.getGatewayPort());
			e.printStackTrace();
			System.out.println("id已重复");
			if (ibusGateway != null) {
				System.out.println(ibusGateway.getGatewayPort()+"");
				this.ibusGatewayDao.deleteByPrimaryKey(ibusGateway.getId());
			}
			return "false";
		}
//		 PushService pushService = (AutoUDPPushServiceImpl) ac
//		 .getBean("pushService");
		/* IbusExistServiceImpl ibusExistService = (IbusExistServiceImpl) ac
		 .getBean("ibusExistService");*/
		 
		 Map<String, Object> map = new HashMap<String, Object>();
			
		try {
			if (result) {
				System.out.println("++++"
						+ ibusGatewayVo.getIbusGateway().getGatewayPort());
				// 获取刚才插入的值
				IbusGateway obj = this.findOneByGatewayPort(ibusGatewayVo
						.getIbusGateway().getGatewayPort());
				System.out.println("查询到网关");
				ExecutorService threadPool = UDPInitServlet.threadPool;
				ExecutorService receiverThreadPool = UDPInitServlet.receiverThreadPool;
				Map<String, Object> threadMap = UDPInitServlet.threadMap;


				Date date = new Date();
				// 创建表
				tableName = "ibus_tpvi_" + obj.getGatewayPort() + "_"
						+ DateUtils.formatDateToString(date, "yyyyMMdd");
				map.put("tableName", tableName);
				map.put("auto_increment",
						DateUtils.formatDateToString(date, "yyyyMMdd")
								+ "0000000");
				boolean createTable = false;
				
				try{
					this.ibusGatewayCustom.deleteTable(map);
				}catch(Exception e){
					System.out.println("该表不存在");
				}
				try {
					createTable = this.createTable(map);
					if(!createTable){
						throw new Exception();
					}
				} catch (Exception e) {
					System.out.println("table");
					ibusGateway = this.ibusGatewayCustom
							.findOneByGatewayPort(ibusGatewayVo
									.getIbusGateway().getGatewayPort());
					if (ibusGateway != null) {
						System.out.println(ibusGateway.getGatewayPort()+"");
						this.ibusGatewayDao.deleteByPrimaryKey(ibusGateway
								.getId());
					}
					return "createTable";
				}

				IbusExist ibusExist = new IbusExist();
				ibusExist.setGatewayId(obj.getId());
				//tableName="ibus_tpvi_9001_20161104";
				ibusExist.setTableName(tableName);
				try {
					IbusExist ibusExist2 = ibusExistService.findByTableName(tableName);
					if(ibusExist2!=null){
						System.out.println("exist2");
						ibusGateway = this.ibusGatewayCustom
								.findOneByGatewayPort(ibusGatewayVo
										.getIbusGateway().getGatewayPort());
						if (ibusGateway != null) {
							this.ibusGatewayDao.deleteByPrimaryKey(ibusGateway
									.getId());
						}
						return "exist2false";
					}
					String resultIsOnline = ibusExistService.saveExist(ibusExist);
					if (resultIsOnline.equals("true")) {
						Map<String, Object> map2 = Helper.map;
						map.put(tableName, 1);
					}
					
					/*if(true){
						throw new Exception();
					}*/
				} catch (Exception e) {
					System.out.println("到exist表了");
					ibusGateway = this.ibusGatewayCustom
							.findOneByGatewayPort(ibusGatewayVo
									.getIbusGateway().getGatewayPort());
					if (ibusGateway != null) {
						this.ibusGatewayDao.deleteByPrimaryKey(ibusGateway
								.getId());
					}
					IbusExist ibusExist2 = ibusExistService
							.findByTableName(tableName);
					if (ibusExist2 != null) {
						ibusExistService.deleteExist(ibusExist2.getId());
					}
					this.ibusGatewayCustom.deleteTable(map);
					return "false";
				}
				// ConnectionFactory ：连接工厂，JMS 用它创建连接
				/*ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
						ActiveMQConnection.DEFAULT_USER,
						ActiveMQConnection.DEFAULT_PASSWORD,
						"tcp://localhost:61616");
				// Connection ：JMS 客户端到JMS Provider 的连接
				Connection connection = null;
				// Session： 一个发送或接收消息的线程
				Session session;

				connection = connectionFactory.createConnection();
				connection.start();
				// 获取操作连接
				session = connection.createSession(Boolean.FALSE,
						Session.AUTO_ACKNOWLEDGE);*/
				int port = obj.getGatewayPort();
				System.out.println(obj.getGatewayPort());
				//Destination destination;
				// destination获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
				// 此处接收和发送都用这个
				//destination = session.createQueue("gateway" + port);
				// 消费者，消息接收者
				//MessageConsumer consumer = session.createConsumer(destination);
				// 像activemq发送消息
				GatewayUDPListenerThread thread = new GatewayUDPListenerThread(
						obj.getGatewayPort(), obj.getGatewayIp(), obj.getId(), ibusTpviService, ibusWarnService);
				// 从activemq接收消息
				/*GatewayMQListenerThread receiver = new GatewayMQListenerThread(
						consumer, ibusTpviService, ibusWarnService,
						obj.getId(), obj.getGatewayPort());*/

				threadMap.put("gateway" + port, thread);

				threadPool.execute(thread);

				/*receiverThreadPool.execute(receiver);*/

				GatewayUDPListenerThread thread2 = (GatewayUDPListenerThread) threadMap
						.get("gateway" + port);
				//thread2.state=false;
				if (!thread2.state) {
					System.out.println("thread2");
					ibusGateway = this.ibusGatewayCustom
							.findOneByGatewayPort(ibusGatewayVo
									.getIbusGateway().getGatewayPort());
					if (ibusGateway != null) {
						this.ibusGatewayDao.deleteByPrimaryKey(ibusGateway
								.getId());
					}
					IbusExist ibusExist2 = ibusExistService
							.findByTableName(tableName);
					if (ibusExist2 != null) {
						ibusExistService.deleteExist(ibusExist2.getId());
					}
					return "false";
				}
				/*if (true) {
					throw new Exception(tableName);
				}*/

			} else {
				System.out.println("else");
				TransactionAspectSupport.currentTransactionStatus()
						.setRollbackOnly();
				return "false";
			}
		} catch (Exception e) {
			System.out.println("JMS");
			ibusGateway = this.ibusGatewayCustom
					.findOneByGatewayPort(ibusGatewayVo.getIbusGateway()
							.getGatewayPort());
			if (ibusGateway != null) {
				this.ibusGatewayDao.deleteByPrimaryKey(ibusGateway.getId());
			}
			IbusExist ibusExist2 = ibusExistService.findByTableName(tableName);
			if (ibusExist2 != null) {
				ibusExistService.deleteExist(ibusExist2.getId());
			}
			this.ibusGatewayCustom.deleteTable(map);
			return "false";
		}
		return result + "";
	}

	public String updateIbusGateway(IbusGatewayVo ibusGatewayVo) {
		boolean result = 1 == this.ibusGatewayDao.updateByPrimaryKeySelective(ibusGatewayVo.getIbusGateway());
		System.out.println("updateIbusGateway:result:"+result);
		return result + "";
	}

	public DataGridResultInfo findList(IbusGatewayVo ibusGatewayVo,
			Integer pageNow, Integer pageSize) {
		pageNow = pageNow == null ? PropertiesUtils.getIntValue(Config.CONFIG,
				Config.PAGE_NOW) : pageNow;
		pageSize = pageSize == null ? PropertiesUtils.getIntValue(
				Config.CONFIG, Config.PAGE_SIZE) : pageSize;
		ibusGatewayVo = ibusGatewayVo == null ? new IbusGatewayVo()
				: ibusGatewayVo;

		PageBean pageBean = new PageBean(pageNow, pageSize);
		ibusGatewayVo.setPageBean(pageBean);

		//List<IbusGateway> groups = this.ibusGatewayCustom.findList(ibusGatewayVo);
		List<Map> groups = this.ibusGatewayCustom.findList2(ibusGatewayVo);
		Integer total = this.ibusGatewayCustom.findTotal(ibusGatewayVo);

		return new DataGridResultInfo(total, groups);
	}

	public IbusGateway findById(Integer id) {
		return this.ibusGatewayDao.selectByPrimaryKey(id);
	}

	public String deleteIbusGateway(Integer id,IbusExistService ibusExistService) {
		IbusGateway ibusGateway = this.ibusGatewayDao.selectByPrimaryKey(id);
		Date date = new Date();
		Map<String, Object> threadMap = UDPInitServlet.threadMap;
		GatewayUDPListenerThread thread2 = (GatewayUDPListenerThread) threadMap
				.get("gateway" + ibusGateway.getGatewayPort());
		if(thread2.state){
			thread2.working=false;
			threadMap.remove("gateway"+ibusGateway.getGatewayPort());
		}
		String tableName = "%"+ibusGateway.getGatewayPort()+"%";
		int s = ibusExistService.deleteExistByTableName(tableName);
		boolean deleteResult = s==-1?false:true;
		System.out.println(s+",deleteResult:"+deleteResult);
		if(deleteResult){
			boolean result = 1 == this.ibusGatewayDao.deleteByPrimaryKey(id);
			return result + "";
		}else{
			return "false";
		}
	}

	public List<IbusGateway> findAll() {
		return this.ibusGatewayCustom.findList(null);
	}

	public IbusGateway findOneByGatewayPort(Integer port) {
		return this.ibusGatewayCustom.findOneByGatewayPort(port);
	}

	public boolean createTable(Map map) {
		int result = this.ibusGatewayCustom.createTable(map);
		System.out.println("result:" + result);
		return result == 0 ? true : false;
	}

}
