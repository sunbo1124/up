package com.ericsson.csp.tsc.admin.util;

import java.security.MessageDigest;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtil {

    private CommonUtil() {
        super();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);

    public static JestClient jestClient(String connUrl) throws Exception {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(connUrl).multiThreaded(true).build());
        return factory.getObject();
    }

    public static String getCurDate(final String format) {
        final DateTime d = new DateTime();
        return d.toString(format);
    }
    
    public static String string2MD5(final String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (final Exception e) {
            LOGGER.info("string2MD5 MessageDigest getInstance failure", e);
            return "";
        }
        final char[] charArray = inStr.toCharArray();
        final byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        final byte[] md5Bytes = md5.digest(byteArray);
        final StringBuilder hexValue = new StringBuilder();
        for (final byte md5Byte : md5Bytes) {
            final int val = (md5Byte) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }
}
