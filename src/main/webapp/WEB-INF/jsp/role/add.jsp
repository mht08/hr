<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.hebeu.entity.*"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>HR医院 - 添加角色</title>
<meta name="keywords" content="">
<meta name="description" content="">

<!-- ZTree js -->

<%-- <script type="text/javascript" src="<%=path%>/js/jquery-1.4.4.min.js"></script> --%>

<link rel="shortcut icon" href="favicon.ico">
<link href="<%=path%>/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="<%=path%>/css/font-awesome.css?v=4.4.0" rel="stylesheet">

<!-- Data Tables -->
<link href="<%=path%>/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=path%>/css/animate.css" rel="stylesheet">
<link href="<%=path%>/css/style.css?1" rel="stylesheet">

<script src="<%=path%>/js/jquery.min.js?1"></script>
<!-- ZTree css -->
<link rel="stylesheet" href="<%=path%>/css/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/css/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.js"></script> 
</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<% 
						Long id = (Long)request.getAttribute("id");
					%>
					<div class="ibox-title">
						<%
							if(id == null) {
						%>
							<h5>添加角色</h5>
						<% } else { %>
							<h5>修改角色</h5>
						<% } %>
					</div>
					<div class="ibox-content">
						<form method="post" class="form-horizontal" id="commentForm" action="<%=path%>/role/add.do">
							<input type="hidden" id="id" name="id" value="<%=id != null ? id : "" %>">
							<div class="form-group">
								<label class="col-sm-3 control-label">角色名称</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="name" id="name">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">英文名称</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="enname" id="enname">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">角色类型</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="roleType" id="roleType" >
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">数据范围</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="dataScope" id="dataScope" >
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-3 control-label">是否系统数据</label>
								<div class="col-sm-7">
									<select class="form-control m-b" name="isSys" size="1" id="isSys" required>
										<option value="1">是</option>
										<option value="2">否</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">是否可用</label>
								<div class="col-sm-7">
									<select class="form-control m-b" name="useable" size="1" id="useable" required>
										<option value="1">是</option>
										<option value="2">否</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">分配菜单</label>
								<div class="col-sm-7">
									<input type="hidden" name="menuIds" id="menuIds">
									<ul id="treeDemo" class="ztree"></ul>
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-8">
									<button class="btn btn-success" type="submit">添&nbsp;&nbsp;加</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button class="btn btn-white" type="reset">取&nbsp;&nbsp;消</button>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- 全局js -->
	<%-- <script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script> --%>
	<script src="<%=path%>/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=path%>/js/plugins/jeditable/jquery.jeditable.js"></script>

	<!-- Data Tables -->
	<script src="<%=path%>/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="<%=path%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- 自定义js -->
	<script src="<%=path%>/js/content.js?v=1.0.0"></script>

	<!-- 表单验证 -->
	<script src="<%=path%>/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="<%=path%>/js/plugins/validate/messages_zh.min.js"></script>

	<!-- layer javascript -->
	<script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
	
	<script>
		var setting = {
			check: {
				enable: true,
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: treenodeClick
			}
		};
		
		$(document).ready(function(){
			$("#commentForm").validate();
			var id = $("#id").val();
			$.ajax({
				type : "POST",
				url : "<%=path%>/menu/roleMenu.do?roleId="+id,
				dataType : "json",
				error : function(error) {
				},
				success : function(result) {
					// 初始化菜单列表	
					$.fn.zTree.init($("#treeDemo"), setting, result.data);
					// 获取所有菜单选中节点
					treenodeClick();
				}
			});
			
			if(id != "") {
				$.ajax({
					type : "POST",
					url : "<%=path%>/role/getById.do?id="+id,
					dataType : "json",
					error : function(error) {
					},
					success : function(result) {
						if(result.code === '0') {
							$("#dataScope").val(result.data.dataScope);
							$("#enname").val(result.data.enname);
							$("#isSys").val(result.data.isSys);
							$("#name").val(result.data.name);
							$("#remarks").val(result.data.remarks);
							$("#roleType").val(result.data.roleType);
							$("#useable").val(result.data.useable);
						}
					}
				});
			}
		});
	
		$.validator.setDefaults({
			submitHandler : function() {
				parent.layer.alert('添加成功！', {
					icon : 1
				}), form.submit();
			}
		});
		
		// 获取所有选中节点
		function treenodeClick() {
			var treeObj=$.fn.zTree.getZTreeObj("treeDemo");
            var nodes=treeObj.getCheckedNodes(true);
            var ids="";
            for(var i=0;i<nodes.length;i++){
            	console.log(nodes);
            	if(ids != "") {
            		ids+=",";
            	}
            	ids+=nodes[i].id;
            }
            $("#menuIds").val(ids);
		}
		
	</script>
</body>
</html>
