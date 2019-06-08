package com.laptrinhjavaweb.entity;

import java.sql.Timestamp;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

@Entity
public class BaseEntity {
	
	@Column(name = "id")
	private Long id;
	
	@Column(name = "createby")
	private String createBy;
	
	@Column(name = "modifieldby")
	private String modifieldBy;
	
	@Column(name = "createdate")
	private Timestamp createDate;
	
	@Column(name = "modifielddate")
	private Timestamp modifieldDate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getModifieldBy() {
		return modifieldBy;
	}
	public void setModifieldBy(String modifieldBy) {
		this.modifieldBy = modifieldBy;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	public Timestamp getModifieldDate() {
		return modifieldDate;
	}
	public void setModifieldDate(Timestamp modifieldDate) {
		this.modifieldDate = modifieldDate;
	}
	
	
}
