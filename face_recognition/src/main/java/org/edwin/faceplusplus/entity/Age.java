package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class Age implements Serializable {
	private static final long serialVersionUID = 8069666102358905723L;
	private int range;
	private int value;

	public Age() {

	}

	public Age(int value, int range) {
		this.value = value;
		this.range = range;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getMaxAge() {
		return value + range;
	}

	public int getMinAge() {
		return value - range;
	}

	@Override
	public String toString() {
		return "Age [range=" + range + ", value=" + value + "]";
	}

}
