<%@page import="java.io.PrintWriter"%>
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
<h3>在线列表：</h3>
<table>
<!-- <textarea cols="100" style="textarea"> -->

<%
/* HttpSession session2=request.getSession();*/
/* PrintWriter out2=response.getWriter();
 */conndb con=new conndb();
ArrayList<String> names=con.getnames();
for(String s: names)
{
%>
<tr><td><%out.println(s);%></td>
<td><img name=<%out.print(s); %> alt="" src="imgdownload?user_name=<%=s%>" height="50%"></td><!-- src="imgdownload?img_name=<%=s%>" --><!-- "/chating/imgs/test.jpg" -->
</tr>

<% }%>

<!-- </textarea>-->
</table>
</body>
</html>