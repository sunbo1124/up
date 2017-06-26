package com.ericsson.csp.tsc.admin.service.sys.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.csp.tsc.admin.controller.pojo.AllotMenu;
import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.dao.entity.SysRoleMenu;
import com.ericsson.csp.tsc.admin.dao.sys.SysMenuDao;
import com.ericsson.csp.tsc.admin.dao.sys.SysRoleMenuDao;
import com.ericsson.csp.tsc.admin.service.sys.AllotMenuService;

@Service("allotMenuService")
@Transactional("transactionManager")
public class AllotMenuServiceImpl implements AllotMenuService {

    @Autowired
    SysMenuDao     sysMenuDao;

    @Autowired
    SysRoleMenuDao sysRoleMenuDao;

    /**
     * 根据id查询SysRole
     * 
     * @param id
     * @return
     */
    @Override
    public List<AllotMenu> query(int roleId, String locale) {
        List<AllotMenu> allotMenuList = new ArrayList<AllotMenu>();
        AllotMenu allotMenu = null;

        List<SysMenu> sysMenuList = sysMenuDao.query(locale);

        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuDao.queryByRoleId(roleId);

        if (sysRoleMenuList != null && !sysRoleMenuList.isEmpty()) {

            for (int i = 0; i < sysMenuList.size(); i++) {
                boolean flog = false;
                allotMenu = new AllotMenu();
                for (int j = 0; j < sysRoleMenuList.size(); j++) {
                    if (sysMenuList.get(i).getId() == 0 || sysMenuList.get(i).getId() == 10
                            || sysMenuList.get(i).getId() == sysRoleMenuList.get(j).getMenuId()) {
                        flog = true;
                        break;
                    }
                }
                allotMenu.setMenuId(sysMenuList.get(i).getId());
                allotMenu.setName(sysMenuList.get(i).getName());
                allotMenu.setPid(sysMenuList.get(i).getPid());
                if (flog) {
                    allotMenu.setCheck(true);

                } else {
                    allotMenu.setCheck(false);
                }
                allotMenuList.add(allotMenu);
            }
            return allotMenuList;
        }

        for (int k = 0; k < sysMenuList.size(); k++) {
            allotMenu = new AllotMenu();
            if (sysMenuList.get(k).getId() == 0 || sysMenuList.get(k).getId() == 10) {
                allotMenu.setCheck(true);
            } else {
                allotMenu.setCheck(false);
            }
            allotMenu.setMenuId(sysMenuList.get(k).getId());
            allotMenu.setName(sysMenuList.get(k).getName());
            allotMenu.setPid(sysMenuList.get(k).getPid());
            allotMenuList.add(allotMenu);
        }

        return allotMenuList;
    }

    @Override
    public int remove(int roleId, int menuId) {
        return sysRoleMenuDao.delByRoleIdAndMenuId(roleId, menuId);
    }

    @Override
    public int add(int roleId, int menuId) {
        SysRoleMenu roleMenus = new SysRoleMenu();
        roleMenus.setCreateTime(new Date());
        roleMenus.setUpdateTime(new Date());
        roleMenus.setRoleId(roleId);
        roleMenus.setMenuId(menuId);
        return sysRoleMenuDao.save(roleMenus);
    }
}
