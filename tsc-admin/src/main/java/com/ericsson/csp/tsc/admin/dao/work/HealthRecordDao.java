package com.ericsson.csp.tsc.admin.dao.work;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.HealthRecord;

public interface HealthRecordDao {
    
    /**
     * 查询HealthRecord所有记录
     * @return
     */
    public List<HealthRecord> query();

    /**
     * 通过id查询HealthRecord
     * @param id
     * @return
     */
    public HealthRecord query(int id);
    
    /**
     * 将role对象保存到HealthRecord
     * @param role
     * @return
     */
    public int save(HealthRecord role);
    
    /**
     * 修改HealthRecord
     * @param role
     * @return
     */
    public int edit(HealthRecord role);
    
    /**
     * 根据id删除HealthRecord
     * @param id
     * @return
     */
    public int del(int id);
   
    /**
     * 查询HealthRecord记录数
     * @return
     */
    public Integer queryCount();
    



}
