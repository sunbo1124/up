package com.ericsson.csp.tsc.admin.controller.sys;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;

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
import com.ericsson.csp.tsc.admin.controller.pojo.OperationResult;
import com.ericsson.csp.tsc.admin.dao.entity.SysRole;
import com.ericsson.csp.tsc.admin.service.sys.SysRoleService;
import com.ericsson.csp.tsc.admin.util.JacksonUtil;
import com.ericsson.csp.tsc.admin.util.Pagination;

@Controller
@RequestMapping(value = "/role")
public class SysRoleController {
    @Autowired
    private SysRoleService      roleService;

    private final static Logger LOGGER = LoggerFactory.getLogger(SysRoleController.class);

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
            Pagination<SysRole> pagination = JSON.parseObject(params, Pagination.class);
            returnJson = roleService.queryTableJson(pagination);
        } else {
            returnJson = roleService.queryTableJson(null);
        }
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> queryById(@PathVariable Integer id) {
        final HttpHeaders headers = createHeader();
        LOGGER.info("{} query", id);
        SysRole role = roleService.query(id);
        String returnJson = JSON.toJSONString(role, role.fetchSimplePropertyPreFilter());
        LOGGER.info("returnJson", returnJson);
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(HttpServletRequest request, @RequestBody
    final String json) throws IOException {
        final HttpHeaders headers = createHeader();
        LOGGER.debug("updating json [{}]", json);
        SysRole role = new SysRole();
        try {
            role = JacksonUtil.parseJsonTOObject(json, SysRole.class);
            role.setUpdateTime(new Date());
            roleService.edit(role, role.getId());
            result = "update role successed!";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysRoleController update role failed {}", e);
            result = "update role failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody
    final String json) {
        final HttpHeaders headers = createHeader();
        SysRole role = new SysRole();
        try {
            role = JSON.parseObject(json, SysRole.class);
            roleService.saveRole(role);
            result = "save role successed!";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysRoleController save role failed {}", e);
            result = "save role failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> del(@RequestParam("id") Integer id) {
        LOGGER.info("{} delete", id);
        final HttpHeaders headers = createHeader();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        try {
            roleService.del(id);
            result = "sysRole is deleted";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysRoleController delete role failed {}", e);
            result = "delete role failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }


        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    private HttpHeaders createHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        return headers;
    }
}
