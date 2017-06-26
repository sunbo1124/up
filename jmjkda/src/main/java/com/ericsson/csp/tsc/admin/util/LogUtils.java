package com.ericsson.csp.tsc.admin.util;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ericsson.csp.tsc.admin.dao.entity.LogFetcher;
import com.ericsson.csp.tsc.api.util.AppConfiguration;

public class LogUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);
    
    public static ConcurrentHashMap<String, Long> vinMap = new ConcurrentHashMap<String, Long>();

    public static ConcurrentHashMap<String, Long> vinMapSms =new ConcurrentHashMap<String, Long>();
    
    private static String         logfetcherPath = AppConfiguration.getString("logfetcherPath");


    
    public static void saveLog(LogFetcher logFetcher){
        try {
            if (logFetcher != null) {
                FileWriter writer;

                String vinContent = logfetcherPath + File.separator + logFetcher.getVin();
                if (!new File(vinContent).exists()) {
                    new File(vinContent).mkdirs();
                }
                writer = new FileWriter(vinContent + File.separator + logFetcher.getShortname() + ".log");
                writer.write(logFetcher.getContent());
                writer.flush();
                writer.close();

            }
        } catch (IOException e) {
            LOGGER.error("Error : ", e);
        }
    }
    public static LogFetcher parseLogDis(String message, ConcurrentHashMap<String, Long> vinMap) {
        try {
            String context = message.substring(message.lastIndexOf("when"));
            String[] a = context.split(" ", 6);
            String vin = a[4].substring(8, a[4].length()-1);
            for (Entry<String, Long> e : vinMap.entrySet()) {
                if (vin.equals(e.getKey())) {
                    LogFetcher logFetcher = new LogFetcher();
                    String time = message.substring(0, 19).replace(":", "-");
                    String direction = a[2].substring(11, a[2].length()-1);
                    String service = a[3].substring(9, a[3].length()-1);
                    logFetcher.setShortname("dis_" + time + "_" + direction + "_" + service + "_" + vin);
                    String format= formatLog(a[5].substring(8)); 
                    LOGGER.debug("{}", format);
                    logFetcher.setContent(format);
                    logFetcher.setVin(vin);
                    return logFetcher;
                }
            }

        } catch (Exception e) {
            LOGGER.error("Error : ", e);
        }
        return null;
    }
    public static String formatLog(String log){
        String format =null;
        try {
            GroovyClassLoader loader = new GroovyClassLoader();
            String path = LogUtils.class.getResource("/").getPath();
            Class fileCreator = loader.parseClass(new File(path+"/translate.groovy"));
            GroovyObject object = (GroovyObject) fileCreator.newInstance();
            format = (String) object.invokeMethod("init", new Object[]{log,path});
            loader.close();
        } catch (Exception e) {
            LOGGER.error("Error : ", e);
            return log;
        } 
        return format;
    }
    public static LogFetcher parseLogSms(String message, ConcurrentHashMap<String, Long> vinMapSms) {
        try {
        	String context =message.substring(message.lastIndexOf("when"));
        	String[] a = context.split(" ", 6);
            String source = a[3];
            if (source.contains("msisdn")) {
                LogFetcher logFetcher = new LogFetcher();
                String msisdn = source.substring(8, source.length()-1);
                for (Entry<String, Long> e : vinMapSms.entrySet()) {
                    if (msisdn.equals(e.getKey()) && logTrackingTimeout(e.getValue())) {
                        String time = message.substring(1, 20).replace(":", "-");
                        String direction = a[1].substring(11, a[1].length()-1);
                        String service = a[2].substring(9, a[2].length()-1);
                        logFetcher.setShortname("SMS_" + time + "_" + direction + "_" + service + "_" + msisdn);
                        logFetcher.setContent(a[5]);
                        for (Entry<String, Long> en : vinMapSms.entrySet()) {
                            if (e.getValue().equals(en.getValue()) && en.getKey().length() == 17) {

                                logFetcher.setVin(en.getKey());
                            }
                        }
                        LOGGER.debug("{}", context);
                        return logFetcher;

                    }
                }
            }
            if (source.contains("iccid")) {
                String iccid = source.substring(7, source.length()-1);
                for (Entry<String, Long> e : vinMapSms.entrySet()) {
                    if (iccid.equals(e.getKey()) && logTrackingTimeout(e.getValue())) {
                        LogFetcher logFetcher = new LogFetcher();
                        String time = message.substring(1, 20).replace(":", "-");
                        String direction = a[1].substring(11, a[1].length()-1);
                        String service = a[2].substring(9,a[2].length()-1);
                        logFetcher.setShortname("SMS_" + time + "_" + direction + "_" + service + "_" + iccid);
                        logFetcher.setContent(a[5]);
                        for (Entry<String, Long> en : vinMapSms.entrySet()) {
                            if (e.getValue().equals(en.getValue()) && en.getKey().length() == 17) {

                                logFetcher.setVin(en.getKey());
                            }
                        }
                        LOGGER.debug("{}", context);
                        return logFetcher;

                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("Error : ", e);
        }
        return null;
    }
    public static Boolean logTrackingTimeout(Long time) {
      
        try {
           if(time>new Date().getTime()){
            return true;   
           }
        } catch (Exception e) {
            LOGGER.error("Error : ", e);
        }
        return false;

    }
}