package com.ericsson.csp.tsc.admin.controller.sys;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ericsson.csp.tsc.admin.controller.pojo.AllotMenu;
import com.ericsson.csp.tsc.admin.service.sys.AllotMenuService;
import com.ericsson.csp.tsc.admin.util.DataTableFormate;
import com.ericsson.csp.tsc.admin.util.Logs;
import com.ericsson.csp.tsc.api.enumconst.LogOperationEnum;
import com.ericsson.csp.tsc.api.pojo.OperationResult;

@Controller
@RequestMapping(value = "/allotMenu")
public class AllotMenuController {

    @Autowired
    private AllotMenuService    allotMenuService;

    private final static Logger LOGGER = LoggerFactory.getLogger(AllotMenuController.class);

    private String              result;

    private String              operationResult;

    private HttpStatus          httpStatus;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<String> queryById(HttpServletRequest request, @PathVariable Integer id) {
        final HttpHeaders headers = createHeader();
        LOGGER.info("{} query", id);
        String locale = (String) request.getSession().getAttribute("locale");
        List<AllotMenu> list = allotMenuService.query(id, locale);
        String returnJson = DataTableFormate.formateTableJsonStrNoPagination(list, list.get(0)
                .fetchSimplePropertyPreFilter());
        return new ResponseEntity<String>(returnJson, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> remove(@RequestParam Integer roleId, @RequestParam Integer menuId) {
        final HttpHeaders headers = createHeader();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        try {
            allotMenuService.remove(roleId, menuId);
            result = "allotMenu is deleted";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("AllotMenuController delete allotMenu failed {}", e);
            result = "delete allotMenu failed!";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            operationResult = OperationResult.OPERATION_FAILURE.name();
        }
        Logs.AdminOperatorLogs(AllotMenuController.class, request, LogOperationEnum.DELETE.getValue() + " <allotMenu>",
                "delete allotMenu id is " + roleId + "," + menuId, operationResult, result);

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    @RequestMapping(value = "/add", method = RequestMethod.DELETE)
    public ResponseEntity<String> add(@RequestParam Integer roleId, @RequestParam Integer menuId) {
        final HttpHeaders headers = createHeader();
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();

        try {
            allotMenuService.add(roleId, menuId);
            result = "add allotMenu successed";
            httpStatus = HttpStatus.OK;
            operationResult = OperationResult.OPERATION_SUCCESS.name();
        } catch (Exception e) {
            LOGGER.error("AllotMenuController add allotMenu failed {}", e);
            result = "add allotMenu failed!";
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
            operationResult = OperationResult.OPERATION_FAILURE.name();
        }
        Logs.AdminOperatorLogs(AllotMenuController.class, request, LogOperationEnum.DELETE.getValue() + " <allotMenu>",
                "add allotMenu ids is " + roleId + "," + menuId, operationResult, result);

        return new ResponseEntity<String>(result, headers, httpStatus);
    }

    private HttpHeaders createHeader() {
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("text", "plain", Charset.forName("utf-8")));
        return headers;
    }

}
