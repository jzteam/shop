<%@ page language="java" pageEncoding="GBK"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>后台登陆</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	overflow:hidden;
}
.STYLE3 {color: #528311; font-size: 12px; }
.STYLE4 {color: #42870a; font-size: 12px; }
-->
</style>
<script type="text/javascript">
	function login(self) {
		var name = document.getElementById("name");
		var password = document.getElementById("password");
		if(name.value != "" && password.value != "") {
			self.href = "adminLoginServlet?name="+name.value+"&"+"password="+password.value;
		}
	}
	function reset() {
		var name = document.getElementById("name");
		var password = document.getElementById("password");
		name.value = "";
		password.value = "";
	}
</script>
  </head>
  
  <body style="margin:auto;text-align:center;background-image:url('Admin/images/bg.gif')">
    <div style="width:439px;height:275px; margin-top:100px; margin-left:auto; margin-right:auto;">
	   <form action="adminLoginServlet" method="post" name="form" id="form">
		    <table style="width:439px;height:275px;font-size:12px;background-image:url('Admin/images/login.jpg');">
		    	<tr>
		    		<td height="80" colspan="3">
		    			<h1><font color="red">后台登录</font></h1>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td height="10" colspan="3">
		    		</td>
		    	</tr>
		    	<tr>
		    		<td width="168px"></td>
		    		<td width="50px">用户名：</td>
		    		<td width="130px"><input type="text" id="name" name="name"  style="height:16px; width:100px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
		    	</tr>
		    	<tr>
		    	    <td></td>
		    		<td>密　码：</td>
		    		<td><input type="password" id="password" name="password"  style="height:16px; width:100px; border:solid 1px #cadcb2; font-size:12px; color:#81b432;"></td>
		    	</tr>
		    	<tr>
		    	 <td></td>
		    	  <td></td>
		    		<td align="left"><input type="submit" value="登录"/>&nbsp;<input type="reset" value="重置" /></td>
		    	</tr>
		    	<tr>
		    	    <td colspan="3" style="color: red;align:right;">${message}</td>
		    	</tr>
		    	<tr>
		    		<td colspan="3" align="center">家电销售网站,版权所有&nbsp;</td>
		    	</tr>
		    </table>
	    </form>
    </div>
  </body>
</html>
