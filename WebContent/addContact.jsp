<%--
  Created by Eclipse
  Author: TonnyLung
  Date: 17/5/25
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script>
function volidate(form) {
	volidate1(form);
	volidate2(form);
}
function volidate1(form) {
	if (form.name.value=="") {
		alert("请输入姓名！");
	}
}
function volidate1(form) {
	if (form.phoneNumber.value=="") {
		alert("请输入电话号码！");
	}
}
</script> -->
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
.form:hover
{
	border:2px solid gray;
}
.caption
{
	text-align:center;
	font-size:25px;
	position:relative;
	top:110px;
	left:-100px;
}
table
{
	margin:0 auto;
	text-align:center;
	font-weight: bold;
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

<title>新建联系人</title>
</head>
<body>
<jsp:include page="home.html" />

<p class="caption">新建联系人</p>
<div class="form">
<form name="form" action="/addressList/ContactController" method="post" >
<input type="hidden" name="action" value="add" />
<table>
<tr>
	<th><b>姓名</b></th>
	<td><input type="text" name="name" autofocus /></td>
</tr>

<tr>
	<th><b>性别</b></th>
	<td>
		<input type="radio" name="sex" value="男" id="sex_male" /> 
		<label for="sex_male">男</label> 
		<input type="radio" name="sex" value="女" id="sex_female" /> 
		<label for="sex_female">女</label>
	</td>
</tr>

<tr>
	<th><b>年龄</b></th>
	<td><input type="text" name="age" /></td>
</tr>

<tr>
	<th><b>电话</b></th>
	<td><input type="text" name="phoneNumber" /></td>
</tr>

<tr>
	<th><b>微信</b></th>
	<td><input type="text" name="wechat" /></td>
</tr>

<tr>
	<th><b>工作单位</b></th>
	<td><input type="text" name="workAddress" /></td>
</tr>

<tr>
	<th><b>家庭住址</b></th>
	<td><input type="text" name="homeAddress" /></td>
</tr>

<tr>
	<th><b>备注</b></th>
	<td><textarea name="description"></textarea></td>
</tr>

<tr>
	<th></th>
	<td><input type="submit" value="添加" /></td>
</tr>

</table>
</form>
</div>
</body>
</html>