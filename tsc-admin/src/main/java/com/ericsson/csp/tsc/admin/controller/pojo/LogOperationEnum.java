package com.ericsson.csp.tsc.admin.controller.pojo;

public enum LogOperationEnum {

    ADD("Add"), 
    DELETE("Delete"), 
    UPDATE("Update"), 
    QUERY("Query"), 
    REMOTE_CONTROL("Remote Control"), 
    TEM_MANAGEMENT("Tem Management"), 
    LOGIN("Login"), 
    LOGOUT("Logout"), 
    PICKUPFRIEND("pickupFriend");

    private String value;

    private LogOperationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
