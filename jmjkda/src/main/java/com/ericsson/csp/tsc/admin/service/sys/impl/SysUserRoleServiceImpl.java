package com.ericsson.csp.tsc.admin.service.sys.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ericsson.csp.tsc.admin.dao.entity.SysUserRole;
import com.ericsson.csp.tsc.admin.dao.sys.SysUserRoleDao;
import com.ericsson.csp.tsc.admin.service.sys.SysUserRoleService;
import com.ericsson.csp.tsc.admin.util.DataTableFormate;
import com.ericsson.csp.tsc.admin.util.Pagination;

@Service("sysUserRoleService")
@Transactional("transactionManager")
public class SysUserRoleServiceImpl implements SysUserRoleService {
    private final static Logger LOGGER = LoggerFactory.getLogger(SysUserRoleServiceImpl.class);

    @Autowired
    SysUserRoleDao              sysUserRoleDao;

    /**
     * 将role对象保存到SysUserRole
     * 
     * @param role
     */
    @Override
    public void save(SysUserRole sysUserRole) {
        if (sysUserRole != null) {
            sysUserRole.setCreateTime(new Date());
            sysUserRole.setUpdateTime(new Date());
        }
        sysUserRoleDao.save(sysUserRole);
    }

    /**
     * 根据id查询SysUserRole
     * 
     * @param id
     * @return
     */
    @Override
    public SysUserRole query(int id) {
        SysUserRole role = sysUserRoleDao.query(id);
        return role;
    }

    /**
     * 查询SysUserRole的所有信息
     * 
     * @return
     */
    @Override
    public List<SysUserRole> query() {
        List<SysUserRole> list = sysUserRoleDao.query();
        return list;
    }

    /**
     * 修改SysUserRole
     * 
     * @param role
     * @param id
     */
    @Override
    public void edit(SysUserRole role, int id) {
        final Calendar cal = Calendar.getInstance();
        role.setCreateTime(cal.getTime());
        role.setUpdateTime(cal.getTime());
        int roleId = sysUserRoleDao.edit(role);
        LOGGER.debug("edit sysUserRole id:{}", roleId);
    }

    /**
     * 根据id删除SysUserRole
     * 
     * @param id
     */
    @Override
    public void del(int id) {
        sysUserRoleDao.del(id);
    }

    /**
     * 查询角色信息
     * 
     * @param pagination
     * @return
     */
    @Override
    public String queryTableJson(Pagination<SysUserRole> pagination) {
        List<SysUserRole> list = sysUserRoleDao.query();
        if (pagination == null) {
            return DataTableFormate.formateTableJsonStrNoPagination(list, list.get(0).fetchSimplePropertyPreFilter());
        } else {
            pagination.setData(list);
            int total = sysUserRoleDao.queryCount();
            pagination.setRecordsTotal(total);
            pagination.setRecordsFiltered(total);
            return pagination.parsePagination(true);
        }
    }

}
