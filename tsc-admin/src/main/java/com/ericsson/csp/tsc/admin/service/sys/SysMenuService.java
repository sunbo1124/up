package com.ericsson.csp.tsc.admin.service.sys;

import java.util.List;

import com.ericsson.csp.tsc.admin.controller.pojo.tool.BootstrapTreeviewPojo;
import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.util.Pagination;

public interface SysMenuService {
    /**
     * 查询当前用户权限下的树形菜单
     * 
     * @param loginName
     *            用户登陆名
     * @param filterType
     *            如果为null,不进行过滤,1是菜单，2是按钮
     * @param locale
     *            CN显示中文 US显示英文
     * @return
     */
    public List<SysMenu> query(String loginId, Integer filterType, String locale);

    /**
     * 查询所有的Boostrap-treeview数据类型的树形菜单
     * 
     * @param loginName
     *            用户登陆名
     * @param locale
     *            CN显示中文 US显示英文
     * @return
     */
    public List<BootstrapTreeviewPojo> queryBootstrapTreeviewPojos(String loginName, String locale);

    /**
     * 查询所有的非树形菜单
     * 
     * @return
     */
    public String queryNotTreeMenus(Pagination<SysMenu> pagination);

    /**
     * 查询SysMenu的所有信息
     * 
     * @return
     */
    public List<SysMenu> query();

    /**
     * 查询SysMenu的所有信息
     * 
     * @return
     */
    public List<SysMenu> query(String locale);

    /**
     * 查询菜单信息
     * 
     * @param pagination
     * @return
     */
    public String queryTableJson(Pagination<SysMenu> pagination);

    /**
     * 根据id查询SysMenu
     * 
     * @param id
     * @return
     */
    public SysMenu query(int id);

    /**
     * 修改SysMenu
     * 
     * @param menu
     * @param id
     */
    public void edit(SysMenu menu);

    /**
     * 将sysMenu保存到SysMenu
     * 
     * @param menu
     */
    public void save(SysMenu menu);

    /**
     * 根据id删除SysMenu
     * 
     * @param id
     */
    public void del(int id);

    /**
     * 查询菜单信息
     * 
     * @param pagination
     * @return
     */
    public String queryTableJson(Pagination<SysMenu> pagination, String locale);
}
