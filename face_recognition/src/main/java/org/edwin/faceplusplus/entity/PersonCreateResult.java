package org.edwin.faceplusplus.entity;

import java.io.Serializable;

public class PersonCreateResult implements Serializable {

    private static final long serialVersionUID = 8252872497663105410L;

    private int addedFace;

    private int addedGroup;

    private String personId;

    private String personName;

    private String tag;

    public int getAddedFace() {
        return addedFace;
    }

    public void setAddedFace(int addedFace) {
        this.addedFace = addedFace;
    }

    public int getAddedGroup() {
        return addedGroup;
    }

    public void setAddedGroup(int addedGroup) {
        this.addedGroup = addedGroup;
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
        return "PersonCreateResult [addedFace=" + addedFace + ", addedGroup=" + addedGroup + ", personId=" + personId
                + ", personName=" + personName + ", tag=" + tag + "]";
    }

}
