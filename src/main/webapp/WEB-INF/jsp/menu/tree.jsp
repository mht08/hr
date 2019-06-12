
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - checkbox</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=path%>/css/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=path%>/css/ztree/zTreeStyle.css" type="text/css">
	<%-- <script type="text/javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script> --%>
	<script src="<%=path%>/js/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.js"></script>
	
	<SCRIPT type="text/javascript">
		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			}
		};

		var zNodes =[
			{ id:1, pId:0, name:"随意勾选 1", open:true},
			{ id:11, pId:1, name:"随意勾选 1-1", open:true},
			{ id:111, pId:11, name:"随意勾选 1-1-1"},
			{ id:112, pId:11, name:"随意勾选 1-1-2"},
			{ id:12, pId:1, name:"随意勾选 1-2", open:true},
			{ id:121, pId:12, name:"随意勾选 1-2-1"},
			{ id:122, pId:12, name:"随意勾选 1-2-2"},
			{ id:2, pId:0, name:"随意勾选 2", checked:true, open:true},
			{ id:21, pId:2, name:"随意勾选 2-1"},
			{ id:22, pId:2, name:"随意勾选 2-2", open:true},
			{ id:221, pId:22, name:"随意勾选 2-2-1", checked:true},
			{ id:222, pId:22, name:"随意勾选 2-2-2"},
			{ id:23, pId:2, name:"随意勾选 2-3"},
			{ id:24, pId:2, name:"随意勾选 2-3"},
			{ id:25, pId:2, name:"随意勾选 2-3"},
			{ id:26, pId:2, name:"随意勾选 2-3"},
			{ id:27, pId:2, name:"随意勾选 2-3"},
			{ id:28, pId:2, name:"随意勾选 2-3"},
			{ id:29, pId:2, name:"随意勾选 2-3"},
			{ id:30, pId:2, name:"随意勾选 2-3"},
			{ id:31, pId:2, name:"随意勾选 2-3"},
			{ id:32, pId:2, name:"随意勾选 2-3"},
			{ id:33, pId:2, name:"随意勾选 2-3"},
			{ id:34, pId:2, name:"随意勾选 2-3"},
			{ id:35, pId:2, name:"随意勾选 2-3"},
			{ id:36, pId:2, name:"随意勾选 2-3"},
			{ id:37, pId:2, name:"随意勾选 2-3"},
			{ id:38, pId:2, name:"随意勾选 2-3"},
			{ id:39, pId:2, name:"随意勾选 2-3"},
			{ id:40, pId:2, name:"随意勾选 2-3"},
			{ id:41, pId:2, name:"随意勾选 2-3"},
			{ id:42, pId:2, name:"随意勾选 2-3"},
			{ id:43, pId:2, name:"随意勾选 2-3"},
			{ id:44, pId:2, name:"随意勾选 2-3"},
			{ id:45, pId:2, name:"随意勾选 2-3"},
			{ id:46, pId:2, name:"随意勾选 2-3"},
			{ id:47, pId:2, name:"随意勾选 2-3"},
			{ id:48, pId:2, name:"随意勾选 2-3"},
			{ id:49, pId:2, name:"随意勾选 2-3"}
		];
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
	</SCRIPT>
</HEAD>

<BODY>
<ul id="treeDemo" class="ztree"></ul>
	<!-- <div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div> -->
</BODY>
</HTML>