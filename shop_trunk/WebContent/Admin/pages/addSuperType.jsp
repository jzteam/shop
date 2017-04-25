<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="tools.jsp"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加大类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript">
	    var superTypeName_IsValid;
		function checkSuperTypeName() {
			var superTypeName = document.getElementById("superTypeName");
			var superTypeNameDiv = document.getElementById("superTypeNameDiv");
			if(superTypeName.value == "") {
				superTypeNameDiv.innerHTML = "大类名不能为空";
				superTypeName_IsValid=false;
			} else {
				checkSuperTypeNameIsExist();
			}
		}
		var req;
		function checkSuperTypeNameIsExist() {
			var superTypeName = document.getElementById("superTypeName");
			var url = "checkSuperTypeIsExist?superTypeName="+superTypeName.value;
			sendSuperTypeName(url);
		}
		function sendSuperTypeName(url) {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if(window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = superTypeNameIsExist;
			req.open("get",url,true);
			req.send(null);
		}
		var superTypeName_IsExist;
		function superTypeNameIsExist() {
			if(req.readyState == 4) {
				if(req.status == 200) {
					var returnXml = req.responseXML;
					var superTypeNameDiv = document.getElementById("superTypeNameDiv");
					var state = returnXml.getElementsByTagName("state")[0].firstChild.data;
					var content = returnXml.getElementsByTagName("content")[0].firstChild.data;
					if(state == "true") {
						superTypeName_IsExist = true;
					} else {
						superTypeName_IsExist = false;
						superTypeName_IsValid=true;
					}
					superTypeNameDiv.innerHTML = content;
				}
			}
		}
		function addSuperType() {
			var oForm = document.getElementsByTagName("form")[0];
			var superTypeName = document.getElementById("superTypeName");
			if(superTypeName_IsValid) {
				oForm.action = "addSuperTypeServlet?sueprTypeName="+superTypeName.value;
				oForm.submit();
			}
		}
	</script>
	<link rel="stylesheet" type="text/css" href="Admin/css/body.css">
	<link rel="stylesheet" type="text/css" href="Admin/css/td_fontSize.css">
	<style type="text/css">
		#t2{
			height: 350px;
			width: 650px;
			text-align: center;
		}
	</style>
  </head>
  
 <body>
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
			


	<form action="addSuperTypeServlet" method="get">
	    <table id="t2">
			<tr>
				<td> 
					大类名称:<input type="text" id="superTypeName" name="superTypeName" onblur="checkSuperTypeName()" />
					<input type="button"  value="添加" onclick="addSuperType()" onblur="checkSuperTypeName()"/><font  color="red">${message }</font>
					<div id="superTypeNameDiv"></div>
				</td>
			</tr>
		</table>
	</form>




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