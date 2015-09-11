package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class DeleteResult implements Serializable {

    private static final long serialVersionUID = 2975904163635526516L;

    private int deleted;

    private boolean success;

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "GroupDeleteResult [deleted=" + deleted + ", success=" + success + "]";
    }

}
