package org.edwin.faceplusplus.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.edwin.faceplusplus.api.FacePlusPlusService;
import org.edwin.faceplusplus.entity.Attribute;
import org.edwin.faceplusplus.entity.Face;
import org.edwin.faceplusplus.entity.FaceDetectResult;
import org.edwin.faceplusplus.entity.FaceVO;
import org.edwin.faceplusplus.utility.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;

import sun.misc.BASE64Decoder;

import com.google.common.collect.Lists;

import fastjson.v123.com.alibaba.fastjson.util.IOUtils;

public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = -3690074703354814132L;

    private static Logger logger = LoggerFactory.getLogger(FileUploadServlet.class);

    private String uploadPath;

    private DiskFileItemFactory factory;

    private FacePlusPlusService facePlusPlusService;

    private ApplicationContext applicationContext;

    public void init() throws ServletException {
        ServletContext servletContext = this.getServletConfig().getServletContext();

        // Inject Spring Beans
        applicationContext = (ApplicationContext) servletContext
                .getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
        facePlusPlusService = (FacePlusPlusService) applicationContext.getBean("facePlusPlusService");

        // Create a factory for disk-based file items
        factory = new DiskFileItemFactory();

        // Configure a repository (to ensure a secure temp location is used)
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);

        // Set factory constraints
        factory.setSizeThreshold(4096);

        // Set Upload Path
        uploadPath = System.getProperty("java.io.tmpdir");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            // set response encoding
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/json;charset=utf-8");
            String fileSavePath = null;

            // save upload file from request
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (isMultipart) {
                fileSavePath = saveMultipartImg(request);
            } else {
                fileSavePath = saveBase64Img(request);
            }

            // call face plus plus service to detect face
            FaceDetectResult faceDetectResult = facePlusPlusService.detectionDetect(fileSavePath,
                    "gender,age,race,smiling,glass,pose");

            // populate detect result for UI display
            List<FaceVO> faceVOs = populateFaceVOs(faceDetectResult);

            // set response json
            response.getWriter().println(JSONUtil.toJson(faceVOs));
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new ServletException(e);
        }
    }

    private List<FaceVO> populateFaceVOs(FaceDetectResult faceDetectResult) {
        List<FaceVO> faceVOs = Lists.newArrayList();
        for (Face face : faceDetectResult.getFace()) {
            Attribute attribute = face.getAttribute();
            FaceVO faceVO = new FaceVO();
            faceVO.setAge(String.format("%s, Min Age: %s, Max Age: %s.", attribute.getAge().getValue(), attribute.getAge()
                    .getMinAge(), attribute.getAge().getMaxAge()));
            faceVO.setGender(String.format("%s, Confidence: %s.", attribute.getGender().getValue(), attribute.getGender()
                    .getConfidence()));
            faceVO.setGlass(String.format("%s, Confidence: %s.", attribute.getGlass().getValue(), attribute.getGlass()
                    .getConfidence()));
            faceVO.setRace(String
                    .format("%s, Confidence: %s.", attribute.getRace().getValue(), attribute.getRace().getConfidence()));
            faceVO.setSmiling(attribute.getSmiling().getValue().toString());
            faceVO.setPose(String.format("PitchAngle: %s, RollAngle: %s, YawAngle: %s", attribute.getPose().getPitchAngle()
                    .getValue(), attribute.getPose().getRollAngle().getValue(), attribute.getPose().getYawAngle().getValue()));

            faceVOs.add(faceVO);
        }

        return faceVOs;
    }

    private String saveMultipartImg(HttpServletRequest request) throws Exception {
        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // Set overall request size constraint
        upload.setSizeMax(10 * 1024 * 1024);

        List<FileItem> items = upload.parseRequest(request);
        Iterator<FileItem> itr = items.iterator();
        while (itr.hasNext()) {
            FileItem item = (FileItem) itr.next();
            // if (item.isFormField()) {// Process a regular form field
            // String name = item.getFieldName();
            // String value = item.getString();
            // }

            if (!item.isFormField()) {// Process a file upload
                File fullFile = new File(item.getName());
                File savedFile = new File(uploadPath, fullFile.getName());
                item.write(savedFile);

                return savedFile.getAbsolutePath();
            }
        }

        throw new Exception("Posted Request does not contains a valid image file.");
    }

    private String saveBase64Img(HttpServletRequest request) throws Exception {
        FileOutputStream write = null;
        try {
            String imgBase64Data = request.getParameter("img");
            BASE64Decoder decoder = new BASE64Decoder();

            String fileSavePath = genFileSavePath();
            write = new FileOutputStream(new File(fileSavePath));
            byte[] decoderBytes = decoder.decodeBuffer(imgBase64Data);
            write.write(decoderBytes);
            write.flush();

            return fileSavePath;
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.close(write);
        }
    }

    private String genFileSavePath() {
        return new StringBuffer(uploadPath).append(File.separator).append(System.currentTimeMillis()).append(".png").toString();
    }
}
