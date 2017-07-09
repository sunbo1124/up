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
import com.ericsson.csp.tsc.admin.dao.entity.HealthRecord;
import com.ericsson.csp.tsc.admin.service.work.HealthRecordService;
import com.ericsson.csp.tsc.admin.util.JacksonUtil;
import com.ericsson.csp.tsc.admin.util.Pagination;

@Controller
@RequestMapping(value = "/healthRecord")
public class HealthRecordController {
	
    @Autowired
    private HealthRecordService      healthRecordService;

    private final static Logger LOGGER = LoggerFactory.getLogger(HealthRecordController.class);

    private String              result;

    private HttpStatus          httpStatus;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<String> query(HttpServletRequest request) {
        final HttpHeaders headers = createHeader();
        String params = request.getParameter("dataTableParamsJson");
        String returnJson;
        if (StringUtils.isNotBlank(params)) {
            @SuppressWarnings("unchecked")
            Pagination<HealthRecord> pagination = JSON.parseObject(params, Pagination.class);
            returnJson = healthRecordService.queryTableJson(pagination);
        } else {
            returnJson = healthRecordService.queryTableJson(null);
        }
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> queryById(@PathVariable Integer id) {
        final HttpHeaders headers = createHeader();
        LOGGER.info("{} query", id);
        HealthRecord role = healthRecordService.query(id);
        String returnJson = JSON.toJSONString(role, role.fetchSimplePropertyPreFilter());
        LOGGER.info("returnJson", returnJson);
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<String> update(HttpServletRequest request, @RequestBody
    final String json) throws IOException {
        final HttpHeaders headers = createHeader();
        LOGGER.debug("updating json [{}]", json);
        HealthRecord healthRecord = new HealthRecord();
        try {
        	healthRecord = JacksonUtil.parseJsonTOObject(json, HealthRecord.class);
            healthRecord.setUpdateTime(new Date());
            healthRecordService.edit(healthRecord, healthRecord.getId());
            result = "update healthRecord successed!";
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            LOGGER.error("HealthRecordController update role failed {}", e);
            result = "update healthRecord failed!";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<String> save(HttpServletRequest request, @RequestBody
    final String json) {
        final HttpHeaders headers = createHeader();
        HealthRecord healthRecord = new HealthRecord();
        try {
        	healthRecord = JSON.parseObject(json, HealthRecord.class);
            healthRecordService.save(healthRecord);
            result = "save role successed!";
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            LOGGER.error("HealthRecordController save role failed {}", e);
            result = "save role failed!";
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
        	healthRecordService.del(id);
            result = "HealthRecord is deleted";
            httpStatus = HttpStatus.OK;
        } catch (Exception e) {
            LOGGER.error("HealthRecordController delete role failed {}", e);
            result = "delete role failed!";
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
