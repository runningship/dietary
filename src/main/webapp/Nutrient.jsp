<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>营养成分</title>
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
	width: 30%;
}
</style>
<script type="text/javascript">

function setUnit(option){
	document.getElementById("unit").value=option.attributes["unit"].value;
}
window.onload=function(){
	var content= document.getElementById("content");
	content.click();
	content.focus();
	var option = document.getElementById("nutrientTypes").selectedOptions[0];
	setUnit(option);
}
</script>
</head>
<body>
<%@include file="Function.jsp" %>
<div><%=getRequestValue(request, "msg") %></div>
<div><%=getRequestValue(request, "foodName") %></div>
<div>
<form method="post" action="add">
<input type="hidden" name="foodName" value="<%=getRequestValue(request, "foodName") %>"/>
<div>
	<span>名称: </span>
	<select id="nutrientTypes" onchange="setUnit(this.selectedOptions[0])" name="nutrient">
		<%
			java.util.List nutrientTypes = (java.util.List)request.getAttribute("nutrientTypes");
			if(nutrientTypes==null){
				return;
			}
			for(Object ntype : nutrientTypes){
				request.setAttribute("nutrientType", ntype);
		%>
		<option unit="<%=getRequestValue(request, "nutrientType").toString().split(":")[1] %>">
			<%=getRequestValue(request, "nutrientType").toString().split(":")[0] %>
		</option>
		<%} %>
	</select>
</div>
<div>
	<span>含量: </span><input type="number"  id="content" name="content" />
</div>
<div>
	<span>单位: </span><input readonly="true" id="unit" name="unit" />
</div>
<input type="submit" value="添加营养成分"/>
</form>
</div>
<table align="center" width="1080px" cellspacing="0px">
		<thead>
			<td class="produce">名称</td>
			<td class="produce">含量</td>
			<td class="produce">单位</td>
			<td class="produce">操作</td>
		</thead>
		<%
			java.util.List nutrients = (java.util.List)request.getAttribute("nutrients");
			if(nutrients==null){
				return;
			}
			for(Object nutrient : nutrients){
				request.setAttribute("nutrient", nutrient);
		%>
		<tr>
			<td><%=getRequestValue(request, "nutrient.nutrient") %></td>
			<td><%=getRequestValue(request, "nutrient.content") %></td>
			<td><%=getRequestValue(request, "nutrient.unit") %></td>
			<td><a href="delete?nutrientUid=<%=getRequestValue(request, "nutrient.uid") %>">删除</a></td>
		</tr>
		<% }%>
	</table>
</div>
</body>
</html>