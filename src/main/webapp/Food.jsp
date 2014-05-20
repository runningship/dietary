<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Foods</title>
<style type="text/css">
	td {
		border-top :1px solid #a9c6c9;
		border-right :1px solid #a9c6c9;
		height: 40px;
		font-family: "微软雅黑";
	}
	td:first-child {
		border-left :1px solid #a9c6c9;
	}
	table:last-child {
		border-bottom :1px solid #a9c6c9;
	}
	.name{
		width: 40%;
	}
	.produce{
		width: 40%;
	}
</style>
</head>
<body>
<%@include file="Function.jsp" %>
<%=getRequestValue(request, "msg") %>
<form method="post"  action="add">
<div>
	<span>名称: </span><input name="name" />
</div>
<input type="submit" value="添加"/>
</form>
</body>
<div>
<table align="center" width="1080px" cellspacing="0px">
		<thead>
			<td class="produce">英文名称</td>
			<td class="produce">中文名称</td>
		</thead>
		<%
			java.util.List foods = (java.util.List)request.getAttribute("foods");
			if(foods==null){
				return;
			}
			for(Object food : foods){
				request.setAttribute("food", food);
		%>
		<tr>
			<td><a target="_blank" href="nutrient/list?foodName=<%=getRequestValue(request, "food.name") %>">
			<%=getRequestValue(request, "food.name") %></a></td>
			<td><%=getRequestValue(request, "food.cname") %></td>
			<td><a target="_blank" href="setCommon?ndbNo=<%=getRequestValue(request, "food.ndbNo") %>">设为常用</a></td>
		</tr>
		<% }%>
	</table>
</div>
</html>