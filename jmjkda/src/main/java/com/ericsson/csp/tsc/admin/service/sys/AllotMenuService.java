package com.ericsson.csp.tsc.admin.service.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.controller.pojo.AllotMenu;

public interface AllotMenuService {

    public List<AllotMenu> query(int roleId, String locale);

    public int remove(int roleId, int menuId);

    public int add(int roleId, int menuId);

}
