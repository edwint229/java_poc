package org.edwin.vote.mvc.to;

import java.io.Serializable;
import java.util.Date;

public class UserTO implements Serializable {
	private static final long serialVersionUID = 5516078978076783435L;
	private String userId;
	private String password;
	private String role;
	private String name;
	private Date createdDt;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
