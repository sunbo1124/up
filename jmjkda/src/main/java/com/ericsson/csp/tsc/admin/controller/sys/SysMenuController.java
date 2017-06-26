package com.ericsson.csp.tsc.admin.controller.sys;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;

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
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ericsson.csp.tsc.admin.controller.pojo.tool.BootstrapTreeviewPojo;
import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.service.sys.SysMenuService;
import com.ericsson.csp.tsc.admin.util.JacksonUtil;
import com.ericsson.csp.tsc.admin.util.Logs;
import com.ericsson.csp.tsc.admin.util.Pagination;
import com.ericsson.csp.tsc.api.enumconst.LogOperationEnum;
import com.ericsson.csp.tsc.api.pojo.OperationResult;

@Controller
@RequestMapping(value = "/menu")
public class SysMenuController {
    @Autowired
    private SysMenuService      menuService;

    private String              result;

    private String              operationResult;

    private HttpStatus          httpStatus;

    private final static Logger LOGGER = LoggerFactory.getLogger(SysMenuController.class);

    @RequestMapping(value = "/query", method = RequestMethod.GET)
    public ResponseEntity<String> queryAuthMenu(HttpServletRequest request, @RequestParam("locale") String locale) {
        final HttpHeaders headers = createHeader();
        String username = (String) request.getSession().getAttribute("loginName");
        List<BootstrapTreeviewPojo> menus = menuService.queryBootstrapTreeviewPojos(username, locale);
        final SerializerFeature[] serializerFeatures = {};
        String returnJson = JSON.toJSONStringWithDateFormat(menus, "yyyy-MM-dd HH:mm:ss", serializerFeatures);
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/testDataTable", method = RequestMethod.GET)
    public ResponseEntity<String> allMenus(HttpServletRequest request) {
        final HttpHeaders headers = createHeader();
        String params = request.getParameter("dataTableParamsJson");
        String returnJson;
        if (StringUtils.isNotBlank(params)) {
            @SuppressWarnings("unchecked")
            Pagination<SysMenu> pagination = JSON.parseObject(params, Pagination.class);
            returnJson = menuService.queryNotTreeMenus(pagination);
        } else {
            returnJson = menuService.queryNotTreeMenus(null);
        }

        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> query(HttpServletRequest request) {
        final HttpHeaders headers = createHeader();
        String params = request.getParameter("dataTableParamsJson");
        String locale = (String) request.getSession().getAttribute("locale");
        String returnJson;
        if (StringUtils.isNotBlank(params)) {
            @SuppressWarnings("unchecked")
            Pagination<SysMenu> pagination = JSON.parseObject(params, Pagination.class);
            returnJson = "";
            returnJson = menuService.queryTableJson(pagination, locale);
        } else {
            returnJson = menuService.queryTableJson(null, locale);
        }
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> queryById(@PathVariable Integer id) {
        final HttpHeaders headers = createHeader();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        LOGGER.info("{} query", id);
        SysMenu menu = menuService.query(id);
        String returnJson = JSON.toJSONString(menu, menu.fetchSimplePropertyPreFilter());
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(HttpServletRequest request, @RequestBody
    final String json) throws IOException {
        final HttpHeaders headers = createHeader();
        LOGGER.debug("updating json [{}]", json);
        SysMenu menu = null;
        try {
            menu = JacksonUtil.parseJsonTOObject(json, SysMenu.class);
            menu.setUpdateTime(new Date());
            menuService.edit(menu);
            result = "update menu successed!";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysMenuController update menu failed {}", e);
            result = "update menu failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        Logs.AdminOperatorLogs(SysMenuController.class, request, LogOperationEnum.UPDATE.getValue() + " sysMenu",
                menu == null ? null : menu.getName(), operationResult, result);
        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody
    final String json) {
        final HttpHeaders headers = createHeader();
        SysMenu menu = null;
        try {
            JSON.parseObject(json, SysMenu.class);
            menuService.save(menu);
            result = "save menu successed!";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysMenuController save menu failed {}", e);
            result = "save menu failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        Logs.AdminOperatorLogs(SysMenuController.class, request, LogOperationEnum.ADD.getValue() + " sysMenu",
                menu == null ? null : menu.getName(), operationResult, result);

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> del(@RequestParam("id") Integer id) {
        final HttpHeaders headers = createHeader();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        try {
            LOGGER.info("{} delete", id);
            menuService.del(id);
            result = "sysMenu is deleted";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("SysMenuController delete menu failed {}", e);
            result = "delete sysMenu failed!";
            operationResult = OperationResult.OPERATION_FAILURE.name();
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        Logs.AdminOperatorLogs(SysMenuController.class, request, LogOperationEnum.DELETE.getValue() + " sysMenu",
                "delete sysMenu id is" + id, operationResult, result);

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    private HttpHeaders createHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        return headers;
    }

}
