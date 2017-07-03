package com.ericsson.csp.tsc.admin.util;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public interface PaginationRow {
    public SimplePropertyPreFilter fetchSimplePropertyPreFilter();
}
