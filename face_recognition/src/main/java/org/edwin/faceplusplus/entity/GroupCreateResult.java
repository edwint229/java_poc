package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class GroupCreateResult implements Serializable {

    private static final long serialVersionUID = -2070668964631324009L;

    private int addedPerson;

    private String groupId;

    private String groupName;

    private String tag;

    public int getAddedPerson() {
        return addedPerson;
    }

    public void setAddedPerson(int addedPerson) {
        this.addedPerson = addedPerson;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public String toString() {
        return "GroupCreateResult [addedPerson=" + addedPerson + ", groupId=" + groupId + ", groupName=" + groupName + ", tag="
                + tag + "]";
    }

}
