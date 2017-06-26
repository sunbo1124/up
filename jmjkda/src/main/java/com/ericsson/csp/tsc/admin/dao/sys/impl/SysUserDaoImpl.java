package com.ericsson.csp.tsc.admin.dao.sys.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ericsson.csp.tsc.admin.dao.base.BaseDao;
import com.ericsson.csp.tsc.admin.dao.entity.SysRole;
import com.ericsson.csp.tsc.admin.dao.entity.SysUser;
import com.ericsson.csp.tsc.admin.dao.sys.SysUserDao;

@Repository("sysUserDao")
public class SysUserDaoImpl extends BaseDao implements SysUserDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(SysUserDaoImpl.class);

    /**
     * 将sysUser对象保存到SysUser
     * 
     * @param sysUser
     * @return
     */
    @Override
    public int save(final SysUser sysUser) {
        try {
            saveObj(sysUser);
        } catch (Exception e) {
            LOGGER.error("SysUserDaoImpl save failure", e);
        }
        int id = sysUser.getId();
        LOGGER.debug("saved user id : {}", id);
        return id;
    }

    /**
     * 查询SysUser所有信息
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysUser> query() {
        Criteria cri = getCurrentSession().createCriteria(SysUser.class);
        cri.addOrder(Order.asc("id"));
        return cri.list();
    }

    /**
     * 查询没有分配权限的SysUser所有信息
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysUser> queryAllotRole(Integer[] userId) {
        Criteria cri = getCurrentSession().createCriteria(SysUser.class);
        cri.add(Restrictions.in("id", userId));
        cri.addOrder(Order.asc("id"));
        return cri.list();
    }

    /**
     * 根据id查询SysUser
     * 
     * @param id
     * @return
     */
    @Override
    public SysUser query(int id) {
        Criteria cri = getCurrentSession().createCriteria(SysUser.class);
        cri.add(Restrictions.eq("id", id));
        return (SysUser) cri.uniqueResult();
    }

    /**
     * 修改SysUser
     * 
     * @param user
     * @return
     */
    @Override
    public Integer edit(SysUser user) {
        updateObj(user);
        Integer id = user.getId();
        LOGGER.debug("edit user id : {}", id);
        return id;
    }

    /**
     * 根据id删除SysUser
     * 
     * @param id
     * @return
     */
    @Override
    public int del(int id) {
        String hql = "delete from SysUser s where s.id = ?";
        return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();

    }

    /**
     * 根据loginName查询SysUser
     * 
     * @param loginName
     * @return
     */
    @Override
    public SysUser query(String loginName) {
        Criteria cri = getCurrentSession().createCriteria(SysUser.class);
        cri.add(Restrictions.eq("loginName", loginName));
        return (SysUser) cri.uniqueResult();
    }

    /**
     * 根据loginName，id查询SysUser
     * 
     * @param loginName
     * @param id
     * @return
     */
    @Override
    public SysUser query(String loginName, int id) {
        SysUser user = query(loginName);
        if (user != null && user.getId() == id) {
            return null;
        }
        return user;
    }

    /**
     * 根据userId查询SysRole
     * 
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<SysRole> queryRolesByUserId(int id) {
        final StringBuilder sb = new StringBuilder();
        sb.append("select new SysRole(sr.id,sr.name,sr.isBuiltin,sr.updateTime,sr.createTime) from SysUserRole sur,SysRole sr where sur.roleId = sr.id and sur.userId = ?");
        final Query query = getCurrentSession().createQuery(sb.toString()).setInteger(0, id);

        return query.list();
    }

    @Override
    public Integer queryCount() {
        final Query query = getCurrentSession().createQuery("select count(id) from SysUser where 1 = 1");
        return ((Number) query.uniqueResult()).intValue();
    }

}
