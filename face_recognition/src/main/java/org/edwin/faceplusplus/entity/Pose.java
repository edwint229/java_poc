package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class Pose implements Serializable {
	private static final long serialVersionUID = -2907283580844144811L;
	private SimpleNumberValue pitchAngle;
	private SimpleNumberValue rollAngle;
	private SimpleNumberValue yawAngle;

	public SimpleNumberValue getPitchAngle() {
		return pitchAngle;
	}

	public void setPitchAngle(SimpleNumberValue pitchAngle) {
		this.pitchAngle = pitchAngle;
	}

	public SimpleNumberValue getRollAngle() {
		return rollAngle;
	}

	public void setRollAngle(SimpleNumberValue rollAngle) {
		this.rollAngle = rollAngle;
	}

	public SimpleNumberValue getYawAngle() {
		return yawAngle;
	}

	public void setYawAngle(SimpleNumberValue yawAngle) {
		this.yawAngle = yawAngle;
	}

	@Override
	public String toString() {
		return "Pose [pitchAngle=" + pitchAngle + ", rollAngle=" + rollAngle
				+ ", yawAngle=" + yawAngle + "]";
	}

}
