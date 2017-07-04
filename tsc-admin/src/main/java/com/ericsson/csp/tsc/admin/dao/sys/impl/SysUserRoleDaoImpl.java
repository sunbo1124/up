package com.ericsson.csp.tsc.admin.dao.sys.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ericsson.csp.tsc.admin.dao.base.BaseDao;
import com.ericsson.csp.tsc.admin.dao.entity.SysUserRole;
import com.ericsson.csp.tsc.admin.dao.sys.SysUserRoleDao;

@Repository("sysUserRoleDao")
public class SysUserRoleDaoImpl extends BaseDao implements SysUserRoleDao {

    /**
     * 查询SysRole所有记录
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysUserRole> query() {
        Criteria cri = getCurrentSession().createCriteria(SysUserRole.class);
        cri.addOrder(Order.asc("id"));
        return cri.list();
    }

    /**
     * 通过id查询SysUserRole
     * 
     * @param id
     * @return
     */
    @Override
    public SysUserRole query(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysUserRole.class);
        cri.add(Restrictions.eq("id", id));
        return (SysUserRole) cri.uniqueResult();
    }

    /**
     * 将sysUserRole对象保存到SysUserRole
     * 
     * @param sysUserRole
     * @return
     */
    @Override
    public int save(SysUserRole sysUserRole) {
        saveObj(sysUserRole);
        return sysUserRole.getId();
    }

    /**
     * 修改SysUserRole
     * 
     * @param sysUserRole
     * @return
     */
    @Override
    public int edit(SysUserRole sysUserRole) {
        updateObj(sysUserRole);
        return sysUserRole.getId();
    }

    /**
     * 根据id删除SysRole
     * 
     * @param id
     * @return
     */
    @Override
    public int del(int id) {
        String hql = "delete from SysUserRole s where s.id=?";
        return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();
    }

    /**
     * 将userRoles保存到SysUserRole
     * 
     * @param userRoles
     */
    @Override
    public void save(List<SysUserRole> userRoles) {
        saveList(userRoles);
    }

    /**
     * 根据roleId查询SysUserRole
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysUserRole> queryByRoleId(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysUserRole.class);
        cri.add(Restrictions.eq("roleId", id));
        return cri.list();
    }

    /**
     * 根据UserId删除SysUserRole
     * 
     * @param id
     */
    @Override
    public void deleteByUserId(int id) {
        String hql = "delete from SysUserRole s where s.userId = ?";
        getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();

    }

    /**
     * 根据UserId查询SysUserRole
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysUserRole> queryByUserId(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysUserRole.class);
        cri.add(Restrictions.eq("userId", id));
        return cri.list();
    }

    /**
     * 查询sysUserRole记录数
     * 
     * @return
     */
    @Override
    public Integer queryCount() {
        final Query query = getCurrentSession().createQuery("select count(id) from SysUserRole where 1 = 1");
        return ((Number) query.uniqueResult()).intValue();
    }

}
