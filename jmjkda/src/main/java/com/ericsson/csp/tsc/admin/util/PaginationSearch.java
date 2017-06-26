package com.ericsson.csp.tsc.admin.util;

public class PaginationSearch {
    /**
     * Global search value. To be applied to all columns which have searchable as true.
     */
    private String value;

    /**
     * true if the global filter should be treated as a regular expression for advanced searching, false otherwise. Note
     * that normally server-side processing scripts will not perform regular expression searching for performance
     * reasons on large data sets, but it is technically possible and at the discretion of your script.
     */
    private String regex;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }
}
