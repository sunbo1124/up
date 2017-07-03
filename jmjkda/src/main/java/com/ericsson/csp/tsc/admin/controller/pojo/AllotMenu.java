package com.ericsson.csp.tsc.admin.controller.pojo;

import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ericsson.csp.tsc.api.util.PaginationRow;

public class AllotMenu implements java.io.Serializable, PaginationRow {

    private static final long serialVersionUID = -4326136570239514465L;

    private int               pid;

    private int               menuId;

    private String            name;

    private Boolean           check;

    public AllotMenu() {
    }

    public AllotMenu(int pid, int menuId, String name, Boolean check) {
        this.pid = pid;
        this.menuId = menuId;
        this.name = name;
        this.check = check;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getMenuId() {
        return menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    @JSONField(serialize = false)
    @Override
    @Transient
    public SimplePropertyPreFilter fetchSimplePropertyPreFilter() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter(AllotMenu.class, "pid", "menuId", "name", "check");
        return filter;
    }

}
