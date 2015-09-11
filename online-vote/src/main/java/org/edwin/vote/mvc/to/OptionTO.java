package org.edwin.vote.mvc.to;

public class OptionTO {

	private Integer id;

	private Integer selectId;

	private String fullName;

	private String shortName;

	private boolean isVoted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSelectId() {
		return selectId;
	}

	public void setSelectId(Integer selectId) {
		this.selectId = selectId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public boolean isVoted() {
		return isVoted;
	}

	public void setVoted(boolean isVoted) {
		this.isVoted = isVoted;
	}

}
