package com.ericsson.csp.tsc.admin.dao.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.SysUserRole;

public interface SysUserRoleDao {

    /**
     * 查询SysUserRole所有记录
     * 
     * @return
     */
    public List<SysUserRole> query();

    /**
     * 通过id查询SysUserRole
     * 
     * @param id
     * @return
     */
    public SysUserRole query(int id);

    /**
     * 将sysUserRole对象保存到SysUserRole
     * 
     * @param role
     * @return
     */
    public int save(SysUserRole sysUserRole);

    /**
     * 修改SysUserRole
     * 
     * @param role
     * @return
     */
    public int edit(SysUserRole sysUserRole);

    /**
     * 根据id删除SysUserRole
     * 
     * @param id
     * @return
     */
    public int del(int id);

    /**
     * 将userRoles保存到SysUserRole
     * 
     * @param userRoles
     */
    public void save(List<SysUserRole> userRoles);

    /**
     * 根据roleId查询SysUserRole
     * 
     * @param id
     * @return
     */
    public List<SysUserRole> queryByRoleId(int id);

    /**
     * 根据UserId删除SysUserRole
     * 
     * @param id
     */
    public void deleteByUserId(int id);

    /**
     * 根据UserId查询SysUserRole
     * 
     * @param id
     * @return
     */
    public List<SysUserRole> queryByUserId(int id);

    /**
     * 查询sysrole记录数
     * 
     * @return
     */
    public Integer queryCount();
}
