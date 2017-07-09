package com.ericsson.csp.tsc.admin.service.work;

import java.util.List;

import com.ericsson.csp.tsc.admin.dao.entity.HealthRecord;
import com.ericsson.csp.tsc.admin.dao.entity.SysRole;
import com.ericsson.csp.tsc.admin.util.DataTableFormate;
import com.ericsson.csp.tsc.admin.util.Pagination;

public interface HealthRecordService {

    /**
     * 将role对象保存到HealthRecord
     * 
     * @param role
     */
    public void save(HealthRecord role);

    /**
     * 根据id查询HealthRecord
     * 
     * @param id
     * @return
     */
    public HealthRecord query(int id);

    /**
     * 查询HealthRecord的所有信息
     * 
     * @return
     */
    public List<HealthRecord> query();

    /**
     * 修改HealthRecord
     * 
     * @param role
     * @param id
     */
    public void edit(HealthRecord role, int id);

    /**
     * 根据id删除HealthRecord
     * 
     * @param id
     */
    public void del(int id);
    
    /**
     * 查询角色信息
     * 
     * @param pagination
     * @return
     */
    public String queryTableJson(Pagination<HealthRecord> pagination);

  
    
}
