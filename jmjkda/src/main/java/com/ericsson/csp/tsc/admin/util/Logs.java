package com.ericsson.csp.tsc.admin.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.csp.tsc.api.util.LogUtils;

public class Logs {
    // for add|delete|update
    private static final Logger LOGGER = LoggerFactory.getLogger(Logs.class);

    private Logs() {
    }

    public static void AdminOperatorLogs(Class<?> clazz, HttpServletRequest request, String operation, String target,
            String result, String description) {
        // operator need to get login user
        HttpSession session = request.getSession();
        String operator = (String) session.getAttribute("loginName");

        // knock together log format
        LOGGER.info(LogUtils.operationLog(operator, operation, target, result, description));

    }

}
