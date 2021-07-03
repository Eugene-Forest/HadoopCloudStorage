package com.example.hadoopcloudstorage.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.hadoopcloudstorage.bean.HdfsDao;
import com.example.hadoopcloudstorage.model.UserDAO;
import org.apache.hadoop.fs.FileStatus;



/**
 * Servlet implementation class LoginServlet
 */

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO user = new UserDAO();
		if (user.checkUser(username, password)) {
			//用户合法,跳转到界面
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			HdfsDao hdfs = new HdfsDao();
			FileStatus[] documentList = HdfsDao.getDirectoryFromHdfs();
			request.setAttribute("documentList", documentList);
			System.out.println("得到list数据" + documentList);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			//用户不合法,调回登录界面,并提示错误信息
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

}
