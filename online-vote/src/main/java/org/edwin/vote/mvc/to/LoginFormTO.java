package org.edwin.vote.mvc.to;

public class LoginFormTO {
	private String userId;
	private String password;
	private boolean checked;

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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	@Override
	public String toString() {
		return "LoginFormTO [userId=" + userId + ", password=[PASSWORD], checked=" + checked + "]";
	}

}
