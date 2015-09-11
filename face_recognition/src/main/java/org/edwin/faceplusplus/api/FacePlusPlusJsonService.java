package org.edwin.faceplusplus.api;

import org.edwin.faceplusplus.client.PostParamBuilder;

public interface FacePlusPlusJsonService {

	String detectionDetect(String imgURI);

	String detectionDetect(String imgURI, String attribute);

	String detectionLandmark(PostParamBuilder builder);

	String trainVerify(PostParamBuilder builder);

	String trainSearch(PostParamBuilder builder);

	String trainIdentify(String groupId);

	String recognitionCompare(PostParamBuilder builder);

	String recognitionVerify(PostParamBuilder builder);

	String recognitionSearch(PostParamBuilder builder);

	String recognitionIdentify(String groupId, String imgURI);

	String groupingGrouping(PostParamBuilder builder);

	String personCreate(String faceId, String personName);

	String personDelete(String personName);

	String personAddFace(PostParamBuilder builder);

	String personRemoveFace(PostParamBuilder builder);

	String personSetInfo(PostParamBuilder builder);

	String personGetInfo(PostParamBuilder builder);

	String facesetCreate(PostParamBuilder builder);

	String facesetDelete(PostParamBuilder builder);

	String facesetAddFace(PostParamBuilder builder);

	String facesetRemoveFace(PostParamBuilder builder);

	String facesetSetInfo(PostParamBuilder builder);

	String facesetGetInfo(PostParamBuilder builder);

	String groupCreate(String groupName);

	String groupDelete(String groupName);

	String groupAddPerson(String groupId, String personId);

	String groupRemovePerson(PostParamBuilder builder);

	String groupSetInfo(PostParamBuilder builder);

	String groupGetInfo(PostParamBuilder builder);

	String infoGetImage(PostParamBuilder builder);

	String infoGetFace(PostParamBuilder builder);

	String infoGetPersonList(PostParamBuilder builder);

	String infoGetFacesetList(PostParamBuilder builder);

	String infoGetGroupList(PostParamBuilder builder);

	String infoGetSession(PostParamBuilder builder);

	String infoGetApp(PostParamBuilder builder);

	String getSessionSync(String sessionId);

	String getSessionSync(String sessionId, long waitMillis);

}
