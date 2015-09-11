package org.edwin.faceplusplus.api;

public enum FaceAttribute {
	GENDER("gender"), AGE("age"), RACE("race"), SMILING("smiling"), GLASS(
			"glass"), POSE("pose");

	private String value;

	FaceAttribute(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
