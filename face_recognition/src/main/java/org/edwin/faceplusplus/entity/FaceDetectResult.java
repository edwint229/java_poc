package org.edwin.faceplusplus.entity;

import java.io.Serializable;
import java.util.List;

public class FaceDetectResult implements Serializable {
	private static final long serialVersionUID = -4790173012882019439L;
	private String sessionId;
	private List<Face> face;
	private String imgId;
	private double imgWidth;
	private double imgHeight;
	private String url;

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public List<Face> getFace() {
		return face;
	}

	public void setFace(List<Face> face) {
		this.face = face;
	}

	public String getImgId() {
		return imgId;
	}

	public void setImgId(String imgId) {
		this.imgId = imgId;
	}

	public double getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(double imgWidth) {
		this.imgWidth = imgWidth;
	}

	public double getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(double imgHeight) {
		this.imgHeight = imgHeight;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "FaceDetectResult [sessionId=" + sessionId + ", face=" + face
				+ ", imgId=" + imgId + ", imgWidth=" + imgWidth
				+ ", imgHeight=" + imgHeight + ", url=" + url + "]";
	}

}
