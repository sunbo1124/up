package com.ericsson.csp.tsc.admin.controller.sys;

import java.nio.charset.Charset;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.ericsson.csp.tsc.admin.dao.entity.SysUser;
import com.ericsson.csp.tsc.admin.service.sys.SysUserService;
import com.ericsson.csp.tsc.admin.util.Logs;
import com.ericsson.csp.tsc.admin.util.Pagination;
import com.ericsson.csp.tsc.api.enumconst.LogOperationEnum;
import com.ericsson.csp.tsc.api.pojo.OperationResult;

@Controller
@RequestMapping(value = "/user")
public class SysUserController {
    @Autowired
    private SysUserService      userService;

    private final static Logger LOGGER = LoggerFactory.getLogger(SysUserController.class);

    private String              result;

    private String              operationResult;

    private HttpStatus          httpStatus;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> query(HttpServletRequest request) {
        final HttpHeaders headers = createHeader();
        String params = request.getParameter("dataTableParamsJson");
        String returnJson;
        if (StringUtils.isNotBlank(params)) {
            @SuppressWarnings("unchecked")
            Pagination<SysUser> pagination = JSON.parseObject(params, Pagination.class);
            returnJson = userService.queryTableJson(pagination);
        } else {
            returnJson = userService.queryTableJson(null);
        }

        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/noAllotRole/", method = RequestMethod.GET)
    public ResponseEntity<String> queryNoAllotRole(HttpServletRequest request) {
        final HttpHeaders headers = createHeader();
        String returnJson = userService.queryNoAllotRoleJson();
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> queryById(@PathVariable Integer id) {
        final HttpHeaders headers = createHeader();
        LOGGER.info("{} query", id);
        SysUser user = userService.query(id);
        String returnJson = JSON.toJSONString(user, user.fetchSimplePropertyPreFilter());
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody
    final String json) {
        final HttpHeaders headers = createHeader();
        SysUser user = JSON.parseObject(json, SysUser.class);
        LOGGER.info("SysUserController save user:{}", user);
        try {
            userService.saveUser(user);
            result = "save user successed!";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysUserController save user failed {}", e);
            result = "save user failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        Logs.AdminOperatorLogs(SysUserController.class, request, LogOperationEnum.ADD.getValue() + " sysUser",
                user == null ? null : user.getName(), operationResult, result);

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/queryLoginName")
    public ResponseEntity<String> queryByLoginName(HttpServletRequest request, @RequestBody String loginName) {
        final HttpHeaders headers = createHeader();
        result = "0";
        SysUser sysUser = new SysUser();
        if (loginName != null) {
            sysUser = userService.query(loginName);
        }
        if (sysUser != null) {
            result = "1";
        }
        return new ResponseEntity<String>(result, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(HttpServletRequest request, @RequestBody
    final String json) {
        final HttpHeaders headers = createHeader();
        LOGGER.debug("sys user update {}", json);
        SysUser sysUser = new SysUser();
        try {
            sysUser = JSON.parseObject(json, SysUser.class);
            userService.edit(sysUser, sysUser.getId());
            result = "update user successed!";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysUserController update user failed {}", e);
            result = "update user failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        Logs.AdminOperatorLogs(SysUserController.class, request, LogOperationEnum.UPDATE.getValue() + " sysUser",
                sysUser == null ? null : sysUser.getName(), operationResult, result);

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> del(@RequestParam("id") Integer id) {
        LOGGER.info("{} delete", id);
        final HttpHeaders headers = createHeader();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        try {
            userService.del(id);
            result = "sysUser is deleted";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();

        } catch (Exception e) {
            LOGGER.error("SysUserController delete user failed {}", e);
            result = "delete user failed!";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            operationResult = OperationResult.OPERATION_FAILURE.name();
        }
        Logs.AdminOperatorLogs(SysUserController.class, request, LogOperationEnum.DELETE.getValue() + " sysUser",
                "delete user id is " + id, operationResult, result);

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    private HttpHeaders createHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        return headers;
    }
}
