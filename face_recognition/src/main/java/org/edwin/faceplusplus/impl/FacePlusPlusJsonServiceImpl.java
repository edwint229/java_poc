package org.edwin.faceplusplus.impl;

import org.apache.commons.lang3.StringUtils;
import org.edwin.faceplusplus.api.FacePlusPlusJsonService;
import org.edwin.faceplusplus.api.ProcessStatus;
import org.edwin.faceplusplus.client.FacePlusPlusClient;
import org.edwin.faceplusplus.client.PostParamBuilder;
import org.edwin.faceplusplus.entity.SessionInfoResult;
import org.edwin.faceplusplus.utility.JSONUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("facePlusPlusJsonService")
public class FacePlusPlusJsonServiceImpl implements FacePlusPlusJsonService {

    private static final long DEFAULT_TIMEOUT = 60000l;

    @Autowired
    private FacePlusPlusClient faceppClient;

    @Value("#{props['api.detection.detect']}")
    private String detectionDetectApi;

    @Value("#{props['api.person.create']}")
    private String personCreateApi;

    @Value("#{props['api.group.create']}")
    private String groupCreateApi;

    @Value("#{props['api.group.add_person']}")
    private String groupAddPersonApi;

    @Value("#{props['api.train.identify']}")
    private String trainIdentifyApi;

    @Value("#{props['api.info.get_session']}")
    private String infoGetSessionApi;

    @Value("#{props['api.recognition.identify']}")
    private String recognitionIdentifyApi;

    @Value("#{props['api.group.delete']}")
    private String groupDeleteApi;

    @Value("#{props['api.person.delete']}")
    private String personDeleteApi;

    protected String request(String api, PostParamBuilder builder) {
        String json = faceppClient.request(api, builder);
        return JSONUtil.formatFieldName(json);
    }

    public String detectionDetect(String imgURI) {
        return detectionDetect(imgURI, null);
    }

    @Override
    public String detectionDetect(String imgURI, String attribute) {
        PostParamBuilder builder = PostParamBuilder.create();
        setImg(imgURI, builder);
        if (StringUtils.isNotBlank(attribute)) {
            builder.setAttribute(attribute);
        }
        return request(detectionDetectApi, builder);
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
    public String trainIdentify(String groupId) {
        PostParamBuilder builder = PostParamBuilder.create().setGroupId(groupId);
        return request(trainIdentifyApi, builder);
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
    public String recognitionIdentify(String groupId, String imgURI) {
        PostParamBuilder builder = PostParamBuilder.create().setGroupId(groupId);
        setImg(imgURI, builder);

        return request(recognitionIdentifyApi, builder);
    }

    private void setImg(String imgURI, PostParamBuilder builder) {
        if (imgURI.startsWith("http")) {
            builder.setUrl(imgURI);
        } else {
            builder.setImg(imgURI);
        }
    }

    @Override
    public String groupingGrouping(PostParamBuilder builder) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String personCreate(String faceId, String personName) {
        PostParamBuilder builder = PostParamBuilder.create().setFaceId(faceId).setPersonName(personName);
        return request(personCreateApi, builder);
    }

    @Override
    public String personDelete(String personName) {
        PostParamBuilder builder = PostParamBuilder.create().setPersonName(personName);
        return request(personDeleteApi, builder);
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
    public String groupCreate(String groupName) {
        PostParamBuilder builder = PostParamBuilder.create().setGroupName(groupName);
        return request(groupCreateApi, builder);
    }

    @Override
    public String groupDelete(String groupName) {
        PostParamBuilder builder = PostParamBuilder.create().setGroupName(groupName);
        return request(groupDeleteApi, builder);
    }

    @Override
    public String groupAddPerson(String groupId, String personId) {
        PostParamBuilder builder = PostParamBuilder.create().setGroupId(groupId).setPersonId(personId);
        return request(groupAddPersonApi, builder);
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
    public String getSessionSync(String sessionId) {
        return this.getSessionSync(sessionId, DEFAULT_TIMEOUT);
    }

    @Override
    public String getSessionSync(String sessionId, long waitMillis) {
        long endTime = System.currentTimeMillis() + waitMillis;
        PostParamBuilder builder = PostParamBuilder.create().setSessionId(sessionId);
        String json = null;
        while (true) {
            json = request(infoGetSessionApi, builder);
            SessionInfoResult sessionInfoResult = JSONUtil.toObject(json, SessionInfoResult.class);
            if (ProcessStatus.INQUEUE.getValue().equals(sessionInfoResult.getStatus())) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    break;
                }
            }

            if (System.currentTimeMillis() >= endTime) {
                break;
            }
        }
        return json;
    }

}
