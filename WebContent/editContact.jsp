<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<style type="text/css">
.form
{
	width:50%;
	margin:0 auto;
	align:center;
	position:relative;
	top:100px;
	border: 1px solid #CCCC99;
	pading:8px;
}
.caption
{
	text-align:center;
	font-size:25px;
	position:relative;
	top:110px;
	left:-100px;
}
.form:hover
{
	border:2px solid gray;
}
table
{
	margin:0 auto;
	text-align:center;
}
th
{
	text-align:right;
	position:relative;
	left:-10px;
}
td
{
	text-align:left;
}
</style>
<title>修改联系人</title>
</head>
<body>
<%
String id = (String) request.getAttribute("id");
String name = (String)request.getAttribute("name");
String sex = (String)request.getAttribute("sex");
String age = (String)request.getAttribute("age");
String phoneNumber = (String)request.getAttribute("phoneNumber");
String wechat = (String)request.getAttribute("wechat");
String workAddress = (String)request.getAttribute("workAddress");
String homeAddress = (String)request.getAttribute("homeAddress");
String description = (String)request.getAttribute("description");
%>
<jsp:include page="home.html" />
<p class="caption">修改联系人</p>
<div class="form">
<form action="/addressList/ContactController" method="post" >
<input type="hidden" name="action" value="save" />
<input type="hidden" name="id" value="<%= id %>" />
<input type="hidden" name="id" value="<%= id %>" />
<input type="hidden" name="id" value="<%= request.getParameter("paegNum") %>" />
<table>
<tr>
	<th><b>姓名</b></th>
	<td><input type="text" name="name"
			value="<%= name %>" autofocus /></td>
</tr>

<tr>
	<th><b>性别</b></th>
	<td>
		<input type="radio" name="sex" value="男" id="sex_male"
		<%= "男".equals(sex) ? "checked" : "" %> /> 
		<label for="sex_male">男</label> 
		<input type="radio" name="sex" value="女" id="sex_female"
		<%= "女".equals(sex) ? "checked" : "" %> /> 
		<label for="sex_female">女</label>
	</td>
</tr>

<tr>
	<th><b>年龄</b></th>
	<td><input type="text" name="age"
		value="<%= age %>" /></td>
</tr>

<tr>
	<th><b>电话</b></th>
	<td><input type="text" name="phoneNumber"
		value="<%= phoneNumber %>" /></td>
</tr>

<tr>
	<th><b>微信</b></th>
	<td><input type="text" name="wechat"
			value="<%= wechat %>" /></td>
</tr>

<tr>
	<th><b>工作单位</b></th>
	<td><input type="text" name="workAddress"
			value="<%= workAddress %>" /></td>
</tr>

<tr>
	<th><b>家庭住址</b></th>
	<td><input type="text" name="homeAddress"
		value="<%= homeAddress %>" /></td>
</tr>

<tr>
	<th><b>备注</b></th>
	<td><textarea name="description"><%= description %></textarea></td>
</tr>

<tr>
	<th></th>
	<td><input type="submit" value="修改" /></td>
</tr>

</table>
</form>
</div>
</body>
</html>