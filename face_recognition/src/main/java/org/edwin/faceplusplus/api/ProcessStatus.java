package org.edwin.faceplusplus.api;

public enum ProcessStatus {
    INQUEUE("INQUEUE"), SUCC("SUCC"), FAILED("FAILED");

    private String value;

    ProcessStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
