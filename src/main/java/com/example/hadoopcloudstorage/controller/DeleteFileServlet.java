package com.example.hadoopcloudstorage.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.hadoopcloudstorage.bean.HdfsDao;
import org.apache.hadoop.fs.FileStatus;



/**
 * Servlet implementation class DeleteFileServlet
 */

public class DeleteFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String filePath = new String(request.getParameter("filePath").getBytes(StandardCharsets.ISO_8859_1), "GB2312");
		HdfsDao hdfs = new HdfsDao();
		HdfsDao.deleteFromHdfs(filePath);
		System.out.println("====" + filePath + "====");
		FileStatus[] documentList = HdfsDao.getDirectoryFromHdfs();
		request.setAttribute("documentList", documentList);
		System.out.println("得到list 数据" + documentList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
