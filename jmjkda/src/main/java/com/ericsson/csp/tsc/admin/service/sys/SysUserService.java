package com.ericsson.csp.tsc.admin.service.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.SysRole;
import com.ericsson.csp.tsc.admin.dao.entity.SysUser;
import com.ericsson.csp.tsc.admin.util.Pagination;

public interface SysUserService {

    /**
     * 将sysUser保存到SysUser
     * 
     * @param sysUser
     */
    public void saveUser(SysUser sysUser);

    /**
     * 根据id查询SysUser
     * 
     * @param id
     * @return
     */
    public SysUser query(int id);

    /**
     * 查询所有SysUser
     * 
     * @return
     */
    public List<SysUser> query();

    /**
     * 查询SysUser的JSON
     * 
     * @return
     */
    public String queryTableJson(Pagination<SysUser> pagination);

    /**
     * 修改SysUser
     * 
     * @param user
     * @param id
     */
    public Integer edit(SysUser user, int id);

    /**
     * 根据id删除SysUser
     * 
     * @param id
     */
    public void del(int id);

    /**
     * 用户的角色授权
     * 
     * @param userId
     * @param roleIds
     */
    public void updateUserRole(int userId, Integer[] roleIds);

    /**
     * 根据userId查询SysRole
     * 
     * @param id
     * @return
     */
    public List<SysRole> queryRolesByUserId(int id);

    /**
     * 根据loginName查询SysUser
     * 
     * @param loginName
     * @return
     */
    public SysUser query(String loginName);

    /**
     * 根据loginId和password修改SysUser密码
     * 
     * @param loginName
     * @param password
     * @return
     */
    public int queryEditPwd(String loginName, String password);

    public String queryNoAllotRoleJson();
}
