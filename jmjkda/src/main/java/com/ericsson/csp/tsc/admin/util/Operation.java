package com.ericsson.csp.tsc.admin.util;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Operation {

    private static final Logger LOGGER      = LoggerFactory.getLogger(TestConnectionPostgreSql.class);

    public static final int     SUCCESSCODE = 1;

    public static final int     FAILCODE    = 0;

    private Operation() {
        super();
    }

    /**
     * 
     * @param resultCode
     *            0 代表错误 1正确
     * @param resultMsg
     *            如果0，则返回错误信息
     * @return
     */
    public static String result(int resultCode, String resultMsg) {
        JSONObject result = new JSONObject();
        try {
            result.put("resultCode", resultCode);
            result.put("resultMsg", resultMsg);
            result.put("resultList", new JSONArray());
        } catch (Exception e) {
            LOGGER.info("JSONObject put result failure", e);
        }
        return result.toString();
    }

    /**
     * 
     * @param resultCode
     *            0 代表错误 1正确
     * @param resultMsg
     *            如果0，则返回错误信息
     * @param results
     *            返回对象
     * @return
     */
    public static String result(int resultCode, String resultMsg, String results) {
        JSONObject result = new JSONObject();
        try {
            result.put("resultCode", resultCode);
            result.put("resultMsg", resultMsg);
            result.put("resultList", results);
        } catch (Exception e) {
            LOGGER.info("JSONObject put result failure", e);
        }
        return result.toString();
    }
}
