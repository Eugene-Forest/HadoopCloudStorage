package com.example.hadoopcloudstorage.controller;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class RegisterServlet
 */

public class RegisterServlet extends HttpServlet {
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
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		UserDAO user = new UserDAO();
		try {
			user.insert(username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		HdfsDao hdfs = new HdfsDao();
		FileStatus[] documentList = HdfsDao.getDirectoryFromHdfs();
		request.setAttribute("documentList", documentList);
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
