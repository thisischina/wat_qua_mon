package com.hd.ibus.socketserver;

import com.hd.ibus.pojo.SessionData;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import javax.jms.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lekoxnfx on 2017/7/13.
 */
public class MinaIOHandler extends IoHandlerAdapter {

    public static final Map<Long,SessionData> map = new HashMap();

    public MinaIOHandler() {
        super();
        System.out.println("MinaIOHandler 初始化!");

    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        System.out.println("session opened");
        session.write("@DTU:0000:DTUALL?");
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        System.out.println("session closed");
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        super.exceptionCaught(session, cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        super.messageReceived(session, message);

        System.out.println(message.toString());

        String str = message.toString();
        String regEx = "\\+.*?:";
        String switchStr = "";
        Pattern pattern = Pattern.compile(regEx,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            switchStr = matcher.group(0).toString();
        }

        //获取当前的session对应的终端信息
        SessionData sessionData = map.get(session.getId());

        if (sessionData!=null){
            if (switchStr.equals("+DTUID:")) {
                sessionData.setDTUID(message.toString().substring(message.toString().indexOf("+DTUID:") + 7));
            } else if (switchStr.equals("+DSCADDR:")) {
                sessionData.setDSCADDR(message.toString().substring(message.toString().indexOf("+DSCADDR:") + 9));
            } else if (switchStr.equals("+KEEPALIVE:")) {
                sessionData.setKEEPALIVE(message.toString().substring(message.toString().indexOf("+KEEPALIVE:") + 11));
            } else if (switchStr.equals("+UARTCFG:")){
                sessionData.setUARTCFG(message.toString().substring(message.toString().indexOf("+UARTCFG:")+9));
            } else if (switchStr.equals("+DEBUGMODE:")){
                sessionData.setDEBUGMODE(message.toString().substring(message.toString().indexOf("+DEBUGMODE:")+11));
            } else if (switchStr.equals("+DTUFILTER:")){
                sessionData.setDTUFILTER(message.toString().substring(message.toString().indexOf("+DTUFILTER:")+11));
            }
            map.put(session.getId(),sessionData);
        }else {
            sessionData = new SessionData();
            if (switchStr.equals("+DTUID:")) {
                sessionData.setDTUID(message.toString().substring(message.toString().indexOf("+DTUID:") + 1));
            } else if (switchStr.equals("+DSCADDR:")) {
                sessionData.setDSCADDR(message.toString().substring(message.toString().indexOf("+DSCADDR:") + 1));
            } else if (switchStr.equals("+KEEPALIVE:")) {
                sessionData.setKEEPALIVE(message.toString().substring(message.toString().indexOf("+KEEPALIVE:") + 1));
            } else if (switchStr.equals("+UARTCFG:")){
                sessionData.setUARTCFG(message.toString().substring(message.toString().indexOf("+UARTCFG:")+9));
            } else if (switchStr.equals("+DEBUGMODE:")){
                sessionData.setDEBUGMODE(message.toString().substring(message.toString().indexOf("+DEBUGMODE:")+11));
            } else if (switchStr.equals("+DTUFILTER:")){
                sessionData.setDTUFILTER(message.toString().substring(message.toString().indexOf("+DTUFILTER:")+11));
            }
            map.put(session.getId(),sessionData);
        }

//        String response = message.toString();
//        System.out.println(response);
//        System.out.println(message.toString());
//        session.write(response);
//
//        session.write("@DTU:0000:DTUALL?");
//        String response = message.toString();
//        System.out.println(response);

    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        super.messageSent(session, message);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        super.inputClosed(session);
    }

}
