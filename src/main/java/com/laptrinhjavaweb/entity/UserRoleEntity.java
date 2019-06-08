package com.laptrinhjavaweb.entity;

import com.laptrinhjavaweb.annotation.Column;
import com.laptrinhjavaweb.annotation.Entity;

@Entity
public class UserRoleEntity extends BaseEntity{
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "role_id")
	private String roleId;
}
