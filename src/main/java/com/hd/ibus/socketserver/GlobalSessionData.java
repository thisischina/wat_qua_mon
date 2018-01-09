package com.hd.ibus.socketserver;

import com.hd.ibus.pojo.SessionData;
import org.apache.mina.core.session.IoSession;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ThinkPad on 2017-08-04.
 */
public class GlobalSessionData {
    private static final Map<Long,SessionData> sessionDataMap = new HashMap();
    private static final Map<String,IoSession> sessionMap = new HashMap<String, IoSession>();

    public static Map<Long, SessionData> getSessionDataMap() {
        return sessionDataMap;
    }

    public static Map<String, IoSession> getSessionMap() {
        return sessionMap;
    }

    public static synchronized void putSessionData(Long sessionId, SessionData sessionData){
        sessionDataMap.put(sessionId,sessionData);
    }

    public static synchronized void putSession(String dtuId, IoSession session){
        sessionMap.put(dtuId,session);
    }

    public static IoSession getSession(String dtuId){
        return  null;
    }
    public static synchronized void deleteSession(IoSession session){

        sessionMap.remove(session.getId());

    }



}
