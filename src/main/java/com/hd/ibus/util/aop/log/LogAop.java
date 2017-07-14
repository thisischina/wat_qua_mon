package com.hd.ibus.util.aop.log;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.hd.ibus.pojo.Station;
import com.hd.ibus.pojo.User;
import com.hd.ibus.pojo.vo.IbusUserVo;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hd.ibus.pojo.Operation;
import com.hd.ibus.service.OperationService;



public class LogAop{
	
	@Resource
	private OperationService operationService;

	public void afterReturning(JoinPoint joinPoint) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		//type
		String methodName = joinPoint.getSignature().getName();
		Operation ope = new Operation();
		ope.setOperationType(methodName);
		//user_id
		User user = (User) session.getAttribute("user");
		if(user==null){
			ope.setUserId(null);
		} else {
			ope.setUserId(user.getId());
		}
		//content
		String logContent = "";
		if(methodName.equals("login")) {
			if(user != null) {
				logContent = "用户名：" + user.getName();
			}
		}else if(methodName.startsWith("delete")) {
			logContent = "ID:"+joinPoint.getArgs()[0];
		}else {
			logContent = joinPoint.getArgs()[0].toString();
		}
		ope.setOperationName(logContent);
		//operation_time
		Date date = new Date();
		String logTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		String result = "";
		ope.setAcTime(date);

		if (ope.getOperationType()!= null) {
			this.operationService.insertOperation(ope);
		}
	}

}
