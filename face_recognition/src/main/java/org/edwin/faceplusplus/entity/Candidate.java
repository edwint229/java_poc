package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class Candidate implements Serializable {

    private static final long serialVersionUID = -5561223876610766093L;

    private double confidence;

    private String personId;

    private String personName;

    private String tag;

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "Candidate [confidence=" + confidence + ", personId=" + personId + ", personName=" + personName + ", tag=" + tag
                + "]";
    }

}
