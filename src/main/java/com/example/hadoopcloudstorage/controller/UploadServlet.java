package com.example.hadoopcloudstorage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.hadoopcloudstorage.bean.HdfsDao;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.hadoop.fs.FileStatus;


/**
 * Servlet implementation class UploadServlet
 */

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		File file;
		int maxFileSize = 50 * 1024 * 1024; // 50M
		int maxMemSize = 50 * 1024 * 1024; // 50M
		ServletContext context = getServletContext();
		String filePath = context.getInitParameter("file-upload");
		System.out.println(" source file path : " + filePath + " ");
		// 验证上传内容的类型
		String contentType = request.getContentType();
		if ((contentType.indexOf("multipart/form-data") >= 0)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// 设置内存中存储文件的最大值
			factory.setSizeThreshold(maxMemSize);
			// 本地存储的数据大于maxMemSize;
			factory.setRepository(new File("c:\\"));
			// 创建一个新的文件上传处理程序
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 设置最大上传的文件大小
			upload.setSizeMax(maxFileSize);
			try {
				// 解析获取的文件
				List fileItems = upload.parseRequest(request);
				// 处理上传的文件
				Iterator i = fileItems.iterator();
				System.out.println("begin to upload file to tomcat server </p>");
				while (i.hasNext()) {
					FileItem fi = (FileItem) i.next();
					if (!fi.isFormField()) {
						// 获取上传文件的参数
						String fieldName = fi.getFieldName();
						String fileName = fi.getName();
						String fn = fileName.substring(fileName.lastIndexOf("\\") + 1);
						System.out.println("<br>" + fn + "<br>");
						boolean isInMemory = fi.isInMemory();
						long sizeInBytes = fi.getSize();
						// 写入文件
						if (fileName.lastIndexOf("\\") >= 0) {
							file = new File(filePath, fileName.substring(fileName.lastIndexOf("\\")));
						} else {
							file = new File(filePath, fileName.substring(fileName.lastIndexOf("\\") + 1));
						}
						fi.write(file);
						System.out.println("upload file to tomcat server success ! ");
						System.out.println("begin to upload file to hadoop hdfs</p>");
						String name = filePath + "\\" + fileName;
						// 将tomcat上的文件上传到Hadoop上
						HdfsDao hdfs = new HdfsDao();
						hdfs.copyFile(name);
						System.out.println("upload file to hadoop hdfs  success!");
						FileStatus[] documentList = HdfsDao.getDirectoryFromHdfs();
						request.setAttribute("documentList", documentList);
						System.out.println(" 得到list数据 " + documentList);
						request.getRequestDispatcher("index.jsp").forward(request, response);
					}
				}
			} catch (Exception ex) {
				System.out.println(ex);
			}
		} else {
			System.out.println("<p>No file upload<p>");
		}
	}

}
