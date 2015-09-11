package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class SessionInfoResult implements Serializable {

    private static final long serialVersionUID = -5897716807419785586L;

    private String sessionId;

    private int createTime;

    private int finishTime;

    private String status;

    private FaceDetectResult result;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getCreateTime() {
        return createTime;
    }

    public void setCreateTime(int createTime) {
        this.createTime = createTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public FaceDetectResult getResult() {
        return result;
    }

    public void setResult(FaceDetectResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "SessionInfoResult [sessionId=" + sessionId + ", createTime=" + createTime + ", finishTime=" + finishTime
                + ", status=" + status + ", result=" + result + "]";
    }

}
