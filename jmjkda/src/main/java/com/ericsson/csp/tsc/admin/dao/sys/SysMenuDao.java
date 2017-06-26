package com.ericsson.csp.tsc.admin.dao.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.util.Pagination;

public interface SysMenuDao {
    public List<SysMenu> query(Pagination<SysMenu> pagination);

    public List<SysMenu> query(List<Integer> menuIds, String locale);

    public Integer queryCount();

    public Integer queryCount(String locale);

    public List<SysMenu> query(String locale);

    public Integer save(SysMenu menu);

    /**
     * 查询SysMenu所有记录
     * 
     * @return
     */
    public List<SysMenu> query();

    /**
     * 通过id查询SysMenu
     * 
     * @param id
     * @return
     */
    public SysMenu query(int id);

    /**
     * 修改SysMenu
     * 
     * @param menu
     * @return
     */
    public int edit(SysMenu menu);

    /**
     * 根据id删除SysMenu
     * 
     * @param id
     * @return
     */
    public int del(int id);
}
