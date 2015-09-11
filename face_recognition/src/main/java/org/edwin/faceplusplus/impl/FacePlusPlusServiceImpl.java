package org.edwin.faceplusplus.impl;

import org.edwin.faceplusplus.api.FacePlusPlusJsonService;
import org.edwin.faceplusplus.api.FacePlusPlusService;
import org.edwin.faceplusplus.client.PostParamBuilder;
import org.edwin.faceplusplus.entity.CandidateResult;
import org.edwin.faceplusplus.entity.DeleteResult;
import org.edwin.faceplusplus.entity.FaceDetectResult;
import org.edwin.faceplusplus.entity.GroupAddPersonResult;
import org.edwin.faceplusplus.entity.GroupCreateResult;
import org.edwin.faceplusplus.entity.PersonCreateResult;
import org.edwin.faceplusplus.entity.SessionIdResult;
import org.edwin.faceplusplus.entity.SessionInfoResult;
import org.edwin.faceplusplus.utility.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("facePlusPlusService")
public class FacePlusPlusServiceImpl implements FacePlusPlusService {

    @Autowired
    private FacePlusPlusJsonService facePlusPlusJsonService;

    public FaceDetectResult detectionDetect(String imgURI) {
        String json = facePlusPlusJsonService.detectionDetect(imgURI);
        return JSONUtil.toObject(json, FaceDetectResult.class);
    }

    @Override
    public FaceDetectResult detectionDetect(String imgURI, String attribute) {
        String json = facePlusPlusJsonService.detectionDetect(imgURI, attribute);
        return JSONUtil.toObject(json, FaceDetectResult.class);
    }

    @Override
    public String detectionLandmark(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String trainVerify(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String trainSearch(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SessionIdResult trainIdentify(String groupId) {
        String json = facePlusPlusJsonService.trainIdentify(groupId);
        return JSONUtil.toObject(json, SessionIdResult.class);
    }

    @Override
    public String recognitionCompare(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String recognitionVerify(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String recognitionSearch(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CandidateResult recognitionIdentify(String groupId, String imgURI) {
        String json = facePlusPlusJsonService.recognitionIdentify(groupId, imgURI);
        return JSONUtil.toObject(json, CandidateResult.class);
    }

    @Override
    public String groupingGrouping(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public PersonCreateResult personCreate(String faceId, String personName) {
        String json = facePlusPlusJsonService.personCreate(faceId, personName);
        return JSONUtil.toObject(json, PersonCreateResult.class);
    }

    @Override
    public DeleteResult personDelete(String personName) {
        String json = facePlusPlusJsonService.personDelete(personName);
        return JSONUtil.toObject(json, DeleteResult.class);
    }

    @Override
    public String personAddFace(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String personRemoveFace(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String personSetInfo(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String personGetInfo(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String facesetCreate(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String facesetDelete(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String facesetAddFace(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String facesetRemoveFace(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String facesetSetInfo(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String facesetGetInfo(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public GroupCreateResult groupCreate(String groupName) {
        String json = facePlusPlusJsonService.groupCreate(groupName);
        return JSONUtil.toObject(json, GroupCreateResult.class);
    }

    @Override
    public DeleteResult groupDelete(String groupName) {
        String json = facePlusPlusJsonService.groupDelete(groupName);
        return JSONUtil.toObject(json, DeleteResult.class);
    }

    @Override
    public GroupAddPersonResult groupAddPerson(String groupId, String personId) {
        String json = facePlusPlusJsonService.groupAddPerson(groupId, personId);
        return JSONUtil.toObject(json, GroupAddPersonResult.class);
    }

    @Override
    public String groupRemovePerson(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String groupSetInfo(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String groupGetInfo(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String infoGetImage(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String infoGetFace(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String infoGetPersonList(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String infoGetFacesetList(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String infoGetGroupList(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String infoGetSession(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String infoGetApp(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public SessionInfoResult getSessionSync(String sessionId) {
        String json = facePlusPlusJsonService.getSessionSync(sessionId);
        return JSONUtil.toObject(json, SessionInfoResult.class);
    }

    @Override
    public SessionInfoResult getSessionSync(String sessionId, long waitMillis) {
        String json = facePlusPlusJsonService.getSessionSync(sessionId, waitMillis);
        return JSONUtil.toObject(json, SessionInfoResult.class);
    }
}
