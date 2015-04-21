<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/WEB-INF/pages/commons/libs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/pages/commons/meta.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../../../resources/css/user/usermanage.css" type="text/css" rel="stylesheet">

<script type="text/javascript" src="../../../resources/js/user/role.js"></script>
<script type="text/javascript">
	var funcIds = ${funcIds};
	$().ready(function() {
		latte.xk.role.initUpdateRole(funcIds);
	 });
</script>
    <title>${page_title}</title>
</head>
<body>
<div class="container" >
<%@include file="/WEB-INF/pages/commons/header.jsp" %>
	<div class="mainWrap">
	    <div class="mainIndex">
	    	 <%@include file="/WEB-INF/pages/user/left-menu.jsp" %>
	      	<div class="mainCon mainCon01">
	        	<div class="mainTitle">
	        		 编辑角色
	        		 <div class="operBtn" style="padding:5px 0;float:right;margin-right:5px;font-size:12px;">
	               	<span id="btn_return"  class="BtnWhite22" onclick="javascript:window.history.back(-1);">
						<a href="javascript:void(0)" class="btnSave" ><span class="returnIco"></span>返回</a>
					</span>
				</div>
	        	</div>
				<div class=" content_main">
			   		<div class="mainPart">
			          <div class="conBox">
			            <div class="formCon">
			                <div class="formList"> 
			                  <label><em>*</em>角色名称：</label>
			                  <div class="role_val">
			                  <input type="text" class="usermanageInput" name="name" id="name" size="35" value="${role.name }" disabled="disabled"/>
								</div>		
			                </div>
			                <div class="formList"> 
			                  <label>角色说明：</label>
			                  <div class="role_val">
			                  <input type="text" name="remark" id="remark"
									class="inputText" size="35" value="${role.remark }" maxlength="120"/>
							  <label for="name" id="remark_label" class="error"></label>
								</div>		
			                </div>
			                <div class="formList">
			                  <label>功能权限：</label>
			                  <div class="info info01">
									<div class="info">
										<div class="treewrap">
						                      <ul class="treeRoot">
												<c:forEach items="${funcMap }" var="firstFuncMap">
													<li class="treeLi">
						                          		<div class="tree1Node">
						                            		<div class="tree1Info">
						                              			<a href="javascript:latte.xk.role.showOrHiddenTree('${firstFuncMap.key.id }');" id="first_${firstFuncMap.key.id }" class="tree1Aarrow">&nbsp;</a>
						                              			<span class="tree1NodeFolder">
						                              			<input type="checkbox" name="funclist"  value="${firstFuncMap.key.id }" id="funclist${firstFuncMap.key.id }"
						                              			onclick="latte.xk.role.checkFirstFuncSelected('${firstFuncMap.key.id }');"/>
							                              		</span>
						                              			<span class="tree1NodeName current">${firstFuncMap.key.name }</span>
						                            		</div>
						                          		</div>
						                          		<ul class="treeUl" id="second_${firstFuncMap.key.id }">
														<c:forEach items="${firstFuncMap.value }" var="secondFuncMap">
								                          <li class="treeLi">
								                            <div class="tree1Node">
								                              <div class="tree1Info">
								                                <span class="tree1NodeIndent">
								                                  <b class=""></b>
								                                </span>
								                                <span class="tree1NodeFolder">
								                                	<input type="checkbox" name="funclist" value="${secondFuncMap.key.id }" 
								                                			id="funclist${secondFuncMap.key.id }" 
							                                  				onclick="latte.xk.role.checkSecondFuncSelected('${firstFuncMap.key.id }', '${secondFuncMap.key.id }');"/>
								                                </span>
								                                <span class="tree1NodeName">${secondFuncMap.key.name }</span>
								                              </div>
								                            </div>
								                            <ul class="treeUl" id="third_${secondFuncMap.key.id }">
								                            
								                              <li class="treeLi">
								                                <div class="tree1Node">
															<c:forEach items="${secondFuncMap.value }" var="funclist" varStatus="func">
								                                  <div class="tree1Info">
								                                    <span class="tree1NodeIndent">
								                                      <b class=""></b>
								                                      <b class=""></b>
								                                    </span>
								                                    <span class="tree1NodeFolder">
								                                    	<input type="checkbox" name="funclist" value="${funclist.id }" id="funclist${funclist.id }" 
									                                  			onclick="latte.xk.role.checkThirdFuncSelected('${firstFuncMap.key.id }', '${secondFuncMap.key.id}', '${funclist.id }');"/>
								                                    </span>
								                                    <span class="tree1NodeName">${funclist.name }</span>
								                                  </div>
					                            			</c:forEach>
								                                </div>
								                              </li>
								                            </ul>
								                          </li>
						             					</c:forEach>       
								                        </ul>
						                        </li>						                          
						      					</c:forEach>
						                      </ul>
				                      	</div>
									</div>
			                  </div>
			                </div>
			   				
			              </div>
         		 		</div>
         		 	</div>
         		 	<input type="hidden" name="id" id="id" value="${role.id }" /> 
         		 	<input type="hidden" name="stats" id="stats" value="${role.stats }" /> 
		          <div class="conBtnWrap conBtnWrap01">
		             <div class="BtnOrange30">
		             	<a id="btnSave" class="btnSave" href="javascript:latte.xk.role.roleUpdate()">确认</a>
		             </div> 
		             <div class="BtnGray30">
		             	<a id="btnSave" class="btnSave" href="javascript:latte.xk.role.cancelSelected('${role.remark }');">重置</a>
		             </div>
		          </div>
		          <div style="height: 50px;"></div>
		</div>
	</div></div></div>
	<%@include file="/WEB-INF/pages/commons/footer.jsp" %>
</div>
</body>
</html>