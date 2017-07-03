package com.hd.ibus.util.activeMQ;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.jms.Destination;

public interface PushService {
	
	public final ExecutorService pushExecutor = Executors.newFixedThreadPool(10);  
    
    public void push(Object info,Destination destination); 

}
