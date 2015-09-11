package org.edwin.faceplusplus.client;

import java.io.File;
import java.util.ArrayList;

import org.apache.http.entity.mime.MultipartEntityBuilder;

/**
 * Build FacePlusPlus Post Parameters
 * {@code PostParamBuilder.create().setMode("oneface").setImg("file PATH").setTag("some message")}
 * 
 * @author edwin
 */
public class PostParamBuilder {

    private static MultipartEntityBuilder multiPartBuilder;

    private PostParamBuilder() {

    }

    public static PostParamBuilder create() {
        multiPartBuilder = MultipartEntityBuilder.create();
        return new PostParamBuilder();
    }

    public MultipartEntityBuilder getMultiPartBuilder() {
        return multiPartBuilder;
    }

    /**
     * async=true|false
     * 
     * @param flag
     * @return this
     */
    public PostParamBuilder setAsync(boolean flag) {
        multiPartBuilder.addTextBody("async", String.valueOf(flag));
        return this;
    }

    /**
     * url=...
     * 
     * @param url
     * @return this
     */
    public PostParamBuilder setUrl(String url) {
        multiPartBuilder.addTextBody("url", url);
        return this;
    }

    /**
     * attribute = gender,age,race,smiling,glass,pose
     * 
     * @param type
     * @return this
     */
    public PostParamBuilder setAttribute(String type) {
        multiPartBuilder.addTextBody("attribute", type);
        return this;
    }

    /**
     * tag=...
     * 
     * @param tag
     * @return this
     */
    public PostParamBuilder setTag(String tag) {
        multiPartBuilder.addTextBody("tag", tag);
        return this;
    }

    /**
     * img=...
     * 
     * @param file
     * @return this
     */
    public PostParamBuilder setImg(String filePath) {
        multiPartBuilder.addBinaryBody("img", new File(filePath));
        return this;
    }

    /**
     * img=...(name in multipart is ...)
     * 
     * @param data
     * @param fileName
     * @return this
     */
    public PostParamBuilder setImg(byte[] data) {
        multiPartBuilder.addBinaryBody("img", data);
        return this;
    }

    /**
     * face_id1=...
     * 
     * @param id
     * @return this
     */
    public PostParamBuilder setFaceId1(String id) {
        multiPartBuilder.addTextBody("face_id1", id);
        return this;
    }

    /**
     * face_id2=...
     * 
     * @param id
     * @return this
     */
    public PostParamBuilder setFaceId2(String id) {
        multiPartBuilder.addTextBody("face_id2", id);
        return this;
    }

    /**
     * group_name=...
     * 
     * @param groupName
     * @return this
     */
    public PostParamBuilder setGroupName(String groupName) {
        multiPartBuilder.addTextBody("group_name", groupName);
        return this;
    }

    /**
     * group_id=...
     * 
     * @param groupId
     * @return this
     */
    public PostParamBuilder setGroupId(String groupId) {
        multiPartBuilder.addTextBody("group_id", groupId);
        return this;
    }

    /**
     * key_face_id=...
     * 
     * @param id
     * @return this
     */
    public PostParamBuilder setKeyFaceId(String id) {
        multiPartBuilder.addTextBody("key_face_id", id);
        return this;
    }

    /**
     * count=...
     * 
     * @param count
     * @return this
     */
    public PostParamBuilder setCount(int count) {
        multiPartBuilder.addTextBody("count", new Integer(count).toString());
        return this;
    }

    /**
     * type= all | search | recognize
     * 
     * @param type
     * @return this
     */
    public PostParamBuilder setType(String type) {
        multiPartBuilder.addTextBody("type", type);
        return this;
    }

    /**
     * face_id=...
     * 
     * @param faceId
     * @return this
     */
    public PostParamBuilder setFaceId(String faceId) {
        multiPartBuilder.addTextBody("face_id", faceId);
        return this;
    }

    /**
     * faceset_id=...
     * 
     * @param facesetId
     * @return this
     */
    public PostParamBuilder setFacesetId(String facesetId) {
        multiPartBuilder.addTextBody("faceset_id", facesetId);
        return this;
    }

    /**
     * faceset_id=..., ..., ...
     * 
     * @param facesetIds
     * @return this
     */
    public PostParamBuilder setFacesetId(String[] facesetId) {
        setFacesetId(toStringList(facesetId));
        return this;
    }

    /**
     * faceset_id=...
     * 
     * @param facesetIds
     * @return this
     */
    public PostParamBuilder setFacesetId(ArrayList<String> facesetId) {
        setFacesetId(toStringList(facesetId));
        return this;
    }

    /**
     * person_id=...
     * 
     * @param personId
     * @return this
     */
    public PostParamBuilder setPersonId(String personId) {
        multiPartBuilder.addTextBody("person_id", personId);
        return this;
    }

    /**
     * person_name=...
     * 
     * @param personName
     * @return this
     */
    public PostParamBuilder setPersonName(String personName) {
        multiPartBuilder.addTextBody("person_name", personName);
        return this;
    }

    /**
     * name=...
     * 
     * @param name
     * @return this
     */
    public PostParamBuilder setName(String name) {
        multiPartBuilder.addTextBody("name", name);
        return this;
    }

    /**
     * session_id=...
     * 
     * @param id
     * @return this
     */
    public PostParamBuilder setSessionId(String id) {
        multiPartBuilder.addTextBody("session_id", id);
        return this;
    }

    /**
     * mode= oneface | normal
     * 
     * @param type
     * @return this
     */
    public PostParamBuilder setMode(String type) {
        multiPartBuilder.addTextBody("mode", type);
        return this;
    }

    /**
     * face_id=... , ... , ...
     * 
     * @param faceIds
     * @return this
     */
    public PostParamBuilder setFaceId(String[] faceIds) {
        return setFaceId(toStringList(faceIds));
    }

    /**
     * person_id=... , ... , ...
     * 
     * @param personIds
     * @return this
     */
    public PostParamBuilder setPersonId(String[] personIds) {
        return setPersonId(toStringList(personIds));
    }

    /**
     * person_name=... , ... , ...
     * 
     * @param personNames
     * @return this
     */
    public PostParamBuilder setPersonName(String[] personNames) {
        return setPersonName(toStringList(personNames));
    }

    /**
     * group_id=... , ... , ...
     * 
     * @param groupIds
     * @return this
     */
    public PostParamBuilder setGroupId(String[] groupIds) {
        return setGroupId(toStringList(groupIds));
    }

    /**
     * group_name=... , ... , ...
     * 
     * @param groupNames
     * @return this
     */
    public PostParamBuilder setGroupName(String[] groupNames) {
        return setGroupName(toStringList(groupNames));
    }

    /**
     * face=... , ... , ...
     * 
     * @param faceIds
     * @return this
     */
    public PostParamBuilder setFaceId(ArrayList<String> faceIds) {
        return setFaceId(toStringList(faceIds));
    }

    /**
     * person_id=... , ... , ...
     * 
     * @param personIds
     * @return this
     */
    public PostParamBuilder setPersonId(ArrayList<String> personIds) {
        return setPersonId(toStringList(personIds));
    }

    /**
     * person_name=... , ... , ...
     * 
     * @param personNames
     * @return this
     */
    public PostParamBuilder setPersonName(ArrayList<String> personNames) {
        return setPersonName(toStringList(personNames));
    }

    /**
     * group_id=... , ... , ...
     * 
     * @param groupIds
     * @return this
     */
    public PostParamBuilder setGroupId(ArrayList<String> groupIds) {
        return setGroupId(toStringList(groupIds));
    }

    /**
     * group_name=... , ... , ...
     * 
     * @param groupNames
     * @return this
     */
    public PostParamBuilder setGroupName(ArrayList<String> groupNames) {
        return setGroupName(toStringList(groupNames));
    }

    /**
     * img_id=...
     * 
     * @param imgId
     * @return this
     */
    public PostParamBuilder setImgId(String imgId) {
        multiPartBuilder.addTextBody("img_id", imgId);
        return this;
    }

    /**
     * faceset_name=...
     * 
     * @param facesetName
     * @return this
     */
    public PostParamBuilder setFacesetName(String facesetName) {
        multiPartBuilder.addTextBody("faceset_name", facesetName);
        return this;
    }

    /**
     * faceset_name=... , ... , ...
     * 
     * @param facesetNames
     * @return this
     */
    public PostParamBuilder setFacesetName(ArrayList<String> facesetNames) {
        return setFacesetName(toStringList(facesetNames));
    }

    /**
     * faceset_name=... , ... , ...
     * 
     * @param facesetNames
     * @return this
     */
    public PostParamBuilder setFacesetName(String[] facesetNames) {
        return setFacesetName(toStringList(facesetNames));
    }

    /**
     * `attr`=`value`
     * 
     * @param attr
     *            value
     * @return this
     */
    public PostParamBuilder addAttribute(String attr, String value) {
        multiPartBuilder.addTextBody(attr, value);
        return this;
    }

    private String toStringList(String[] sa) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sa.length; ++i) {
            if (i != 0)
                sb.append(',');
            sb.append(sa[i]);
        }

        return sb.toString();
    }

    private String toStringList(ArrayList<String> sa) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sa.size(); ++i) {
            if (i != 0)
                sb.append(',');
            sb.append(sa.get(i));
        }

        return sb.toString();
    }
}
