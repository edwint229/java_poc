package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class Position implements Serializable {
	private static final long serialVersionUID = -2634273913347084805L;
	private double width;
	private double height;
	private Coordinate center;
	private Coordinate eyeLeft;
	private Coordinate eyeRight;
	private Coordinate mouthLeft;
	private Coordinate mouthRight;
	private Coordinate nose;

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public Coordinate getCenter() {
		return center;
	}

	public void setCenter(Coordinate center) {
		this.center = center;
	}

	public Coordinate getEyeLeft() {
		return eyeLeft;
	}

	public void setEyeLeft(Coordinate eyeLeft) {
		this.eyeLeft = eyeLeft;
	}

	public Coordinate getEyeRight() {
		return eyeRight;
	}

	public void setEyeRight(Coordinate eyeRight) {
		this.eyeRight = eyeRight;
	}

	public Coordinate getMouthLeft() {
		return mouthLeft;
	}

	public void setMouthLeft(Coordinate mouthLeft) {
		this.mouthLeft = mouthLeft;
	}

	public Coordinate getMouthRight() {
		return mouthRight;
	}

	public void setMouthRight(Coordinate mouthRight) {
		this.mouthRight = mouthRight;
	}

	public Coordinate getNose() {
		return nose;
	}

	public void setNose(Coordinate nose) {
		this.nose = nose;
	}

	@Override
	public String toString() {
		return "Position [width=" + width + ", height=" + height + ", center="
				+ center + ", eyeLeft=" + eyeLeft + ", eyeRight=" + eyeRight
				+ ", mouthLeft=" + mouthLeft + ", mouthRight=" + mouthRight
				+ ", nose=" + nose + "]";
	}

}
