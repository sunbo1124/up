package com.ericsson.csp.tsc.admin.service.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.dao.entity.SysRole;
import com.ericsson.csp.tsc.admin.util.Pagination;

public interface SysRoleService {

    /**
     * 将role对象保存到SysRole
     * 
     * @param role
     */
    public void save(SysRole role);

    /**
     * 根据id查询SysRole
     * 
     * @param id
     * @return
     */
    public SysRole query(int id);

    /**
     * 查询SysRole的所有信息
     * 
     * @return
     */
    public List<SysRole> query();

    /**
     * 修改SysRole
     * 
     * @param role
     * @param id
     */
    public void edit(SysRole role, int id);

    /**
     * 根据id删除SysRole
     * 
     * @param id
     */
    public void del(int id);

    /**
     * 根据roleId查询SysMenu
     * 
     * @param id
     * @return
     */
    public List<SysMenu> queryRoleMenuByRoleId(int id);

    /**
     * 角色的菜单授权
     * 
     * @param roleId
     * @param menuIds
     */
    public void assignRoleMenu(int roleId, Integer[] menuIds);

    /**
     * 查询角色信息
     * 
     * @param pagination
     */
    public String queryTableJson(Pagination<SysRole> pagination);

    /**
     * 将sysRole保存到SysRole
     * 
     * @param sysRole
     */
    public void saveRole(SysRole role);
    
}
