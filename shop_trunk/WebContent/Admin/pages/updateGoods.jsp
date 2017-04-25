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
    
    <title>修改商品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript">
		var req;
		function getSuperType() {
			var url = "getSuperType";
			sendSuperTypeRequest(url);
		}
		function sendSuperTypeRequest(url) {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if(window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = showSuper;
			req.open("get",url,true);
			req.send(null);
		}
		function showSuper() {
			if(req.readyState == 4) {
				if(req.status == 200) {
					var subTypeXml = req.responseXML;
					var superTypes = subTypeXml.getElementsByTagName("super");
					var superTypeId = document.getElementById("superTypeId");
					if(superTypes.length > 0) {
						for(var i=0;i<superTypes.length;i++) {
							var superId = superTypes[i].getElementsByTagName("superId").item(0).firstChild.data;
							var superName = superTypes[i].getElementsByTagName("superName").item(0).firstChild.data;
							var op = document.createElement("option");
							op.setAttribute("value",superId);
							var txt = document.createTextNode(superName);
							op.appendChild(txt);
							superTypeId.appendChild(op);
							superTypeId.style.width = "auto";
						}
						var nType = document.getElementById("superType").value;
                        for(var i=0;i<=superTypeId.options.length;i++ ){
                             if(superTypeId.options[i].value==nType){
                                  superTypeId.options.selectedIndex = i;
                                    reuturn;
                             }
                         }
					} else {
						typeDiv.innerHTML = "还没有大类";
					}
				}
			}
		}
		function getSubType() {
			var id;
			var superType = document.getElementById("superTypeId");
			for(var i=0;i<superType.options.length;i++) {
				if(superType.options[i].selected) {
					id = superType.options[i].value;
				}
			}
			var url = "getSubTypeBySuperTypeId?superTypeId="+id;
			sendRequest(url);
		}
		function sendRequest(url) {
			if(window.XMLHttpRequest) {
				req = new XMLHttpRequest();
			} else if(window.ActiveXObject) {
				req = new ActiveXObject("Microsoft.XMLHTTP");
			}
			req.onreadystatechange = showSub;
			req.open("get",url,true);
			req.send(null);
		}
		function clearSubType() {
			 var subType = document.getElementById("subTypeId");
			 for(var i = subType.options.length - 1;i>=0;i--) {
			 	subType.options[i].parentNode.removeChild(subType.options[i]);
			 }
			 subType.style.width = "";
		}
		function showSub() {
			if(req.readyState == 4) {
				if(req.status == 200) {
					var subTypeXml = req.responseXML;
					clearSubType();
					var subTypes = subTypeXml.getElementsByTagName("subType");
					var subType = document.getElementById("subTypeId");
					var typeDiv = document.getElementById("typeDiv");
					if(subTypes.length > 0) {
						for(var i=0;i<subTypes.length;i++) {
							var subTypeId = subTypes[i].getElementsByTagName("subTypeId").item(0).firstChild.data;
							var subTypeName = subTypes[i].getElementsByTagName("subTypeName").item(0).firstChild.data;
							var op = document.createElement("option");
							op.setAttribute("value",subTypeId);
							var txt = document.createTextNode(subTypeName);
							op.appendChild(txt);
							subType.appendChild(op);
							subType.style.width = "auto";
							typeDiv.innerHTML = "√";
						}
					} else {
						typeDiv.innerHTML = "*";
					}
				}
			}
		}
        var checkGoodsName_;
		function checkGoodsName() {
			var GoodsName = document.getElementById("GoodsName");
			var GoodsNameDiv = document.getElementById("GoodsNameDiv");
			if(GoodsName.value == "") {
				GoodsNameDiv.innerHTML = "商品名不能为空";
				checkGoodsName_=false;
			} else {
				GoodsNameDiv.innerHTML="√";
				checkGoodsName_=true;
			}
		}
		var checkISBN_;
		function checkISBN() {
			var isbn = document.getElementById("ISBN");
			var isbnDiv = document.getElementById("ISBNDiv");
			if(isbn.value == "") {
				isbnDiv.innerHTML = "商品编码不能为空";
				checkISBN_=false;
			} else {
				ISBNDiv.innerHTML = "√";
				checkISBN_=true;
			}
		}
		
		var checkPages_;
		function checkPages() {
			var pages = document.getElementById("pages");
			var pagesDiv = document.getElementById("pagesDiv");
			//var pattern = /^[0-9]\d*$/;
			var pattern = /(?:19|20\d{2})\/(?:0[1-9]|1[0-2])\/(?:0[1-9]|[12][0-9]|3[01])/;
			if(pages.value == "") {
				pagesDiv.innerHTML = "商品的生产日期不能为空";
				checkPages_ = false;
			} else if(pattern.test(pages.value)) {
				pagesDiv.innerHTML = "√";
				checkPages_ = true;
			} else {
				pagesDiv.innerHTML = "格式不对";
				checkPages_ = false;
			}
		}
		var checkPublisher_;
		function checkPublisher() {
			var publisher = document.getElementById("publisher");
			var publisherDiv = document.getElementById("publisherDiv");
			if(publisher.value == "") {
				publisherDiv.innerHTML = "产地不能为空";
				checkPublisher_ = false;
			} else {
				publisherDiv.innerHTML = "√";
				checkPublisher_ = true;
			}
		}
		var checkAuthor_ ;
		function checkAuthor() {
			var author = document.getElementById("author");
			var authorDiv = document.getElementById("authorDiv");
			if(author.value == "") {
				authorDiv.innerHTML = "品牌不能为空";
				checkAuthor_ = false;
			} else {
				authorDiv.innerHTML = "√";
				checkAuthor_ = true;
			}
		}
		var checkPrice_;
		function checkPrice() {
			var price = document.getElementById("price");
			var priceDiv = document.getElementById("priceDiv");
			var pattern = /^[1-9]\d*([.]\d+||\d*)$/;
			if(price.value == "") {
				priceDiv.innerHTML = "原价不能为空";
				checkPrice_ = false;
			} else if(pattern.test(price.value)) {
				priceDiv.innerHTML = "√";
				checkPrice_ = true;
			} else {
				priceDiv.innerHTML = "格式不对";
				checkPrice_ = false;
			}
		}
		var checkNowPrice_;
		function checkNowPrice() {
			var nowPrice = document.getElementById("nowPrice");
			var nowPriceDiv = document.getElementById("nowPriceDiv");
			var pattern = /^[1-9]\d*([.]\d+||\d*)$/;
			if(nowPrice.value == "") {
				nowPriceDiv.innerHTML = "现价不能为空";
				checkNowPrice_ = false;
			} else if(pattern.test(nowPrice.value)) {
				nowPriceDiv.innerHTML = "√";
				checkNowPrice_ = true;
			} else {
				nowPriceDiv.innerHTML = "格式不对";
				checkNowPrice_ = false;
			}
		}
		var checkGoodsNum_;
		function checkGoodsNum() {
			var GoodsNum = document.getElementById("GoodsNum");
			var GoodsNumDiv = document.getElementById("GoodsNumDiv");
			var pattern = /^[1-9]\d*$/;
			if(GoodsNum.value == "") {
				GoodsNumDiv.innerHTML = "数量不能为空";
				checkGoodsNum_ = false;
			} else if(pattern.test(GoodsNum.value)) {
				GoodsNumDiv.innerHTML = "√";
				checkGoodsNum_ = true;
			} else {
				GoodsNumDiv.innerHTML = "格式不对";
				checkGoodsNum_ = false;
			}
		}
		function update() {
			checkGoodsName();
			checkISBN();
			checkPages();
			checkPublisher();
			checkAuthor();
			checkPrice();
			checkNowPrice();
			checkGoodsNum();
			var s1 = document.getElementById("superTypeId");
			var s2 = document.getElementById("subTypeId");
			var typeDiv = document.getElementById("typeDiv");
			if(s1.value == "0" || s2.value == "0" || s2.value == "") {
				typeDiv.innerHTML = "请匹配大类和小类";
			} else if(checkGoodsName_ && checkISBN_ && checkPages_ && checkPublisher_ && checkAuthor_ && checkPrice_ && checkNowPrice_ && checkGoodsNum_) {
				var oForm = document.getElementsByTagName("form")[0];
				oForm.submit();
			}
		}
	</script>
	</script>

	<link rel="stylesheet" type="text/css" href="Admin/css/body.css">
	<link rel="stylesheet" type="text/css" href="Admin/css/td_fontSize.css">
	<style type="text/css">
		
	select{
			font-size: 12px;
		}
	div {
			color: red;
		}
	#t1{
		 	height: 350px;
		   	width: 649px;
			text-align: center;
		}
	.introduce{
		  vertical-align: top;
		}
		input{
	border: solid 1px #cadcb2;
	}
	</style>
  </head>
   <body onload="getSuperType()">
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
			<table id="t1" align="center">
  	<tr><td>
  		<form action="updateGoodsServlet" method="get">
    	<table>
    		<tr>
				<td>选择类别:</td>
				<td>大类<select id="superTypeId" name="superTypeId" onchange="getSubType()">
							<option value="0">--选择大类--</option>
							<c:forEach var="superType" items="${superTypes }">
								<option value="${superType.superTypeId }">${superType.typeName }</option>
							</c:forEach>
							</select>
				</td>
				<td>
				小类<select id="subTypeId" name="subTypeId">
					<option value="0">--选择小类--</option>
					</select>
					</td>
				<td><div id="typeDiv"></div></td>
			</tr>
			<tr>
			<td><input type="hidden" id="superType" name="superTypeId" value="${Goods.superTypeId}"/></td>
			<td><input type="hidden" id="subType" name="subTypeId" value="${Goods.subTypeId}"/></td>
			<tr>
			<tr>
    			<td>商品ID:</td>
				<td><input type="text" id="GoodsId" name="GoodsId" value="${Goods.goodsId}" readonly=true/></td>
				<td><div id="GoodsIdDiv"></div></td>
    	   </tr>
    		<tr>
				<td>商品名:</td>
				<td><input type="text" id="GoodsName" name="GoodsName" value="${Goods.goodsName}" onblur="checkGoodsName()"/></td>
				<td><div id="GoodsNameDiv">*</div></td>
			</tr>
			<tr>
				<td>商品编码:</td>
				<td><input type="text" id="ISBN" name="ISBN" value="${Goods.ISBN}" onblur="checkISBN()"/></td>
				<td><div id="ISBNDiv">*</div></td>
			</tr>
			<tr>
    			<td class="introduce">相关介绍:</td>
				<td colspan="2"><textarea rows="3" cols="20" id="introduce"  name="introduce">${Goods.introduce}</textarea></td>		
			</tr>
			<tr>
    			<td>生产时间:</td>
				<td><input type="text" id="pages" name="pages" value="${Goods.produceDate}" onblur="checkPages()"/></td>
				<td><div id="pagesDiv">*</div></td>
			</tr>
			<tr>
    			<td>产地:</td>
				<td><input type="text" id="publisher" name="publisher" value="${Goods.publisher}" onblur="checkPublisher()"/></td>
				<td><div id="publisherDiv">*</div></td>
			</tr>
			<tr>
    			<td>品牌:</td>
				<td><input type="text" id="author" name="author" value="${Goods.author}" onblur="checkAuthor()"/></td>
				<td><div id="authorDiv">*</div></td>
			</tr>
    		<tr>
				<td>原价:</td>
				<td><input type="text" id="price" name="price" value="${Goods.price}" onblur="checkPrice()"/></td>
				<td><div id="priceDiv">*</div></td>
			</tr>
    		<tr>
				<td>现价:</td>
				<td><input type="text" id="nowPrice" name="nowPrice" value="${Goods.nowPrice}" onblur="checkNowPrice()"/></td>
				<td><div id="nowPriceDiv">*</div></td>
			</tr>
    		<tr>
				<td>数量:</td>
				<td><input type="text" id="GoodsNum" name="GoodsNum" value="${Goods.goodsNum}" onblur="checkGoodsNum()"/></td>
				<td><div id="GoodsNumDiv">*</div></td>
			</tr>
			<!-- 
				<tr>
				<td><input type="hidden" id="newGoods" name="newGoods" value="${Goods.newGoods}"/></td>
				<td><input type="hidden" id="saleGoods" name="saleGoods" value="${Goods.saleGoods}"/></td>
				<td><input type="hidden" id="hotGoods" name="hostGoods" value="${Goods.hostGoods}"/></td>
				<td><input type="hidden" id="specialGoods" name="specialGoods" value="${Goods.specialGoods}"/></td>
				<tr>
			 -->
			<tr>
				<td>类型:</td>
				<td colspan="2">
				  <c:if test="${Goods.hostGoods == 1 }">
				    <input type="checkbox" name="hostGoods" value="1" checked="checked"/>热卖
				  </c:if>
				  <c:if test="${Goods.hostGoods != 1 }">
				    <input type="checkbox" name="hostGoods" value="1"/>热卖
				  </c:if>
				  
				  
				  <c:if test="${Goods.newGoods == 1 }">
					<input type="checkbox" name="newGoods" value="1" checked="checked"/>新品
				  </c:if>
				  <c:if test="${Goods.newGoods != 1 }">
					<input type="checkbox" name="newGoods" value="1"/>新品
				  </c:if>
				  
				  <c:if test="${Goods.saleGoods == 1 }">
					<input type="checkbox" name="saleGoods" value="1" checked="checked"/>特价
				  </c:if>
				  <c:if test="${Goods.saleGoods != 1 }">
					<input type="checkbox" name="saleGoods" value="1"/>特价
				  </c:if>
				  
				  <c:if test="${Goods.specialGoods ==1 }">
					<input type="checkbox" name="specialGoods" value="1" checked="checked"/>特别推荐
				  </c:if>
				  <c:if test="${Goods.specialGoods !=1 }">
					<input type="checkbox" name="specialGoods" value="1"/>特别推荐
				  </c:if>
				  
				</td>
			</tr>
    		<tr>
				<td><input type="button" onclick="update()" value="修改" /></td>
				<td><font color="red">${updateMessage}</font></td>
				<td><div></div></td>
			</tr>
    	</table>
    	</form>
    	<td></tr>
    	</table>
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