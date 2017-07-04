package com.ericsson.csp.tsc.admin.dao.sys.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ericsson.csp.tsc.admin.dao.base.BaseDao;
import com.ericsson.csp.tsc.admin.dao.entity.SysMenu;
import com.ericsson.csp.tsc.admin.dao.entity.SysRole;
import com.ericsson.csp.tsc.admin.dao.sys.SysRoleDao;

@Repository("sysRoleDao")
public class SysRoleDaoImpl extends BaseDao implements SysRoleDao {

    /**
     * 查询SysRole所有记录
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysRole> query() {
        Criteria cri = getCurrentSession().createCriteria(SysRole.class);
        cri.addOrder(Order.asc("id"));
        return cri.list();
    }

    /**
     * 通过id查询SysRole
     * 
     * @param id
     * @return
     */
    @Override
    public SysRole query(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysRole.class);
        cri.add(Restrictions.eq("id", id));
        return (SysRole) cri.uniqueResult();
    }

    /**
     * 将role对象保存到SysRole
     * 
     * @param role
     * @return
     */
    @Override
    public int save(SysRole role) {
        saveObj(role);
        return role.getId();
    }

    /**
     * 修改SysRole
     * 
     * @param role
     * @return
     */
    @Override
    public int edit(SysRole role) {
        updateObj(role);
        return role.getId();
    }

    /**
     * 根据id删除SysRole
     * 
     * @param id
     * @return
     */
    @Override
    public int del(int id) {
        String hql = "delete from SysRole s where s.id=?";
        return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();
    }

    /**
     * 根据name，id查询SysRole
     * 
     * @param name
     * @param id
     * @return
     */
    @Override
    public SysRole query(String name, int id) {
        SysRole role = query(name);
        if (role != null && role.getId() == id) {
            return null;
        }
        return role;
    }

    /**
     * 根据name查询SysRole
     * 
     * @param name
     * @return
     */
    @Override
    public SysRole query(String name) {
        Criteria cri = getCurrentSession().createCriteria(SysRole.class);
        cri.add(Restrictions.eq("name", name));
        return (SysRole) cri.uniqueResult();
    }

    /**
     * 根据roleId查询SysMenu
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysMenu> queryRoleMenusByRoleId(int id) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select new SysMenu(sm.id,sm.pid,sm.name,sm.url,sm.orderNum,sm.type,sm.updateTime,sm.createTime) from SysRoleMenu srm,SysMenu sm where srm.menuId = sm.id and srm.roleId = ?");
        Query query = getCurrentSession().createQuery(sb.toString()).setInteger(0, id);
        return query.list();
    }

    /**
     * 查询sysrole记录数
     * 
     * @return
     */
    @Override
    public Integer queryCount() {
        final Query query = getCurrentSession().createQuery("select count(id) from SysRole where 1 = 1");
        return ((Number) query.uniqueResult()).intValue();
    }

}
