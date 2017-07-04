package com.ericsson.csp.tsc.admin.dao.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.SysRoleMenu;

public interface SysRoleMenuDao {

    /**
     * 通过roleId删除SysRoleMenu
     * 
     * @param id
     */
    public void delByRoleId(int id);

    /**
     * 将roleMenus集合保存到SysRoleMenu
     * 
     * @param roleMenus
     */
    public void save(List<SysRoleMenu> roleMenus);

    /**
     * 通过roleId查询SysRoleMenu
     * 
     * @param id
     * @return
     */
    public List<SysRoleMenu> queryByRoleId(int id);

    /**
     * 通过roleId的集合，查询SysRoleMenu
     * 
     * @param ids
     * @param locale
     * @return
     */
    public List<SysRoleMenu> queryByRoleIds(List<Integer> ids);

    /**
     * 通过roleId,menuId删除SysRoleMenu
     * 
     * @param roleId
     * @param menuId
     */
    public int delByRoleIdAndMenuId(int roleId, int menuId);

    /**
     * 将sysRoleMenu对象保存到SysRoleMenu
     * 
     * @param sysUser
     * @return
     */
    public int save(final SysRoleMenu sysRoleMenu);

}
