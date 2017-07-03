package com.ericsson.csp.tsc.admin.controller.sys;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ericsson.csp.tsc.admin.realms.WebRealm;

@Controller
@RequestMapping(value = "/api")
public class AngularShiroController {

    private final static Logger LOGGER = LoggerFactory.getLogger(SysVersionController.class);

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody
    final String json, final HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        JSONObject jsonObject = JSON.parseObject(json);
        LOGGER.debug("jsonObject", jsonObject);

        final Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.hasRole("admin")) {
            LOGGER.debug("login role is", "admin");
        } else {
            LOGGER.debug("login role is not ", "admin role");
        }
        Object shiroStr = currentUser.getSession().getAttribute(WebRealm.ANGULAR_SHIRO);
        String result = null;
        if (shiroStr != null) {
            result = shiroStr.toString();
        }

        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }
}
