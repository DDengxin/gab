<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>

<% 	
	
	String uploadPath = "demo\\fileUpload\\upload";
	String saveDirectory = session.getServletContext().getRealPath(uploadPath);
	//System.out.println(saveDirectory);
	MultipartRequest multi = new MultipartRequest(request,saveDirectory,
		100 * 1024 * 1024, "UTF-8");
	//如果有上传文件, 则保存到数据内
	Enumeration files = multi.getFileNames();
	//multi
	while (files.hasMoreElements()) {
		String name = (String)files.nextElement();//表示文件
		File f = multi.getFile(name);
		if(f!=null){
			//读取上传后的项目文件, 导入保存到数据中
			String fileName = multi.getFilesystemName(name);//文件名称
			response.getWriter().write(fileName +"("+new Date()+")");    //可以返回一个JSON字符串, 在客户端做更多处理					
		}
	}
%>
