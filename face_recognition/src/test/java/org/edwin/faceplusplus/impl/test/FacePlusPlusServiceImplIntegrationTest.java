package org.edwin.faceplusplus.impl.test;

import org.edwin.faceplusplus.api.FacePlusPlusService;
import org.edwin.faceplusplus.api.ProcessStatus;
import org.edwin.faceplusplus.entity.CandidateResult;
import org.edwin.faceplusplus.entity.DeleteResult;
import org.edwin.faceplusplus.entity.FaceDetectResult;
import org.edwin.faceplusplus.entity.GroupAddPersonResult;
import org.edwin.faceplusplus.entity.GroupCreateResult;
import org.edwin.faceplusplus.entity.PersonCreateResult;
import org.edwin.faceplusplus.entity.SessionIdResult;
import org.edwin.faceplusplus.entity.SessionInfoResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class FacePlusPlusServiceImplIntegrationTest {

    @Autowired
    private FacePlusPlusService facePlusPlusService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        // delete person
        DeleteResult personDeleteResult = facePlusPlusService.personDelete("edwin");
        System.out.println(personDeleteResult);

        // delete group
        DeleteResult groupDeleteResult = facePlusPlusService.groupDelete("TestGroup");
        System.out.println(groupDeleteResult);
    }

    @Test
    public void testDetectionDetectWithLocalImg() {
        // detect image
        FaceDetectResult faceDetectResult = facePlusPlusService.detectionDetect("E:/photos/me.png",
                "gender,age,race,smiling,glass,pose");
        System.out.println(faceDetectResult);

        // create person by face_id
        PersonCreateResult personCreateResult = facePlusPlusService.personCreate(faceDetectResult.getFace().iterator().next()
                .getFaceId(), "edwin");
        System.out.println(personCreateResult);

        // create group
        GroupCreateResult groupCreateResult = facePlusPlusService.groupCreate("TestGroup");
        System.out.println(groupCreateResult);

        // add person into group
        GroupAddPersonResult groupAddPersonResult = facePlusPlusService.groupAddPerson(groupCreateResult.getGroupId(),
                personCreateResult.getPersonId());
        System.out.println(groupAddPersonResult);

        // train person
        SessionIdResult sessionIdResult = facePlusPlusService.trainIdentify(groupCreateResult.getGroupId());
        System.out.println(sessionIdResult);

        // get train result by session id
        SessionInfoResult sessionInfoResult = facePlusPlusService.getSessionSync(sessionIdResult.getSessionId(), 600000);
        System.out.println(sessionInfoResult);

        // identify person
        if (ProcessStatus.SUCC.getValue().equals(sessionInfoResult.getStatus())) {
            CandidateResult candidateResult = facePlusPlusService.recognitionIdentify(groupCreateResult.getGroupId(),
                    "E:/photos/me.png");
            System.out.println(candidateResult);
        }

    }
}
