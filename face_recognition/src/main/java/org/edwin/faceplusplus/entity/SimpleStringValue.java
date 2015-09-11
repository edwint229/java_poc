package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class SimpleStringValue implements Serializable {
	private static final long serialVersionUID = 9065302623159241734L;
	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SimpleValue [value=" + value + "]";
	}

}
