package com.ericsson.csp.tsc.admin.dao.sys.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ericsson.csp.tsc.admin.dao.base.BaseDao;
import com.ericsson.csp.tsc.admin.dao.entity.SysRoleMenu;
import com.ericsson.csp.tsc.admin.dao.sys.SysRoleMenuDao;

@Repository("roleMenuDao")
public class SysRoleMenuDaoImpl extends BaseDao implements SysRoleMenuDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysRoleMenuDaoImpl.class);

    /**
     * 通过roleId删除SysRoleMenu
     * 
     * @param id
     */
    @Override
    public void delByRoleId(int id) {
        String hql = "delete from SysRoleMenu s where s.roleId = ?";
        getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();

    }

    /**
     * 将roleMenus集合保存到SysRoleMenu
     * 
     * @param roleMenus
     */
    @Override
    public void save(List<SysRoleMenu> roleMenus) {
        saveList(roleMenus);

    }

    /**
     * 通过roleId查询SysRoleMenu
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysRoleMenu> queryByRoleId(int roleId) {
        Criteria cri = getCurrentSession().createCriteria(SysRoleMenu.class);
        cri.add(Restrictions.eq("roleId", roleId));
        return cri.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SysRoleMenu> queryByRoleIds(List<Integer> ids) {
        Criteria cri = getCurrentSession().createCriteria(SysRoleMenu.class);
        cri.add(Restrictions.in("roleId", ids));
        return cri.list();
    }

    /**
     * 通过roleId,MenuId删除SysRoleMenu
     * 
     * @param id
     */
    @Override
    public int delByRoleIdAndMenuId(int roleId, int menuId) {
        String hql = "delete from SysRoleMenu s where s.roleId = ? and s.menuId = ?";
        return getCurrentSession().createQuery(hql).setInteger(0, roleId).setInteger(1, menuId).executeUpdate();
    }

    /**
     * 将sysRoleMenu对象保存到SysRoleMenu
     * 
     * @param sysUser
     * @return
     */
    @Override
    public int save(final SysRoleMenu sysRoleMenu) {
        try {
            saveObj(sysRoleMenu);
        } catch (Exception e) {
            LOGGER.error("SysRoleMenuDaoImpl save failure", e);
        }
        int id = sysRoleMenu.getId();
        LOGGER.debug("saved sysRoleMenu id : {}", id);
        return id;
    }

}
