<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath();
	String basePath = request.getScheme()+"://" +request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<base href="<%=basePath%>"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>无标题文档</title>
<link href="css/backstyle.css" rel="stylesheet" type="text/css" />
<script>
function $(id){
	return document.getElementById(id);
}
function createXMLHttpRequest(){
	var xmlHttpRequest;
	if(window.XMLHttpRequest){
		//现代浏览器创建xmlHttpRequest的方式
		xmlHttpRequest = new XMLHttpRequest();
	}else{
		//ie5,ie6
		xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	return xmlHttpRequest;
}

window.onload = function(){
	var bigSelect = $("big");
	var smallSelect = $("small");
	/* 失去焦点事件 */
	bigSelect.onchange = function(){
		
		var xmlHttp = createXMLHttpRequest();
		xmlHttp.onreadystatechange = function(){
			if(xmlHttp.readyState ==4&&xmlHttp.status==200){
				//[{"gtype_name":"蛋糕","gtype_parentid":1,"gtype_pic":"","id":7},{"gtype_name":"点心","gtype_parentid":1,"gtype_pic":"","id":8}]
				var json = xmlHttp.responseText;
				var info = eval(json);
				var text;
				for(var i=0;i<info.length;i++){
					text+="<option value="+info[i].id+">"+info[i].gtype_name+"</option>";
				}
				
				smallSelect.innerHTML = text;
			}
			
		}
		//构造一个请求地址
		xmlHttp.open("GET","back/GoodsTypeServlet?action=getSmallListByBigId&bigId="+bigSelect.value,true);
		xmlHttp.send();
	}
}
</script>
</head>

<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">表单</a></li>
    </ul>
    </div>
    
    <div class="formbody">
    
    <div class="formtitle"><span>添加商品信息</span></div>
    <form action="GoodsInfoServlet?action=add" method="post" enctype="multipart/form-data">
    	<ul class="forminfo">
	    <li><label>商品名称</label><input name="goods_name" type="text" class="dfinput" /><i>标题不能超过30个字符</i></li>
	    <li><label>所属大类</label>
	    		<select name="goods_parentid" id="big">
	    			<option value="0">请选择</option>
	    			<c:forEach items="${tList }" var="type">
	    				<c:if test="${type.gtype_parentid==0 }">
	    					<option value="${type.id}">${type.gtype_name }</option>
	    				</c:if>
	    			</c:forEach>
	    		</select>
	    		
	    </li>
	    <li><label>所属小类</label>
	    		<select name="goods_fatherid" id="small">
	    		<option value="null">请选择</option>
	    		</select>
	    </li>
	     <li><label>商品图片</label><input name="goods_pic" type="file" /></li>
	    <li><label>商品价格</label><input name="goods_price" type="text" class="dfinput" /></li>
	    <li><label>商品折扣</label><input name="goods_discount" type="text" class="dfinput" />折</li>
	    <li><label>商品优惠价</label><input name="goods_price_off" type="text" class="dfinput" /></li>
	    <li><label>商品描述</label><textarea rows="8" cols="40" name="goods_description" ></textarea></li>
	    <li><label>商品库存</label><input name="goods_stock" type="text" class="dfinput" /></li>
	    <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="确认保存"/></li>
	    </ul>
    
    </form>
    </div>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>

