package org.edwin.faceplusplus.api;

public enum DetectMode {
	NORMAL("normal"), ONE_FACE("oneface");

	private String value;

	DetectMode(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
