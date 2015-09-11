package org.edwin.faceplusplus.utility.test;

import java.util.List;

import org.edwin.faceplusplus.entity.Age;
import org.edwin.faceplusplus.entity.Attribute;
import org.edwin.faceplusplus.entity.Coordinate;
import org.edwin.faceplusplus.entity.EstimateStringValue;
import org.edwin.faceplusplus.entity.Face;
import org.edwin.faceplusplus.entity.FaceDetectResult;
import org.edwin.faceplusplus.entity.Pose;
import org.edwin.faceplusplus.entity.Position;
import org.edwin.faceplusplus.entity.SimpleNumberValue;
import org.edwin.faceplusplus.utility.JSONUtil;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Lists;

public class JSONUtilTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testToObject() {
        long start = System.currentTimeMillis();
        String formatedJson = "{\"sessionId\":\"38047ad0f0b34c7e8c6efb6ba39ed355\",\"face\":[{\"faceId\":\"17233b4b1b51ac91e391e5afe130eb78\",\"tag\":\"\",\"position\":{\"width\":26.8,\"height\":26.8,\"center\":{\"x\":49.4,\"y\":37.6},\"eyeLeft\":{\"x\":43.3692,\"y\":30.8192},\"eyeRight\":{\"x\":56.5606,\"y\":30.9886},\"mouthLeft\":{\"x\":46.1326,\"y\":44.9468},\"mouthRight\":{\"x\":54.2592,\"y\":44.6282},\"nose\":{\"x\":49.9404,\"y\":38.8484}},\"attribute\":{\"age\":{\"range\":5,\"value\":23},\"gender\":{\"confidence\":99.9999,\"value\":\"Female\"},\"glass\":{\"confidence\":99.945,\"value\":\"None\"},\"race\":{\"confidence\":99.6121,\"value\":\"Asian\"},\"smiling\":{\"value\":4.86501},\"pose\":{\"pitchAngle\":{\"value\":17},\"rollAngle\":{\"value\":0.735735},\"yawAngle\":{\"value\":-2}}}}],\"imgId\":\"22fd9efc64c87e00224c33dd8718eec7\",\"imgWidth\":500.0,\"imgHeight\":500.0,\"url\":\"http://www.faceplusplus.com.cn/wp-content/themes/faceplusplus/assets/img/demo/1.jpg?v=4\"}";
        FaceDetectResult faceDetectResult = JSONUtil.toObject(formatedJson, FaceDetectResult.class);
        System.out.println(String.format("ToObject cost %s ms", System.currentTimeMillis() - start));
        System.out.println(faceDetectResult);
    }

    @Test
    public void testToJson() {
        FaceDetectResult result = new FaceDetectResult();
        result.setSessionId("38047ad0f0b34c7e8c6efb6ba39ed355");
        result.setUrl("http://www.faceplusplus.com.cn/wp-content/themes/faceplusplus/assets/img/demo/1.jpg?v=4");
        result.setImgId("22fd9efc64c87e00224c33dd8718eec7");
        result.setImgWidth(500);
        result.setImgHeight(500);

        List<Face> face = Lists.newArrayList();
        Face face1 = new Face();
        face1.setFaceId("17233b4b1b51ac91e391e5afe130eb78");
        face1.setTag("");

        Attribute attribute = new Attribute();
        attribute.setAge(new Age(25, 5));
        attribute.setGender(new EstimateStringValue(99.9, "male"));
        attribute.setRace(new EstimateStringValue(99.6121, "Asian"));
        attribute.setGlass(new EstimateStringValue(99.945, "None"));
        attribute.setSmiling(new SimpleNumberValue("4.86501"));
        Pose pose = new Pose();
        pose.setPitchAngle(new SimpleNumberValue("17"));
        pose.setRollAngle(new SimpleNumberValue("0.735735"));
        pose.setYawAngle(new SimpleNumberValue("-2"));
        attribute.setPose(pose);
        face1.setAttribute(attribute);

        Position position = new Position();
        position.setWidth(26.8);
        position.setHeight(26.8);
        position.setCenter(new Coordinate(49.4, 37.6));
        position.setEyeLeft(new Coordinate(43.3692, 30.8192));
        position.setEyeRight(new Coordinate(56.5606, 30.9886));
        position.setMouthLeft(new Coordinate(46.1326, 44.9468));
        position.setMouthRight(new Coordinate(54.2592, 44.6282));
        position.setNose(new Coordinate(49.9404, 38.8484));
        face1.setPosition(position);

        face.add(face1);
        result.setFace(face);

        long start = System.currentTimeMillis();
        String json = JSONUtil.toJson(result);
        System.out.println(String.format("ToJson cost %s ms", System.currentTimeMillis() - start));
        System.out.println(json);
    }

    @Test
    public void testFormatFieldName() {
        long start = System.currentTimeMillis();
        String formatedJson = JSONUtil
                .formatFieldName("{\"session_id\":\"38047ad0f0b34c7e8c6efb6ba39ed355\",\"face\":[{\"face_id\":\"17233b4b1b51ac91e391e5afe130eb78\",\"tag\":\"\",\"position\":{\"width\":26.8,\"height\":26.8,\"center\":{\"x\":49.4,\"y\":37.6},\"eye_left\":{\"x\":43.3692,\"y\":30.8192},\"eye_right\":{\"x\":56.5606,\"y\":30.9886},\"mouth_left\":{\"x\":46.1326,\"y\":44.9468},\"mouth_right\":{\"x\":54.2592,\"y\":44.6282},\"nose\":{\"x\":49.9404,\"y\":38.8484}},\"attribute\":{\"age\":{\"range\":5,\"value\":23},\"gender\":{\"confidence\":99.9999,\"value\":\"Female\"},\"glass\":{\"confidence\":99.945,\"value\":\"None\"},\"race\":{\"confidence\":99.6121,\"value\":\"Asian\"},\"smiling\":{\"value\":4.86501},\"pose\":{\"pitch_angle\":{\"value\":17},\"roll_angle\":{\"value\":0.735735},\"yaw_angle\":{\"value\":-2}}}}],\"img_id\":\"22fd9efc64c87e00224c33dd8718eec7\",\"img_width\":500.0,\"img_height\":500.0,\"url\":\"http://www.faceplusplus.com.cn/wp-content/themes/faceplusplus/assets/img/demo/1.jpg?v=4\"}");
        System.out.println(String.format("FormatFieldName cost %s ms", System.currentTimeMillis() - start));
        System.out.println(formatedJson);
    }

    @Test
    public void testIntegrated() {
        String formatedJson = JSONUtil
                .formatFieldName("{\"session_id\":\"38047ad0f0b34c7e8c6efb6ba39ed355\",\"face\":[{\"face_id\":\"17233b4b1b51ac91e391e5afe130eb78\",\"tag\":\"\",\"position\":{\"width\":26.8,\"height\":26.8,\"center\":{\"x\":49.4,\"y\":37.6},\"eye_left\":{\"x\":43.3692,\"y\":30.8192},\"eye_right\":{\"x\":56.5606,\"y\":30.9886},\"mouth_left\":{\"x\":46.1326,\"y\":44.9468},\"mouth_right\":{\"x\":54.2592,\"y\":44.6282},\"nose\":{\"x\":49.9404,\"y\":38.8484}},\"attribute\":{\"age\":{\"range\":5,\"value\":23},\"gender\":{\"confidence\":99.9999,\"value\":\"Female\"},\"glass\":{\"confidence\":99.945,\"value\":\"None\"},\"race\":{\"confidence\":99.6121,\"value\":\"Asian\"},\"smiling\":{\"value\":4.86501},\"pose\":{\"pitch_angle\":{\"value\":17},\"roll_angle\":{\"value\":0.735735},\"yaw_angle\":{\"value\":-2}}}}],\"img_id\":\"22fd9efc64c87e00224c33dd8718eec7\",\"img_width\":500.0,\"img_height\":500.0,\"url\":\"http://www.faceplusplus.com.cn/wp-content/themes/faceplusplus/assets/img/demo/1.jpg?v=4\"}");
        FaceDetectResult faceDetectResult = JSONUtil.toObject(formatedJson, FaceDetectResult.class);
        String toJson = JSONUtil.toJson(faceDetectResult);
        Assert.assertEquals(formatedJson, toJson);
    }

}
