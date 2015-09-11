package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class GroupAddPersonResult implements Serializable {

    private static final long serialVersionUID = 6151580289735617465L;

    private int added;

    private boolean success;

    public int getAdded() {
        return added;
    }

    public void setAdded(int added) {
        this.added = added;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "GroupAddPersonResult [added=" + added + ", success=" + success + "]";
    }

}
