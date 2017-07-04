package com.ericsson.csp.tsc.admin.dao.base;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class BaseDao {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Autowired
    private SessionFactory sessionFactory;

    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public <T> List<T> getObjectList(final Class<T> dbeanType, final String sql) {
        final List<T> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(dbeanType));
        return list;
    }

    public <T> List<T> getObjectList(final Class<T> dbeanType, final String sql, final Object[] args) {
        final List<T> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(dbeanType), args);
        return list;
    }

    public void saveOrUpdateObj(final Object obj) {
        getCurrentSession().saveOrUpdate(obj);
    }

    public void saveObj(final Object obj) {
    	getCurrentSession().save(obj);
    }

    public void updateObj(final Object obj) {
    	getCurrentSession().merge(obj);
    }

    public <T> void saveOrUpdateSet(final Set<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.saveOrUpdate(bean);
        }
    }

    public <T> void saveSet(final Set<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.save(bean);
        }
    }

    public <T> void updateSet(final Set<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.update(bean);
        }
    }

    public <T> void saveOrUpdateList(final List<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.saveOrUpdate(bean);
        }
    }

    public <T> void saveList(final List<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.save(bean);
        }
    }

    public <T> void updateList(final List<T> objs) {
        final Session session = getCurrentSession();
        for (final T bean : objs) {
            session.update(bean);
        }
    }

    public void setJdbcTemplate(final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setSessionFactory(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
