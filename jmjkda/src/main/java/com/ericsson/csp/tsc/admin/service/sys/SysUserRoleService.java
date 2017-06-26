package com.ericsson.csp.tsc.admin.service.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.SysUserRole;
import com.ericsson.csp.tsc.admin.util.Pagination;

public interface SysUserRoleService {

    /**
     * 将sysUserRole对象保存到SysUserRole
     * 
     * @param role
     */
    public void save(SysUserRole sysUserRole);

    /**
     * 根据id查询SysUserRole
     * 
     * @param id
     * @return
     */
    public SysUserRole query(int id);

    /**
     * 查询SysUserRole的所有信息
     * 
     * @return
     */
    public List<SysUserRole> query();

    /**
     * 修改SysUserRole
     * 
     * @param role
     * @param id
     */
    public void edit(SysUserRole role, int id);

    /**
     * 根据id删除SysUserRole
     * 
     * @param id
     */
    public void del(int id);

    public String queryTableJson(Pagination<SysUserRole> pagination);

}
