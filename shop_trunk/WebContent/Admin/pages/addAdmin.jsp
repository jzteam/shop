<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="tools.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加管理员</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="Admin/css/body.css">
   <script type="text/javascript">
	
	function getAdminType() {
			var url = "getAdminType";		
			sendRequest(url);
		}
		var req;
		function sendRequest(url) {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if(window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			req.onreadystatechange = showAdminType;
			req.open("get",url,true);
			req.send(null);
		}
		function showAdminType() {
			if(req.readyState == 4) {
				if(req.status == 200) {
					var TypeXml = req.responseXML;
					var adminTypes =TypeXml.getElementsByTagName("adminType");
					var adminTypeId = document.getElementById("adminTypeId");				
					if(adminTypes.length > 0) {
						for(var i=0;i<adminTypes.length;i++) {
							var TypeId = adminTypes[i].getElementsByTagName("adminTypeId").item(0).firstChild.data;
							var adminTypeName = adminTypes[i].getElementsByTagName("adminTypeName").item(0).firstChild.data;
							var op = document.createElement("option");
							op.setAttribute("value",TypeId);
							var txt = document.createTextNode(adminTypeName);
							op.appendChild(txt);			
							adminTypeId.appendChild(op);
							adminTypeId.style.width = "auto";
						}
					}else {
						typeDiv.innerHTML = "还未设置管理级别";
					}
				}
			}
		}
		var checkAdminName;
		function checkAdminName() {
			var adminName = document.getElementById("adminName");
			var adminNameDiv = document.getElementById("AdminNameDiv");
			if(adminName.value == "") {
				adminNameDiv.innerHTML = "真实姓名不能为空";
				checkAdminName = false;
			} else {
				adminNameDiv.innerHTML = "√";
				checkAdminName = true;
			}
		}
		var loginName_IsExist;
        function checkLoginNameIsExist() {
			var loginName = document.getElementById("loginName");
			var url = "checkLoginNameIsExist?loginName="+loginName.value;
			sendLoginName(url);
		}
        function sendLoginName(url) {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if(window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = loginNameIsExist;
			req.open("get",url,true);
			req.send(null);
		}
		function loginNameIsExist() {
			if(req.readyState == 4) {
				if(req.status == 200) {
					var returnXml = req.responseXML;
					var loginNameDiv = document.getElementById("loginNameDiv");
					var state = returnXml.getElementsByTagName("state")[0].firstChild.data;
					var content = returnXml.getElementsByTagName("content")[0].firstChild.data;
					if(state == "true") {
						loginName_IsExist = true;
					} else {
						loginName_IsExist = false;
					}
					loginNameDiv.innerHTML = content;
				}
			}
		}
		function checkLoginName() {
			var loginName = document.getElementById("loginName");
			var loginNameDiv = document.getElementById("loginNameDiv");
			if(loginName.value == "") {
				loginNameDiv.innerHTML = "登录名不能为空";
			} else {
				checkLoginNameIsExist();
			}
		}
		var checkPwd1;
		function checkPwd1() {
			pwd1 = document.getElementById("loginPwd1");
			var pwd1Div = document.getElementById("pwd1Div");
			if(pwd1.value == "") {
				pwd1Div.innerHTML = "密码不能为空";
				checkPwd1 = false;
			} else {
				pwd1Div.innerHTML = "√";
				checkPwd1 = true;
			}
		}
		var checkPwd2;
		function checkPwd2() {
			var pwd2 = document.getElementById("loginPwd2").value;
			var pwd1 = document.getElementById("loginPwd1").value;
			var pwd2Div = document.getElementById("pwd2Div");
			if(pwd2 == "") {
				pwd2Div.innerHTML = "密码不能为空";
				checkPwd2 = false;
			} else {
			    if(pwd2!=pwd1)
			    {
			      pwd2Div.innerHTML = "两次密码不一致";
				  checkPwd2 = false;
			    }else{
			       pwd2Div.innerHTML = "√";
				   checkPwd2 = true;
			    }
				
			}
		}

	//清除错误提示
	function clearAN(){
	    var adminNameDiv = document.getElementById("adminNameDiv");
		adminNameDiv.innerHTML="*";
	}
	function clearP1D(){
	    var pwd1Div = document.getElementById("pwd1Div");
		pwd1Div.innerHTML="*";
	}
	function clearP2D(){
	    var pwd2Div = document.getElementById("pwd2Div");
		pwd2Div.innerHTML="*";
	}
	//必须所有信息填写正确才能成功提交信息
	function checkAll(){
		document.addAdmin_form.submit();
	}
  </script>
  </head>
  
  <body  onload="getAdminType()">
  	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="Admin/images/tab_03.gif" width="15" height="30" /></td>
        <td width="1101" background="Admin/images/tab_05.gif">&nbsp;</td>
        <td width="281" background="Admin/images/tab_05.gif"><table border="0" align="right" cellpadding="0" cellspacing="0">
            <tr>
              <td width="60"><table width="87%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"></div></td>
                    <td class="STYLE1"><div align="center"></div></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><br></div></td>
                    <td class="STYLE1"></td>
                  </tr>
              </table></td>
              <td width="60"><table width="90%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><br></div></td>
                    <td class="STYLE1"><div align="center"><br></div></td>
                  </tr>
              </table></td>
              <td width="52"><table width="88%" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="STYLE1"><div align="center"><br></div></td>
                    <td class="STYLE1"><div align="center"><br></div></td>
                  </tr>
              </table></td>
            </tr>
        </table></td>
        <td width="14"><img src="Admin/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="Admin/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="#f3ffe3">
<center>
<form  name="addAdmin_form" method= "post" action="addAdminServlet"> 
	<table>
    	<tr>
    	    <td>管理级别&nbsp &nbsp</td>
    	    <td>  
			<select name="adminTypeId">
					<c:forEach var="adminType" items="${adminTypes }">
						<option value="${adminType.adminTypeId}">${adminType.typeName}</option>
					</c:forEach>
			</select>
			</td>
			<td><font color="red"><div id="TypeDiv">*</div></font></td>
		</tr>
    	<tr>
    		<td>真实姓名&nbsp &nbsp</td>
    		<td><input type="text" name="adminName" onfocus="clearAN()" onblur="checkAdminName()"  size=16/></td>
    		<td><font color="red"><div id="adminNameDiv">*</div></font></td>
    	</tr>

    	<tr>
    		<td>登录账号&nbsp &nbsp</td>
    		<td><input type="text" name="loginName" onblur="checkLoginName()" size=16/></td>
    		<td><font color="red"><div id="loginNameDiv">*</div></font></td>
    	</tr>
    	<tr>
    		<td>登录密码&nbsp &nbsp</td>
    		<td><input type="password" name="loginPwd1"  onfocus="clearP1D()" onblur="checkPwd1()"  size=16/></td>
    		<td><font color="red"><div id="pwd1Div">*</div></font></td>
    	</tr>
    	<tr>
    		<td>确认密码&nbsp &nbsp</td>
    		<td><input type="password" name="loginPwd2" onfocus="clearP2D()" onblur="checkPwd2()"  size=16/></td>
    		<td><font color="red"><div id="pwd2Div">*</div></font></td>
    	</tr>
       	<tr>  
        <td></td>
        <td><input  type="button"  value="添加" size=4 onclick="checkAll()"></td>
        <td><font color="red">${addMessage}</font></td>
        </tr> 
  </table>  
 </form> 
</center>


		</td>
        <td width="9" background="Admin/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="Admin/images/tab_20.gif" width="15" height="29" /></td>
        <td background="Admin/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="25%" height="29" nowrap="nowrap">&nbsp;</td>
            <td width="75%" valign="top" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="Admin/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
  </body>
</html>
