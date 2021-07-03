package com.example.hadoopcloudstorage.controller;


import com.example.hadoopcloudstorage.bean.HdfsDao;
import org.apache.hadoop.fs.FileStatus;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class DownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String local = "C:\\red_spider";
        String filePath = new String(request.getParameter("filePath").getBytes(StandardCharsets.ISO_8859_1),"GB2312");
        HdfsDao hdfs = new HdfsDao();
        hdfs.download(filePath, local);
        FileStatus[] documentList = HdfsDao.getDirectoryFromHdfs();
        request.setAttribute("documentList", documentList);
        System.out.println("得到1ist数据" + documentList);
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
