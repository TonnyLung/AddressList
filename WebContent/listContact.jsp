<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="com.tongxunlu.ContactDAOImpl" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.tongxunlu.Contact" %>
<html>
<head>
<meta charset="UTF-8">
<script>
function myFunction()
{
	var array = document.getElementsByName("id"); 
	for(var i = 0; i < array.length; i++) 
	{
		array[i].checked = true;
	}	
}
function myFunction2()
{
	var array = document.getElementsByName("id"); 
	for(var i = 0; i < array.length; i++) 
	{
		array[i].checked = false;
	}	
}
</script>
<style type="text/css">
table
{
	font-family:Microsoft YaHei;
	font-size:0.8em;
	width:100%;
	border-collapse:collapse;
	text-align:center;
}
#contact td, #contact th
{
	border:1px solid gray;
	padding:5px;
}
#first
{
	background-color:#DDDDDD;
}
caption
{
	font-size:1.1em;
	caption-position:top;
	color:blue;
	margin-top:25px;
	margin-bottom: 8px;
}
#fetchContact
{
	font-size:0.7em;
	margin-top:10px;
	position:relative;
	width:50%;
	text-align:left;
	
}
#col
{
	color:red;
	text-align:right;
	margin:0;
}
ul 
{
	list-style-type: none;
	padding:0;
	margin:0;
	overfrow:hidden;
}
li
{
	float:left;
}
ul a:link, ul a:visited
{
	margin-top:50px;
	display:block;
	width:100%;
	font-weigth:bold;
	background-color:#CCFFCC;
	text-align:center;
	padding:6px;
	text-decoration:none;
	
}
ul a:hover
{
	color:#669966;
}
</style>
<title>联系人</title>
</head>
<body>
<ul>
<li><a href="http://localhost:8080/addressList/home.html">主页</a></li>
<li><a href="http://localhost:8080/addressList/addContact.jsp?action=add">新建联系人</a></li>
<li><a href="http://localhost:8080/addressList/ContactController?action=findAll">所有联系人</a></li>
</ul>
<form action="/addressList/ContactController" method="get">
<p id="col"></p>
<%
String message = (String)request.getAttribute("message");
if(message != null){
%>
<script>
	document.getElementById("col").innerHTML = <%= message%>;
</script>
<%
} else {
%>
<script>
	document.getElementById("col").innerHTML = "";
</script>
<%
}
%>
<table id="contact">
<caption id="alert">联系人列表</caption>
<tr id="first">
<th></th>
<th>ID</th>
<th>姓名</th>
<th>性别</th>
<th>电话</th>
<th>微信</th>
<th>工作单位</th>
<th>家庭住址</th>
<th>备注</th>
<th>创建时间</th>
<th>操作</th>
</tr>
<%
if(request.getAttribute("list") != null){
ArrayList<Contact> list = (ArrayList<Contact>)request.getAttribute("list");
for(int i = 0; i < list.size(); i++) {
	 Contact contact = list.get(i);
%>
<tr>
<td><input type="checkbox" name="id" value=<%= contact.getId() %> /></td>
<td><%= contact.getId() %></td>
<td><%= contact.getName() %></td>
<td><%= contact.getSex() %></td>
<td><%= contact.getPhoneNumber() %></td>
<td><%= contact.getWechat() %></td>
<td><%= contact.getWorkAddress() %></td>
<td><%= contact.getHomeAddress() %></td>
<td><%= contact.getDescription() %></td>
<td><%= contact.getCreateTime() %></td>
<td>
	<a href="/addressList/ContactController?action=delete&id=<%= contact.getId() %>">
	删除</a> 
	<a href="/addressList/ContactController?action=edit&id=<%= contact.getId() %>">
	修改</a>
</td>
</tr>
<%
 }
} else {
%>
</table>
<script>
	document.getElementById("col").innerHTML = "您的联系人名单为空！！！";
</script>
<%
}
%>
<table id="fetchContact">
<tr>
<td>
	<input type="hidden" name="action" value="delete" />
	<a href="#" onclick="myFunction()">全选</a>
	<a href="#" onclick="myFunction2()">取消全选</a>
	<input type="submit" style="width:40px; height:20; font-size:1em;"
	onclick="return confirm('真的要删除所有记录？');" value="删除" />
</td>
</tr>
</table>
</form>
</body>
</html>