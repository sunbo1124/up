package com.ericsson.csp.tsc.admin.util;

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
}
