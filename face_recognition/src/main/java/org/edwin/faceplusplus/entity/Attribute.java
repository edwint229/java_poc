package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class Attribute implements Serializable {
	private static final long serialVersionUID = 4119668798194801749L;
	private Age age;
	private EstimateStringValue gender;
	private EstimateStringValue glass;
	private EstimateStringValue race;
	private SimpleNumberValue smiling;
	private Pose pose;

	public Age getAge() {
		return age;
	}

	public void setAge(Age age) {
		this.age = age;
	}

	public EstimateStringValue getGender() {
		return gender;
	}

	public void setGender(EstimateStringValue gender) {
		this.gender = gender;
	}

	public EstimateStringValue getGlass() {
		return glass;
	}

	public void setGlass(EstimateStringValue glass) {
		this.glass = glass;
	}

	public EstimateStringValue getRace() {
		return race;
	}

	public void setRace(EstimateStringValue race) {
		this.race = race;
	}

	public SimpleNumberValue getSmiling() {
		return smiling;
	}

	public void setSmiling(SimpleNumberValue smiling) {
		this.smiling = smiling;
	}

	public Pose getPose() {
		return pose;
	}

	public void setPose(Pose pose) {
		this.pose = pose;
	}

	@Override
	public String toString() {
		return "Attribute [age=" + age + ", gender=" + gender + ", glass="
				+ glass + ", race=" + race + ", smiling=" + smiling + ", pose="
				+ pose + "]";
	}

}
