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
import javax.persistence.Transient;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.ericsson.csp.tsc.admin.util.PaginationRow;

/**
 * SysRole generated by hbm2java
 */
@Entity
@Table(name = "health_record")
public class HealthRecord implements java.io.Serializable, PaginationRow {

	private static final long serialVersionUID = 2433049450911879851L;

	private Integer id;

	private String name;

	private String sex;

	private String address;

	private String code;

	private String permanentType;

	private String phone;

	private String contact;

	private String contactPhone;

	private String payType;

	private String bloodType;

	private String allergicHis;

	private String medicalHis;

	private Date physicalTime;

	private Date updateTime;

	private Date createTime;

	public HealthRecord() {
	}

	public HealthRecord(Integer id, String name, String sex, String address,
			String code, String permanentType, String phone, String contact,
			String contactPhone, String payType, String bloodType,
			String allergicHis, String medicalHis, Date physicalTime,
			Date updateTime, Date createTime) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.address = address;
		this.code = code;
		this.permanentType = permanentType;
		this.phone = phone;
		this.contact = contact;
		this.contactPhone = contactPhone;
		this.payType = payType;
		this.bloodType = bloodType;
		this.allergicHis = allergicHis;
		this.medicalHis = medicalHis;
		this.physicalTime = physicalTime;
		this.updateTime = updateTime;
		this.createTime = createTime;
	}

	@Id
	@SequenceGenerator(name = "geely_sequences", sequenceName = "geely_sequences", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "geely_sequences")
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false, unique = true, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "sex")
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Column(name = "address")
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Column(name = "permanent_type")
	public String getPermanentType() {
		return permanentType;
	}

	public void setPermanentType(String permanentType) {
		this.permanentType = permanentType;
	}

	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "contact")
	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Column(name = "contact_phone")
	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	@Column(name = "pay_type")
	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	@Column(name = "blood_type")
	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	@Column(name = "allergic_his")
	public String getAllergicHis() {
		return allergicHis;
	}

	public void setAllergicHis(String allergicHis) {
		this.allergicHis = allergicHis;
	}

	@Column(name = "medical_his")
	public String getMedicalHis() {
		return medicalHis;
	}

	public void setMedicalHis(String medicalHis) {
		this.medicalHis = medicalHis;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "physical_time", nullable = false, length = 22)
	public Date getPhysicalTime() {
		return physicalTime;
	}

	public void setPhysicalTime(Date physicalTime) {
		this.physicalTime = physicalTime;
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

	@JSONField(serialize = false)
	@Override
	@Transient
	public SimplePropertyPreFilter fetchSimplePropertyPreFilter() {
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(
				HealthRecord.class, "id", "name", "sex", "address", "code", "permanentType", "phone", "contact", "contactPhone", "payType", "bloodType", "allergicHis", "medicalHis", "physicalTime","updateTime",
				"createTime");
		return filter;
	}
	




}
