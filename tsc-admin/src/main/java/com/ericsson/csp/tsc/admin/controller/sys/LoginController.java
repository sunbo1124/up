package com.ericsson.csp.tsc.admin.controller.sys;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.ericsson.csp.tsc.admin.dao.entity.SysUser;
import com.ericsson.csp.tsc.admin.service.sys.SysUserService;
import com.ericsson.csp.tsc.admin.util.Operation;
import com.ericsson.csp.tsc.admin.util.SysConstant;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService      userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String loginIn() {
        return "/login/login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> login(HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String locale = request.getParameter("locale");
        HttpSession session = request.getSession();
        session.setAttribute("locale", locale);
        session.setAttribute("loginName", username);
        String result = "";
        final UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            final Subject currentUser = SecurityUtils.getSubject();
            currentUser.login(token);
            if (currentUser.isAuthenticated()) {
                // TOOD...需要设置登陆时间？
                result = Operation.result(1, "Login Success.");
                LOGGER.info("OPERATION_LOG:[{}][login]User login success.", username);

            } else {
                result = Operation.result(0, "Login Failed.");
                token.clear();
            }
        } catch (AuthenticationException e) {
            result = Operation.result(0, "loginName or password error.");
            LOGGER.info("AuthenticationException exception: {}", e);
        } catch (Exception e) {
            LOGGER.error("login error: {}", e);
        }
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/checkPwd", method = RequestMethod.POST)
    public ResponseEntity<String> checkPwd(HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String result = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        request.getSession().setAttribute(SysConstant.SESSION_LOGIN_ID_KEY, username);
        SysUser user = userService.query(username);
        DateTime timeout = new DateTime(user.getPasswordLastModifyTime()).plusDays(90);
        LOGGER.info("timeout:", timeout);
        if (user != null || timeout.isBeforeNow() && password.equals(user.getPassword())) {
            result = Operation.result(0, "首次登陆或密码过期");
        }
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/preEditPwd", method = RequestMethod.GET)
    public String preEditPwd(HttpServletRequest request, Model model) {

        return "/login/editPwd";
    }

    @RequestMapping(value = "/editPwd", method = RequestMethod.POST)
    public ResponseEntity<String> editPwd(@RequestBody
    final String json, HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String result = "";
        String loginName = StringUtils.defaultIfEmpty(
                (String) request.getSession().getAttribute(SysConstant.SESSION_LOGIN_ID_KEY), "");
        String password = JSON.parseObject(json).getString("password");
        int type = userService.queryEditPwd(loginName, password);
        if (type == SysConstant.SUCCESS) {
            result = Operation.result(1, "修改密码成功");
            LOGGER.debug("update user {} password sucess", loginName);
        } else {
            result = Operation.result(0, "修改密码失败");
            LOGGER.debug("update user {} password failed", loginName);
        }

        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity<String> logout(HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String result = "";
        HttpSession session = request.getSession();
        session.invalidate();
        result = Operation.result(1, "注销成功");
        LOGGER.debug("user logout sucess");
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/queryLocale", method = RequestMethod.GET)
    public ResponseEntity<String> queryLocale(HttpServletRequest request) {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        String result = (String) request.getSession().getAttribute("locale");
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }
}
