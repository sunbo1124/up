package com.ericsson.csp.tsc.admin.dao.work.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ericsson.csp.tsc.admin.dao.base.BaseDao;
import com.ericsson.csp.tsc.admin.dao.entity.HealthRecord;
import com.ericsson.csp.tsc.admin.dao.work.HealthRecordDao;

@Repository("healthRecordDao")
public class HealthRecordDaoImpl extends BaseDao implements HealthRecordDao {

    /**
     * 查询SysRole所有记录
     * 
     * @return
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<HealthRecord> query() {
        Criteria cri = getCurrentSession().createCriteria(HealthRecord.class);
        cri.addOrder(Order.asc("id"));
        return cri.list();
    }

    /**
     * 通过id查询HealthRecord
     * 
     * @param id
     * @return
     */
    @Override
    public HealthRecord query(int id) {
        Criteria cri = getCurrentSession().createCriteria(HealthRecord.class);
        cri.add(Restrictions.eq("id", id));
        return (HealthRecord) cri.uniqueResult();
    }

    /**
     * 将role对象保存到HealthRecord
     * 
     * @param healthRecord
     * @return
     */
    @Override
    public int save(HealthRecord healthRecord) {
        saveObj(healthRecord);
        return healthRecord.getId();
    }

    /**
     * 修改HealthRecord
     * 
     * @param healthRecord
     * @return
     */
    @Override
    public int edit(HealthRecord healthRecord) {
        updateObj(healthRecord);
        return healthRecord.getId();
    }

    /**
     * 根据id删除HealthRecord
     * 
     * @param id
     * @return
     */
    @Override
    public int del(int id) {
        String hql = "delete from HealthRecord s where s.id=?";
        return getCurrentSession().createQuery(hql).setInteger(0, id).executeUpdate();
    }

    /**
     * 查询HealthRecord记录数
     * 
     * @return
     */
    @Override
    public Integer queryCount() {
        final Query query = getCurrentSession().createQuery("select count(id) from HealthRecord where 1 = 1");
        return ((Number) query.uniqueResult()).intValue();
    }

}
