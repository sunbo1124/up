package com.ericsson.csp.tsc.admin.service.sys.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.csp.tsc.admin.controller.pojo.tool.BootstrapTreeviewPojo;
import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.dao.entity.SysRoleMenu;
import com.ericsson.csp.tsc.admin.dao.entity.SysUser;
import com.ericsson.csp.tsc.admin.dao.entity.SysUserRole;
import com.ericsson.csp.tsc.admin.dao.sys.SysMenuDao;
import com.ericsson.csp.tsc.admin.dao.sys.SysRoleDao;
import com.ericsson.csp.tsc.admin.dao.sys.SysRoleMenuDao;
import com.ericsson.csp.tsc.admin.dao.sys.SysUserDao;
import com.ericsson.csp.tsc.admin.dao.sys.SysUserRoleDao;
import com.ericsson.csp.tsc.admin.service.sys.SysMenuService;
import com.ericsson.csp.tsc.admin.util.DataTableFormate;
import com.ericsson.csp.tsc.admin.util.Pagination;
import com.ericsson.csp.tsc.admin.util.SysConstant;

@Service("sysMenuService")
@Transactional("transactionManager")
public class SysMenuServiceImpl implements SysMenuService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SysMenuServiceImpl.class);

    @Autowired
    SysMenuDao                  sysMenuDao;

    @Autowired
    SysUserDao                  sysUserDao;

    @Autowired
    SysUserRoleDao              sysUserRoleDao;

    @Autowired
    SysRoleDao                  sysRoleDao;

    @Autowired
    SysRoleMenuDao              sysRoleMenuDao;

    @Override
    public List<SysMenu> query(String loginId, Integer filterType, String locale) {
        List<SysMenu> beanList = queryByLoginId(loginId, locale);
        if (filterType != null) {
            beanList = filterMenuByType(beanList, filterType);
        }
        List<SysMenu> list = initIdxMenuTree(beanList, 0);
        return list;
    }

    /**
     * 查询SysMenu的所有信息
     * 
     * @return
     */
    @Override
    public List<SysMenu> query() {
        List<SysMenu> list = sysMenuDao.query();
        return list;
    }

    /**
     * 查询locale的SysMenu
     * 
     * @return
     */
    @Override
    public List<SysMenu> query(String locale) {
        List<SysMenu> list = new ArrayList<SysMenu>();
        if (!StringUtils.isEmpty(locale)) {
            list = sysMenuDao.query(locale);
            return list;
        }
        list = sysMenuDao.query();
        return list;
    }

    /*
     * 递归算法
     */
    private List<SysMenu> initIdxMenuTree(List<SysMenu> beanList, Integer pid) {
        List<SysMenu> list = new ArrayList<SysMenu>();
        for (SysMenu bean : beanList) {
            if (pid == bean.getPid()) {
                list.add(bean);
            }
        }
        if (list != null && !list.isEmpty()) {
            for (SysMenu bean : list) {
                List<SysMenu> childList = initIdxMenuTree(beanList, bean.getId());
                if (childList != null && !childList.isEmpty()) {
                    bean.setHasChild(true);
                    bean.setChildList(childList);
                }
            }
        }
        return list;
    }

    /**
     * 过滤menu对象，1是菜单，2是按钮
     * 
     * @param list
     * @param type
     * @return
     */
    private List<SysMenu> filterMenuByType(List<SysMenu> list, int type) {
        List<SysMenu> filtedMenus = new ArrayList<SysMenu>();
        for (SysMenu sysMenu : list) {
            if (sysMenu.getType() == type) {
                filtedMenus.add(sysMenu);
            }
        }

        return filtedMenus;
    }

    private List<SysMenu> queryByLoginId(String loginId, String locale) {
        List<SysMenu> sysMenuList = new ArrayList<SysMenu>();
        SysUser curUser = sysUserDao.query(loginId);
        if (curUser == null) {
            LOGGER.warn("User {} not config.", loginId);
            return sysMenuList;
        }
        List<SysUserRole> sysUserRoleList = sysUserRoleDao.queryByUserId(curUser.getId());
        if (sysUserRoleList == null || sysUserRoleList.isEmpty()) {
            LOGGER.warn("User {} not assign role.", loginId);
            return sysMenuList;
        }
        List<Integer> roleIdList = new ArrayList<Integer>();
        for (SysUserRole userRole : sysUserRoleList) {
            roleIdList.add(userRole.getRoleId());
        }
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuDao.queryByRoleIds(roleIdList);
        if (sysRoleMenuList == null || sysRoleMenuList.isEmpty()) {
            LOGGER.warn("User {} not assign menu.", loginId);
            return sysMenuList;
        }
        List<Integer> menuIds = new ArrayList<Integer>();
        for (SysRoleMenu roleMenu : sysRoleMenuList) {
            menuIds.add(roleMenu.getMenuId());
        }
        sysMenuList = sysMenuDao.query(menuIds, locale);
        return sysMenuList;
    }

    @Override
    public List<BootstrapTreeviewPojo> queryBootstrapTreeviewPojos(String loginName, String locale) {
        return _queryBootstrapTreeviewPojos(query(loginName, SysConstant.MENU_TYPE_MENU, locale));
    }

    private List<BootstrapTreeviewPojo> _queryBootstrapTreeviewPojos(List<SysMenu> menus) {
        List<BootstrapTreeviewPojo> pojos = new ArrayList<BootstrapTreeviewPojo>();
        if (menus == null)
            return pojos;
        for (SysMenu menu : menus) {
            BootstrapTreeviewPojo pojo = new BootstrapTreeviewPojo();
            pojo.setText(menu.getName());
            pojo.setHref(menu.getUrl());
            pojo.setIcon(menu.getIcon());
            List<SysMenu> children = menu.getChildList();
            if (children != null && !children.isEmpty()) {
                pojo.setChildren(_queryBootstrapTreeviewPojos(children));
                pojo.setHasChildren(true);
            }
            pojos.add(pojo);
        }
        return pojos;
    }

    @Override
    public String queryNotTreeMenus(Pagination<SysMenu> pagination) {
        String json = "";
        List<SysMenu> data = sysMenuDao.query(pagination);
        if (pagination == null) {
            json = DataTableFormate.formateTableJsonStrNoPagination(data, data.get(0).fetchSimplePropertyPreFilter());
        } else {
            pagination.setData(data);
            int total = sysMenuDao.queryCount();
            pagination.setRecordsTotal(total);
            pagination.setRecordsFiltered(total);
            json = pagination.parsePagination(false);
        }

        return json;
    }

    /**
     * 查询菜单信息
     * 
     * @param pagination
     * @return
     */
    @Override
    public String queryTableJson(Pagination<SysMenu> pagination) {
        List<SysMenu> list = query();
        if (pagination == null) {
            return DataTableFormate.formateTableJsonStrNoPagination(list, list.get(0).fetchSimplePropertyPreFilter());
        } else {
            pagination.setData(list);
            int total = sysMenuDao.queryCount();
            pagination.setRecordsTotal(total);
            pagination.setRecordsFiltered(total);
            return pagination.parsePagination(true);
        }
    }

    /**
     * 查询菜单信息
     * 
     * @param pagination
     * @return
     */
    @Override
    public String queryTableJson(Pagination<SysMenu> pagination, String locale) {
        List<SysMenu> list = query(locale);
        if (pagination == null) {
            return DataTableFormate.formateTableJsonStrNoPagination(list, list.get(0).fetchSimplePropertyPreFilter());
        } else {
            pagination.setData(list);
            int total = sysMenuDao.queryCount(locale);
            pagination.setRecordsTotal(total);
            pagination.setRecordsFiltered(total);
            return pagination.parsePagination(true);
        }
    }

    /**
     * 根据id查询SysRole
     * 
     * @param id
     * @return
     */
    @Override
    public SysMenu query(int id) {
        SysMenu menu = sysMenuDao.query(id);
        return menu;
    }

    /**
     * 修改SysMenu
     * 
     * @param role
     * @param id
     */
    @Override
    public void edit(SysMenu menu) {
        int menuId = sysMenuDao.edit(menu);
        LOGGER.debug("edit role id:{}", menuId);
    }

    /**
     * 将SysMenu保存到SysMenu
     * 
     * @param SysMenu
     */
    @Override
    public void save(SysMenu menu) {
        final Calendar cal = Calendar.getInstance();
        if (sysMenuDao.query(menu.getId()) == null) {
            menu.setCreateTime(cal.getTime());
            menu.setUpdateTime(cal.getTime());
            try {
                int id = sysMenuDao.save(menu);
                LOGGER.debug("saved menu id : {}", id);
            } catch (Exception e) {
                LOGGER.error("SysMenuServiceImpl save failure", e);
            }

        }
    }

    /**
     * 根据id删除SysMenu
     * 
     * @param id
     */
    @Override
    public void del(int id) {
        if (sysMenuDao.query(id) != null) {
            sysMenuDao.del(id);
        }
    }

    public SysMenuDao getSysMenuDao() {
        return sysMenuDao;
    }

    public void setSysMenuDao(SysMenuDao sysMenuDao) {
        this.sysMenuDao = sysMenuDao;
    }

    public SysUserDao getSysUserDao() {
        return sysUserDao;
    }

    public void setSysUserDao(SysUserDao sysUserDao) {
        this.sysUserDao = sysUserDao;
    }

    public SysUserRoleDao getSysUserRoleDao() {
        return sysUserRoleDao;
    }

    public void setSysUserRoleDao(SysUserRoleDao sysUserRoleDao) {
        this.sysUserRoleDao = sysUserRoleDao;
    }

    public SysRoleDao getSysRoleDao() {
        return sysRoleDao;
    }

    public void setSysRoleDao(SysRoleDao sysRoleDao) {
        this.sysRoleDao = sysRoleDao;
    }

    public SysRoleMenuDao getSysRoleMenuDao() {
        return sysRoleMenuDao;
    }

    public void setSysRoleMenuDao(SysRoleMenuDao sysRoleMenuDao) {
        this.sysRoleMenuDao = sysRoleMenuDao;
    }
}
