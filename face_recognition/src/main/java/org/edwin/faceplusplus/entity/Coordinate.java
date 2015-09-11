package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class Coordinate implements Serializable {
	private static final long serialVersionUID = -8286361915940229600L;
	private double x;
	private double y;

	public Coordinate() {
	}

	public Coordinate(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}

}
