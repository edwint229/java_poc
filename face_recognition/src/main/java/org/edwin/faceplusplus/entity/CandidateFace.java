package org.edwin.faceplusplus.entity;

import java.io.Serializable;
import java.util.List;

public class CandidateFace implements Serializable {

    private static final long serialVersionUID = -1888960039717172178L;

    private List<Candidate> candidate;

    private String faceId;

    private Position position;

    public List<Candidate> getCandidate() {
        return candidate;
    }

    public void setCandidate(List<Candidate> candidate) {
        this.candidate = candidate;
    }

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

    @Override
    public String toString() {
        return "CandidateFace [candidate=" + candidate + ", faceId=" + faceId + ", position=" + position + "]";
    }

}
