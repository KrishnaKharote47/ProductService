package com.example.ecommerce.helper;

public class LogIDGenerator {

    public static String logIdGenerator(){
        long logID=0L;
        try {
            logID = System.currentTimeMillis();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "LOG"+ String.valueOf(logID)+":: ";
    }

}
