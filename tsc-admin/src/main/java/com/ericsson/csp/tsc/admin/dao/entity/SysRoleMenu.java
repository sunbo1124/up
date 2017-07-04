package com.ericsson.csp.tsc.admin.dao.entity;

// Generated 2015-10-13 17:16:11 by Hibernate Tools 4.0.0

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * SysRoleMenu generated by hbm2java
 */
@Entity
@Table(name = "sys_role_menu", schema = "public")
public class SysRoleMenu implements java.io.Serializable {

    private static final long serialVersionUID = -7649429082841464062L;

    private int               id;

    private int               roleId;

    private int               menuId;

    private Date              updateTime;

    private Date              createTime;

    public SysRoleMenu() {
    }

    public SysRoleMenu(int id, int roleId, int menuId, Date updateTime, Date createTime) {
        this.id = id;
        this.roleId = roleId;
        this.menuId = menuId;
        this.updateTime = updateTime;
        this.createTime = createTime;

    }

    @Id
    @SequenceGenerator(name = "geely_sequences", sequenceName = "geely_sequences", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geely_sequences")
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "role_id", nullable = false)
    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Column(name = "menu_id", nullable = false)
    public int getMenuId() {
        return this.menuId;
    }

    public void setMenuId(int menuId) {
        this.menuId = menuId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_time", nullable = false, length = 22)
    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", nullable = false, length = 22)
    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
