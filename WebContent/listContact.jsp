<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page import="tongxunlu.dao.ContactDAOImpl" %>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="tongxunlu.domain.Contact" %>
<%@ page import="tongxunlu.model.ContactModel" %>
<%@ page import="tongxunlu.util.Pagination" %>
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
body
{
	positon:relative;
}
table
{
	font-family:Microsoft YaHei;
	font-size:0.8em;
	width:100%;
	border-collapse:collapse;
	text-align:center;
}
.contact td, .contact th
{
	border:1px solid gray;
	padding:5px;
}
.first
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
.fetchContact
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
</style>
<title>联系人</title>
</head>

<body>
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
<jsp:include page="home.html" />
<div>
<table class="contact">
<caption id="alert">联系人列表</caption>
<tr class="first">
<th></th>
<th>ID</th>
<th>姓名</th>
<th>性别</th>
<th>年龄</th>
<th>电话</th>
<th>微信</th>
<th>工作单位</th>
<th>家庭住址</th>
<th>备注</th>
<th>创建时间</th>
<th>操作</th>
</tr>
<%
int pageNum = (int)request.getAttribute("pageNum"); 
int pageCount = (int)request.getAttribute("pageCount"); 
int recordCount = (int)request.getAttribute("recordCount"); 
String pageUrl = (String)request.getAttribute("pageUrl");
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
<td><%= contact.getAge() %></td>
<td><%= contact.getPhoneNumber() %></td>
<td><%= contact.getWechat() %></td>
<td><%= contact.getWorkAddress() %></td>
<td><%= contact.getHomeAddress() %></td>
<td><%= contact.getDescription() %></td>
<td><%= contact.getCreateTime() %></td>
<td>
	<a href="/addressList/ContactController?action=delete&id=<%= contact.getId()%>&pageNum=<%= pageNum %>">
	删除</a> 
	<a href="/addressList/ContactController?action=edit&id=<%= contact.getId()%>&pageNum=<%= pageNum %>">
	修改</a>
</td>
</tr>
<%
 }
} else {
%>
</table>
</div>
<script>
	document.getElementById("col").innerHTML = "您的联系人名单为空！！！";
</script>
<%
}
%>
<table class="fetchContact">
<tr>
<td>
	<input type="hidden" name="action" value="delete" />
	<input type="hidden" name="pageNum" value="<%= pageNum %>" />
	<a href="#" onclick="myFunction()">全选</a>
	<a href="#" onclick="myFunction2()">取消全选</a>
	<input type="submit" style="width:40px; height:20; font-size:1em;"
	onclick="return confirm('真的要删除所有记录？');" value="删除" />
</td>
</tr>
</table>
<div>
<span>
	<%= Pagination.getPagenation(pageNum, pageCount, recordCount, pageUrl) %>
</span>
</div>
</form>
</body>
</html>