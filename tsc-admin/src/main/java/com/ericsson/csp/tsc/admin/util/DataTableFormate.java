package com.ericsson.csp.tsc.admin.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class DataTableFormate {
    /**
     * formate DataTable json with custom-side pagination.
     * 
     * @author wangsy
     * @param collection
     * @param filter
     * @return
     */
    private DataTableFormate() {
    }

    public static String formateTableJsonStrNoPagination(Collection<?> collection, SimplePropertyPreFilter filter) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", collection);
        return JSON.toJSONString(map, filter);
    }
}
