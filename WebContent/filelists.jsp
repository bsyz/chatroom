<%@page import="java.util.ArrayList"%>
<%@page import="conndb.conndb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
body{
 
background-color:rgb(247,238,214);
 }
 </style>
</head>
<body>
<table>
<h3>资源列表</h3>
<%
conndb c=new conndb();
ArrayList<String> names=new ArrayList();
names=c.filenamelists();
int i=0;
for(String name : names)
{
	i++;
%>
<tr>
<form action="filedownload" name=<%out.print("form"+i); %> method="post">
<td><input type="text" style="border-style:none" name="filename" value=<%out.println(name);%> readonly></td>
<td><input type="submit" value="下载" name=<%out.println(i); %> id=<%out.println(i); %> ></td>
</form>
</tr>
<%} %>
</table>

</body>
</html>