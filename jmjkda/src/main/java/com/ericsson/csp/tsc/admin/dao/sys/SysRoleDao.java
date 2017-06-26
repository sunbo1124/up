package com.ericsson.csp.tsc.admin.dao.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.dao.entity.SysRole;

public interface SysRoleDao {
    
    /**
     * 查询SysRole所有记录
     * @return
     */
    public List<SysRole> query();

    /**
     * 通过id查询SysRole
     * @param id
     * @return
     */
    public SysRole query(int id);
    
    /**
     * 将role对象保存到SysRole
     * @param role
     * @return
     */
    public int save(SysRole role);
    
    /**
     * 修改SysRole
     * @param role
     * @return
     */
    public int edit(SysRole role);
    
    /**
     * 根据id删除SysRole
     * @param id
     * @return
     */
    public int del(int id);

    /**
     * 根据name，id查询SysRole
     * @param name
     * @param id
     * @return
     */
    public SysRole query(String name, int id);

    /**
     * 根据name查询SysRole
     * @param name
     * @return
     */
    public SysRole query(String name);

    /**
     * 根据roleId查询SysMenu
     * @param id
     * @return
     */
    public List<SysMenu> queryRoleMenusByRoleId(int id);
    /**
     * 查询sysrole记录数
     * @return
     */
    public Integer queryCount();
    



}
