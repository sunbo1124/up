package com.ericsson.csp.tsc.admin.service.work.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.csp.tsc.admin.dao.entity.HealthRecord;
import com.ericsson.csp.tsc.admin.dao.entity.SysRole;
import com.ericsson.csp.tsc.admin.dao.work.HealthRecordDao;
import com.ericsson.csp.tsc.admin.service.work.HealthRecordService;
import com.ericsson.csp.tsc.admin.util.DataTableFormate;
import com.ericsson.csp.tsc.admin.util.Pagination;

@Service("healthRecordService")
@Transactional("transactionManager")
public class HealthRecordServiceImpl implements HealthRecordService {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(HealthRecordServiceImpl.class);

	@Autowired
	private HealthRecordDao healthRecordDao;

	/**
	 * 将role对象保存到HealthRecord
	 * 
	 * @param role
	 */
	@Override
	public void save(HealthRecord healthRecord) {
		int id = healthRecordDao.save(healthRecord);
		LOGGER.debug("save healthRecord id:{}", id);
	}

	/**
	 * 根据id查询HealthRecord
	 * 
	 * @param id
	 * @return
	 */
	@Override
	public HealthRecord query(int id) {
		HealthRecord healthRecord = healthRecordDao.query(id);
		return healthRecord;
	}

	/**
	 * 查询HealthRecord的所有信息
	 * 
	 * @return
	 */
	@Override
	public List<HealthRecord> query() {
		List<HealthRecord> list = healthRecordDao.query();
		return list;
	}

	/**
	 * 修改HealthRecord
	 * 
	 * @param healthRecord
	 * @param id
	 */
	@Override
	public void edit(HealthRecord healthRecord, int id) {
		healthRecordDao.edit(healthRecord);
	}
	
	/**
     * 查询角色信息
     * 
     * @param pagination
     * @return
     */
    @Override
    public String queryTableJson(Pagination<HealthRecord> pagination) {
        List<HealthRecord> list = query();
        if (pagination == null) {
            return DataTableFormate.formateTableJsonStrNoPagination(list, list.get(0).fetchSimplePropertyPreFilter());
        } else {
            pagination.setData(list);
            int total = healthRecordDao.queryCount();
            pagination.setRecordsTotal(total);
            pagination.setRecordsFiltered(total);
            return pagination.parsePagination(true);
        }
    }

	/**
	 * 根据id删除HealthRecord
	 * 
	 * @param id
	 */
	@Override
	public void del(int id) {
		healthRecordDao.del(id);
	}

	public HealthRecordDao getHealthRecordDao() {
		return healthRecordDao;
	}

	public void setHealthRecordDao(HealthRecordDao healthRecordDao) {
		this.healthRecordDao = healthRecordDao;
	}

}
