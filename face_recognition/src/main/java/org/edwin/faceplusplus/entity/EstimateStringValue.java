package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class EstimateStringValue implements Serializable {
	private static final long serialVersionUID = 3282065280648067001L;
	private double confidence;
	private String value;

	public EstimateStringValue() {

	}

	public EstimateStringValue(double confidence, String value) {
		super();
		this.confidence = confidence;
		this.value = value;
	}

	public double getConfidence() {
		return confidence;
	}

	public void setConfidence(double confidence) {
		this.confidence = confidence;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "EstimateStringValue [confidence=" + confidence + ", value="
				+ value + "]";
	}

}
