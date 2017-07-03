package com.hd.ibus.util.aop.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hd.ibus.pojo.IbusOperation;
import com.hd.ibus.pojo.IbusUser;
import com.hd.ibus.pojo.vo.IbusGatewayVo;
import com.hd.ibus.pojo.vo.IbusGroupVo;
import com.hd.ibus.pojo.vo.IbusNodeVo;
import com.hd.ibus.pojo.vo.IbusProjectVo;
import com.hd.ibus.pojo.vo.IbusUserVo;
import com.hd.ibus.service.IbusOperationService;



public class LogAop{
	
	@Resource
	private IbusOperationService ibusOperationService;

	public void beforeSleep(){
		System.out.println("开始前");
	}
	
	
	public void afterSleep(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
        HttpSession session = request.getSession(); 
		
        for (int i = 0; i < joinPoint.getArgs().length; i++) {  
            System.out.println("joinPoint:"+joinPoint.getArgs()[i]);  
        }
		// 获取方法名
		String methodName = joinPoint.getSignature().getName();
		String logContent = methodName + "-->"+ joinPoint.getTarget().getClass().getSimpleName();
		System.out.println("=========================================="+methodName);
		IbusOperation ope = new IbusOperation();
		if(methodName.equals("checkAccount")){
			ope.setOperationType("用户登录");
			logContent = "用户登录："+joinPoint.getArgs()[0];
			ope.setOperationName(logContent); 
		}else if(methodName.equals("saveIbusUser")){ 
			ope.setOperationType("添加用户");
			IbusUserVo ibusUserVo = (IbusUserVo) joinPoint.getArgs()[0];
			logContent = "添加用户，用户名:"+ibusUserVo.getIbusUser().getUserName();
			ope.setOperationName(logContent); 
		}else if(methodName.equals("updateIbusUser")){
			ope.setOperationType("修改用户信息");
			IbusUserVo ibusUserVo = (IbusUserVo) joinPoint.getArgs()[0];
			logContent = "修改用户信息，用户名:"+ibusUserVo.getIbusUser().getUserName();
			ope.setOperationName(logContent); 
		}else if(methodName.equals("deleteIbusUser")){
			ope.setOperationType("删除用户");
			logContent = "删除用户，用户ID:"+joinPoint.getArgs()[0];
			ope.setOperationName(logContent); 
 		}else if(methodName.equals("saveIbusGateway")){
 			ope.setOperationType("添加网关");
 			IbusGatewayVo ibusGatewayVo = (IbusGatewayVo) joinPoint.getArgs()[0];
 			logContent = "添加网关，网关名称:"+ibusGatewayVo.getIbusGateway().getGatewayName();
 			ope.setOperationName(logContent); 
 		}else if(methodName.equals("updateIbusGateway")){
 			ope.setOperationType("修改网关");
 			IbusGatewayVo ibusGatewayVo = (IbusGatewayVo) joinPoint.getArgs()[0];
 			logContent = "修改网关，网关ID:"+ibusGatewayVo.getIbusGateway().getId();
 			ope.setOperationName(logContent); 
 		}else if(methodName.equals("deleteIbusGateway")){
 			ope.setOperationType("删除网关");
			logContent = "删除网关，网关ID:"+joinPoint.getArgs()[0];
			ope.setOperationName(logContent); 
 		}else if(methodName.equals("saveIbusGroup")){
 			ope.setOperationType("添加群组");
 			IbusGroupVo ibusGroupVo = (IbusGroupVo) joinPoint.getArgs()[0];
 			logContent = "添加群组，群组名称:"+ibusGroupVo.getIbusGroup().getGroupName();
 			ope.setOperationName(logContent); 
 		}else if(methodName.equals("updateIbusGroup")){
 			ope.setOperationType("修改群组");
 			IbusGroupVo ibusGroupVo = (IbusGroupVo) joinPoint.getArgs()[0];
 			logContent = "修改群组，群组ID:"+ibusGroupVo.getIbusGroup().getId();
 			ope.setOperationName(logContent);
 		}else if(methodName.equals("deleteIbusGroup")){
 			ope.setOperationType("删除群组");
			logContent = "删除群组，群组ID:"+joinPoint.getArgs()[0];
			ope.setOperationName(logContent); 
 		}else if(methodName.equals("saveIbusNode")){
 			ope.setOperationType("添加节点");
 			IbusNodeVo ibusNodeVo = (IbusNodeVo) joinPoint.getArgs()[0];
 			logContent = "添加节点，节点名称:"+ibusNodeVo.getIbusNode().getNodeName();
 			ope.setOperationName(logContent);
 		}else if(methodName.equals("updateIbusNode")){
 			ope.setOperationType("修改节点");
 			IbusNodeVo ibusNodeVo = (IbusNodeVo) joinPoint.getArgs()[0];
 			logContent = "修改节点，节点ID:"+ibusNodeVo.getIbusNode().getId();
 			ope.setOperationName(logContent);
 		}else if(methodName.equals("deleteIbusNode")){
 			ope.setOperationType("删除节点");
			logContent = "删除节点，节点ID:"+joinPoint.getArgs()[0];
			ope.setOperationName(logContent); 
 		}else if(methodName.equals("updateByAddress")){
 			ope.setOperationType("开关节点");
 			logContent = "开关节点，节点状态:"+((Integer)joinPoint.getArgs()[0]==1?"开":"关")+",节点地址："+joinPoint.getArgs()[1];
 			ope.setOperationName(logContent);
 		}else if(methodName.equals("updateByAddress")){
 			ope.setOperationType("改变节点在线状态");
 			logContent = "节点上下线，节点状态："+((Integer)joinPoint.getArgs()[0]==1?"在线":"下线")+",节点地址："+joinPoint.getArgs()[1];
 			ope.setOperationName(logContent); 
 		}else if(methodName.equals("insertFromExcel")){
 			ope.setOperationType("批量导入");
 			logContent = "从Excel中批量导入";
 			ope.setOperationName(logContent); 
 		}else if(methodName.equals("insertFromUrl")){
 			ope.setOperationType("批量导入");
 			logContent = "从url中批量导入";
 			ope.setOperationName(logContent); 
 		}else if(methodName.equals("saveIbusProject")){
 			ope.setOperationType("添加项目");
 			IbusProjectVo ibusProjectVo = (IbusProjectVo) joinPoint.getArgs()[0];
 			logContent = "添加项目，项目ID:"+ibusProjectVo.getIbusProject().getProjectName();
 			ope.setOperationName(logContent);
 		}else if(methodName.equals("updateIbusProject")){
 			ope.setOperationType("修改项目");
 			IbusProjectVo ibusProjectVo = (IbusProjectVo) joinPoint.getArgs()[0];
 			logContent = "修改项目，项目ID:"+ibusProjectVo.getIbusProject().getId();
 			ope.setOperationName(logContent);
 		}else if(methodName.equals("deleteIbusProject")){
 			ope.setOperationType("删除项目");
			logContent = "删除项目，项目ID:"+joinPoint.getArgs()[0];
			ope.setOperationName(logContent); 
 		}else if(methodName.equals("saveTpvi")){
 			return;
 		}else if(methodName.equals("saveTpvi2")){
 			return;
 		}else if(methodName.equals("saveTpvi22")){
 			return;
 		}else if(methodName.equals("deleteExistByTableName")){
 			return;
 		}else if(methodName.equals("updateState")){
 			ope.setOperationType("删除报警记录");
			logContent = "删除报警记录，报警记录ID:"+joinPoint.getArgs()[0];
			ope.setOperationName(logContent); 
 		}
		
		
		
		Date date = new Date();
		String logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		String result = "";
		ope.setAcTime(date);
		IbusUser user = (IbusUser) session.getAttribute("user");
		if(user==null){
			return;
		}
		ope.setUserId(user.getId()); 
		if(ope.getOperationType()==null){
			
		}else{
			this.ibusOperationService.insertOperation(ope);
		}
		System.out.println("结束后");
		System.out.println("result:"+result);
	}

}
