package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class SessionIdResult implements Serializable {

    private static final long serialVersionUID = -5897716807419785586L;

    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "SessionIdResult [sessionId=" + sessionId + "]";
    }

}
