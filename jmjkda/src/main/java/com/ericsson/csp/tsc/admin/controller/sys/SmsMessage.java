package com.ericsson.csp.tsc.admin.controller.sys;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.csp.tsc.admin.dao.entity.LogFetcher;
import com.ericsson.csp.tsc.admin.util.LogUtils;
import com.ericsson.csp.tsc.api.util.AppConfiguration;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

class SmsMessage implements Runnable {
    private final static Logger LOGGER = LoggerFactory.getLogger(SmsMessage.class);

    private String              userName;

    private String              host;

    private String              port;

    private String              password;

    private String       logPath     = AppConfiguration.getString("smsAdapterLogPath");
    public SmsMessage() {
    }

    public SmsMessage(String userName, String host, String port, String password) {
        this.userName = userName;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    @Override
    public void run() {
        String cmdSms = "tail -F -n 1 "+logPath+"|grep when";
        // while (true) {
        try {
            JSch jsch = new JSch();
            Session session = jsch.getSession(userName, host, Integer.parseInt(port));
            session.setPassword(password);
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            ChannelExec channelSms = (ChannelExec) session.openChannel("exec");
            BufferedReader inSms = new BufferedReader(new InputStreamReader(channelSms.getInputStream()));
            channelSms.setCommand(cmdSms);
            channelSms.setPty(true);
            channelSms.connect();
            LOGGER.info("Connect to the sms-adapter server {} successfully! ",host);
            String msgSms;
            while ((msgSms = inSms.readLine()) != null) {
                LogFetcher lf = new LogFetcher();
                lf = LogUtils.parseLogSms(msgSms, LogUtils.vinMapSms);
                LogUtils.saveLog(lf);
            }
            channelSms.disconnect();
            LOGGER.info("Disconnect to the sms-adapter server {} ",host);
            session.disconnect();
        } catch (Exception e) {
            LOGGER.info("Disconnect to the sms-adapter server {} successfully! ",host);
        }
        // }
    }

}
