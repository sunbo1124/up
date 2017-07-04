package com.ericsson.csp.tsc.admin.dao.sys.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ericsson.csp.tsc.admin.dao.base.BaseDao;
import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.dao.sys.SysMenuDao;
import com.ericsson.csp.tsc.admin.util.Pagination;

@Repository("sysMenuDao")
public class SysMenuDaoImpl extends BaseDao implements SysMenuDao {

    @SuppressWarnings("unchecked")
    @Override
    public List<SysMenu> query(Pagination<SysMenu> pagination) {
        Criteria cri = getCurrentSession().createCriteria(SysMenu.class);
        cri.addOrder(Order.asc("pid")).addOrder(Order.asc("id"));
        if (pagination != null) {
            cri.setFirstResult(pagination.getStart());
            cri.setMaxResults(pagination.getLength());
        }
        return cri.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SysMenu> query(List<Integer> menuIds, String locale) {
        Criteria cri = getCurrentSession().createCriteria(SysMenu.class);
        cri.add(Restrictions.in("id", menuIds));
        cri.add(Restrictions.eq("locale", locale));
        cri.addOrder(Order.asc("pid")).addOrder(Order.asc("orderNum"));
        return cri.list();
    }

    @Override
    public Integer queryCount() {
        final Query query = getCurrentSession().createQuery("select count(id) from SysMenu where 1 = 1");
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public Integer queryCount(String locale) {
        final Query query = getCurrentSession().createQuery(
                "select count(id) from SysMenu  where  locale = '" + locale + "'  ");
        return ((Number) query.uniqueResult()).intValue();
    }

    @Override
    public Integer save(SysMenu menu) {
        saveObj(menu);
        return menu.getId();
    }

    /**
     * 查询SysMenu所有记录
     * 
     * @return
     */
    @Override
    public List<SysMenu> query() {
        Criteria cri = getCurrentSession().createCriteria(SysMenu.class);
        cri.addOrder(Order.asc("id"));
        @SuppressWarnings("unchecked")
        List<SysMenu> list = cri.list();
        return list;
    }
    
    /**
     * 查询SysMenu根据locale
     * 
     * @return
     */
    @Override
    public List<SysMenu> query(String locale) {
        Criteria cri = getCurrentSession().createCriteria(SysMenu.class);
        cri.addOrder(Order.asc("id"));
        cri.add(Restrictions.eq("locale", locale));
        @SuppressWarnings("unchecked")
        List<SysMenu> list = cri.list();
        return list;
    }

    /**
     * 通过id查询SysMenu
     * 
     * @param id
     * @return
     */
    @Override
    public SysMenu query(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysMenu.class);
        cri.add(Restrictions.eq("id", id));
        return (SysMenu) cri.uniqueResult();
    }

    /**
     * 修改SysMenu
     * 
     * @param menu
     * @return
     */
    @Override
    public int edit(SysMenu menu) {
        updateObj(menu);
        return menu.getId();
    }

    /**
     * 根据id删除SysMenu
     * 
     * @param id
     * @return
     */
    @Override
    public int del(int id) {
        String hql = "delete from SysMenu s where s.id=?";
        return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();
    }

}
