<%@ page language="java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD html 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="head.jsp"%>
<%@ page import="org.apache.hadoop.fs.FileStatus"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset-UTF-8">
<title>Insert title here</title>
</head>
<body>
<body style="text-align: center; margin-bottom: 100px;">
	<div class="navbar">
		<div class="navbar-inner">
			<a class="brand" href="#" style="margin-left: 200px;"
				style="color: #000000">网盘</a>
			<ul class="nav" style="line-height: 50px; float: right;">
				<li><a style="color:#000000 size=40"> <%=session.getAttribute("username")%></a></li>
				<li><a href="login.jsp"> <input type="button" value="退出"></a></li>
			</ul>
		</div>
	</div>
	<div
		style="margin: 0px auto; text-align: left; width: 1200px; height: 50px;">
		<form class=" form-inline " method="post"
			enctype="MULTIPART/FORM-DATA" action="UploadServlet">
			<div style="line-height: 50px; float: left;">
				<input style="background-color: #000000" type="submit" name="submit"
					value="上传文件">
			</div>
			<div style="line-height: 50px; float: left">
				<input type="file" name="filel" size="30" />
			</div>
		</form>
	</div>
	<div
		style="margin: 0px auto; width: 1200px; height: 500px; background:#fff;">
		<table class="table table-hover"
			style="width: 1000px;margin-left: 100px;">
			<tr>
				<td>文件名</td>
				<td>属性</td>
				<td>大小(KB)</td>
				<td>操作</td>
				<td>操作</td>
			</tr>
			<%
			FileStatus[] list = (FileStatus[]) request.getAttribute("documentList");
			String name = (String) request.getAttribute("usename");
			if (list != null)
				for (int i = 0; i < list.length; i++) {
			%>
			<tr style="border-bottom: 2px solid #ddd">
				<%
				if (list[i].isDir()) //DocumentServlet
				{
					out.print("<td><a href=\"UploadServlet>filePath=" + list[i].getPath() + "\">" + list[i].getPath().getName()
					+ "</a></td>");
				} else {
					out.print("<td>" + list[i].getPath().getName() + "</td>");
				}
				%>
				<td><%= (list[i].isDir() ? "目录" : "文件")%></td>
				<td><%= list[i].getLen()/1024%></td>
				<td><a style="color: #000000"
					href="DeleteFileServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(), "GB2312")%>">删除</a></td>
				<td><a style="color: #000000" c
					href="DownloadServlet?filePath=<%=java.net.URLEncoder.encode(list[i].getPath().toString(), "GB2312")%>">下载</a></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</body>
</html>
