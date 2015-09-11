package org.edwin.faceplusplus.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class SimpleNumberValue implements Serializable {
	private static final long serialVersionUID = 7476923969780221221L;
	private BigDecimal value;

	public SimpleNumberValue() {
	}

	public SimpleNumberValue(String value) {
		this.value = new BigDecimal(value);
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "SimpleNumberValue [value=" + value + "]";
	}

}
