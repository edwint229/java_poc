package org.edwin.faceplusplus.impl.test;

import org.edwin.faceplusplus.api.FacePlusPlusService;
import org.edwin.faceplusplus.entity.CandidateResult;
import org.edwin.faceplusplus.entity.FaceDetectResult;
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
public class FacePlusPlusServiceImplTest {

    @Autowired
    private FacePlusPlusService facePlusPlusService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testDetectionDetectWithOnlineImg() {
        FaceDetectResult result = facePlusPlusService
                .detectionDetect("http://img5.cache.netease.com/photo/0003/2014-12-12/AD9DN2KA00AJ0003.jpg");
        System.out.println(result);
    }

    @Test
    public void testDetectionDetectWithLocalImg() {
        // detect image
        FaceDetectResult faceDetectResult = facePlusPlusService.detectionDetect("E:/photos/me.png",
                "gender,age,race,smiling,glass,pose");
        System.out.println(faceDetectResult);

    }

    @Test
    public void testGetSessionSync() {
        // get train result by session id
        SessionInfoResult sessionInfoResult = facePlusPlusService.getSessionSync("", 60000);
        System.out.println(sessionInfoResult);
    }

    @Test
    public void testRecognitionIdentify() {
        CandidateResult candidateResult = facePlusPlusService.recognitionIdentify("TestGroup", "E:/photos/me.png");
        System.out.println(candidateResult);
    }

}
