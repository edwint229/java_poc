package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class Face implements Serializable {
	private static final long serialVersionUID = 1035164295643711382L;
	private String faceId;
	private String tag;
	private Position position;
	private Attribute attribute;

	public String getFaceId() {
		return faceId;
	}

	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	@Override
	public String toString() {
		return "Face [faceId=" + faceId + ", position=" + position + ", tag="
				+ tag + ", attribute=" + attribute + "]";
	}

}
