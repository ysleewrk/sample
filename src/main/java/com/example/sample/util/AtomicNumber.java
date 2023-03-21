package com.example.sample.util;

import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class AtomicNumber {
    private AtomicInteger atomicInteger = null;
    public AtomicNumber(){
        atomicInteger = new AtomicInteger(0);
    }

    public int getNumber(){
        int number = atomicInteger.getAndIncrement();
        if(number >= 998){
            atomicInteger = new AtomicInteger(0);
            number = atomicInteger.getAndIncrement();
        }
        return number;
    }

    public String getString(){
        int number = atomicInteger.getAndIncrement();
        if(number >= 998){
            atomicInteger = new AtomicInteger(0);
            number = atomicInteger.getAndIncrement();
        }
        return String.format("%03d", number);
    }

	public String getTrxGuid(String prefix) throws Exception {

	    String serverName = null;
		try {
			serverName = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			throw new Exception(e);
		}
		
        serverName = serverName.substring(serverName.length() - 2, serverName.length()); 
        
        String trxGuid = prefix + serverName + "" + getString();
        
        return trxGuid;
	}
}
