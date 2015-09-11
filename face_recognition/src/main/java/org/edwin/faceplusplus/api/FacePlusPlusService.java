package org.edwin.faceplusplus.api;

import org.edwin.faceplusplus.client.PostParamBuilder;
import org.edwin.faceplusplus.entity.CandidateResult;
import org.edwin.faceplusplus.entity.DeleteResult;
import org.edwin.faceplusplus.entity.FaceDetectResult;
import org.edwin.faceplusplus.entity.GroupAddPersonResult;
import org.edwin.faceplusplus.entity.GroupCreateResult;
import org.edwin.faceplusplus.entity.PersonCreateResult;
import org.edwin.faceplusplus.entity.SessionIdResult;
import org.edwin.faceplusplus.entity.SessionInfoResult;

public interface FacePlusPlusService {

	public FaceDetectResult detectionDetect(String imgURI);

	/**
	 * Detect People Face from Picture
	 * 
	 * @param imgURI
	 * @param attribute
	 * @return
	 */
	public FaceDetectResult detectionDetect(String imgURI, String attribute);

	public String detectionLandmark(PostParamBuilder builder);

	public String trainVerify(PostParamBuilder builder);

	public String trainSearch(PostParamBuilder builder);

	public SessionIdResult trainIdentify(String groupId);

	public String recognitionCompare(PostParamBuilder builder);

	public String recognitionVerify(PostParamBuilder builder);

	public String recognitionSearch(PostParamBuilder builder);

	public CandidateResult recognitionIdentify(String groupId, String imgURI);

	public String groupingGrouping(PostParamBuilder builder);

	public PersonCreateResult personCreate(String faceId, String personName);

	public DeleteResult personDelete(String personName);

	public String personAddFace(PostParamBuilder builder);

	public String personRemoveFace(PostParamBuilder builder);

	public String personSetInfo(PostParamBuilder builder);

	public String personGetInfo(PostParamBuilder builder);

	public String facesetCreate(PostParamBuilder builder);

	public String facesetDelete(PostParamBuilder builder);

	public String facesetAddFace(PostParamBuilder builder);

	public String facesetRemoveFace(PostParamBuilder builder);

	public String facesetSetInfo(PostParamBuilder builder);

	public String facesetGetInfo(PostParamBuilder builder);

	public GroupCreateResult groupCreate(String groupName);

	public DeleteResult groupDelete(String groupName);

	public GroupAddPersonResult groupAddPerson(String groupId, String personId);

	public String groupRemovePerson(PostParamBuilder builder);

	public String groupSetInfo(PostParamBuilder builder);

	public String groupGetInfo(PostParamBuilder builder);

	public String infoGetImage(PostParamBuilder builder);

	public String infoGetFace(PostParamBuilder builder);

	public String infoGetPersonList(PostParamBuilder builder);

	public String infoGetFacesetList(PostParamBuilder builder);

	public String infoGetGroupList(PostParamBuilder builder);

	public String infoGetSession(PostParamBuilder builder);

	public String infoGetApp(PostParamBuilder builder);

	public SessionInfoResult getSessionSync(String sessionId);

	public SessionInfoResult getSessionSync(String sessionId, long waitMillis);

}
