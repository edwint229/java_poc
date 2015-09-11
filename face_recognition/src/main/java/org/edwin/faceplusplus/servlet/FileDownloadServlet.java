package org.edwin.faceplusplus.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownloadServlet extends HttpServlet {

    private static final long serialVersionUID = 1490276172636807471L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        String fileName = "D:/data/changeAccountingDate/errorFile/errorFile.zip";
        String fileType = "application/zip";
        // Find this file id in database to get file name, and file type

        // You must tell the browser the file type you are going to send
        // for example application/pdf, text/plain, text/html, image/jpg,
        // application/zip
        resp.setContentType(fileType);

        // Make sure to show the download dialog
        resp.setHeader("Content-disposition", "attachment; filename=errorFile.zip");

        // Assume file name is retrieved from database
        // For example D:\\file\\test.pdf
        File my_file = new File(fileName);

        // This should send the file to browser
        OutputStream out = resp.getOutputStream();
        FileInputStream in = new FileInputStream(my_file);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

}
