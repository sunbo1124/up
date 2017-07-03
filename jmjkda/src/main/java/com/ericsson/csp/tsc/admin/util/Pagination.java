package com.ericsson.csp.tsc.admin.util;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ericsson.csp.tsc.api.util.PaginationRow;

/**
 * 
 * @author wangsy DataTables parameters Java Bean, include pagination\search\order function and so on.
 * @param <T>
 */
public class Pagination<T> implements Serializable {

    private static final long      serialVersionUID = -953608902473032026L;

    private static final Logger    LOGGER           = LoggerFactory.getLogger(Pagination.class);

    /**
     * The draw counter that this object is a response to - from the draw parameter sent as part of the data request.
     * Note that it is strongly recommended for security reasons that you cast this parameter to an integer, rather than
     * simply echoing back to the client what it sent in the draw parameter, in order to prevent Cross Site Scripting
     * (XSS) attacks.
     */
    private Integer                draw;

    /**
     * Paging first record indicator. This is the start point in the current data set (0 index based - i.e. 0 is the
     * first record).
     */
    private int                    start;

    /**
     * Number of records that the table can display in the current draw. It is expected that the number of records
     * returned will be equal to this number, unless the server has fewer records to return. Note that this can be -1 to
     * indicate that all records should be returned (although that negates any benefits of server-side processing!)
     */
    private int                    length;

    private PaginationSearch       search;

    private List<PaginationOrder>  order;

    private List<PaginationColumn> columns;

    /**
     * Total records, before filtering (i.e. the total number of records in the database)
     */
    private Integer                recordsTotal;

    /**
     * Total records, after filtering (i.e. the total number of records after filtering has been applied - not just the
     * number of records being returned for this page of data).
     */
    private Integer                recordsFiltered;

    /**
     * The data to be displayed in the table. This is an array of data source objects, one for each row, which will be
     * used by DataTables. Note that this parameter's name can be changed using the ajax option's dataSrc property.
     */
    private List<T>                data;

    /**
     * Optional: If an error occurs during the running of the server-side processing script, you can inform the user of
     * this error by passing back the error message to be displayed using this parameter. Do not include if there is no
     * error.
     */
    private String                 error;

    public String parsePagination(boolean operation) {
        if (draw == null || recordsTotal == null || recordsFiltered == null) {
            LOGGER.error("draw: {}, recordsTotal: {}, recordsFiltered: {}", draw, recordsTotal, recordsFiltered);
            throw new RuntimeException("Pagination parameter error.");
        }
        JSONObject returnJson = new JSONObject();
        returnJson.put("draw", draw);
        returnJson.put("recordsTotal", recordsTotal);
        returnJson.put("recordsFiltered", recordsFiltered);
        JSONArray jsonArray = new JSONArray();
        for (T t : data) {
            if (t instanceof PaginationRow) {
                JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(t,
                        ((PaginationRow) t).fetchSimplePropertyPreFilter(), SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullNumberAsZero));
                // if need operation btn
                if (operation) {
                    jsonObject.put("operation", operation);
                }
                jsonArray.add(jsonObject);
            }
        }
        returnJson.put("data", jsonArray);

        LOGGER.debug("pagination json str: {}", recordsTotal);
        return returnJson.toJSONString();
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Integer recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Integer getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Integer recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public PaginationSearch getSearch() {
        return search;
    }

    public void setSearch(PaginationSearch search) {
        this.search = search;
    }

    public List<PaginationOrder> getOrder() {
        return order;
    }

    public void setOrder(List<PaginationOrder> order) {
        this.order = order;
    }

    public List<PaginationColumn> getColumns() {
        return columns;
    }

    public void setColumns(List<PaginationColumn> columns) {
        this.columns = columns;
    }
}
