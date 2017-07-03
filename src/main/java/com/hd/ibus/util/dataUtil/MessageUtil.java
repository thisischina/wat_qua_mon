package com.hd.ibus.util.dataUtil;

import com.hd.ibus.util.Config;
import com.hd.ibus.util.PropertiesUtils;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
/**
 * 短信发送接口
 * @author zhangjunxian
 *
 */
public class MessageUtil {
	static String url = "http://gw.api.taobao.com/router/rest";
	static String appkey = "23551205";
	static String secret = "f3b45e08ef04ab18afdf72f5ff3ecab9";
	public static String sendMessage(String content) throws Exception{
		
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend( "" );
		req.setSmsType( "normal" );
		req.setSmsFreeSignName( "监控中心" );
		req.setSmsParamString(content);
		req.setRecNum( PropertiesUtils.getValue(Config.CONFIG, "tel") );
		req.setSmsTemplateCode( "SMS_30850019" );
		AlibabaAliqinFcSmsNumSendResponse rsp = client.execute(req);
		System.out.println(rsp.getBody());
		return rsp.getBody();

	}
	
	public static void main(String[] args) throws Exception {
		
		//String content =   "{gateway:'网关005',node:'节点1',reason:'温度过高',time:'10:41:12'}" ;
		String content = "{gateway:'9001',node:'节点1',reason:'电压越阀值,',time:'16:17:26'}";
		String result = sendMessage(content);
	}
	

}
