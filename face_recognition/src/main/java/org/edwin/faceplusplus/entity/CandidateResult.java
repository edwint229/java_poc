package org.edwin.faceplusplus.entity;

import java.io.Serializable;
import java.util.List;

public class CandidateResult implements Serializable {

    private static final long serialVersionUID = -2626580633587696737L;

    private String sessionId;

    private List<CandidateFace> face;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public List<CandidateFace> getFace() {
        return face;
    }

    public void setFace(List<CandidateFace> face) {
        this.face = face;
    }

    @Override
    public String toString() {
        return "CandidateResult [sessionId=" + sessionId + ", face=" + face + "]";
    }

}
