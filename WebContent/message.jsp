<%@page import="java.io.PrintWriter"%>
<%@page import="allmessage.allmessage"%>
<%@page import="java.util.ArrayList"%>
<%@page import="conndb.conndb"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh" content="1">
<title>message</title>
<script type="text/javascript">
function scrollwindow()
{
	scroll(0,100000);
	setTimeout('scrollwindow()' , 200);
}
window.onload=scrollwindow();
</script>
<style type="text/css">
body{
 
background-color:rgb(247,238,214);
 }
 </style>
</head>
<body onload="scroll(0,9999)">
<h3>消息列表</h3>
<table>
<%
conndb conn=new conndb();
ArrayList<allmessage> allnews=new ArrayList();
allnews=conn.getnews();
for(allmessage temp:allnews)
{	
%>
<tr><td><%out.println(temp.getName());out.println("  在");out.println(temp.getTimestamp());out.println("说：");%></td></tr>
<tr><td><%out.println(temp.getMessages());%></td></tr>
<%
}
%>
</table>
</body>
</html>