<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.hebeu.entity.*" %>
<% String path = request.getContextPath(); %>
<!DOCTYPE>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>修改菜单</title>
	<meta name="keywords" content="">
	<meta name="description" content="">

	<link rel="shortcut icon" href="favicon.ico">
	<link href="<%=path %>/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
	<link href="<%=path %>/css/font-awesome.css?v=4.4.0" rel="stylesheet">

	<!-- Data Tables -->
	<link href="<%=path %>/css/plugins/dataTables/dataTables.bootstrap.css"
		rel="stylesheet">
	<link href="<%=path %>/css/animate.css" rel="stylesheet">
	<link href="<%=path %>/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>修改菜单</h5>
					</div>
					<div class="ibox-content">
							<% 
								Menu menu = (Menu)request.getAttribute("menu");
							%>
							
						<form method="post" class="form-horizontal" id="commentForm" action="<%=path %>/menu/<%=menu.getId()%>/updateMenu.do">
							<div class="form-group">
								<label class="col-sm-3 control-label">名称</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="name"  value="<%=menu.getName()%>">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">排序</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="sort" value="<%= menu.getSort() %>"  required>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">菜单类型</label>
								<div class="col-sm-7">
									<select class="form-control m-b" name="hrefType" size="1" value="<%= menu.getHrefType() %>"   required>
										<option value="1">头标题</option>
										<option value="2">小标题</option>
										<option value="3">菜单</option>
										<option value="4">按钮</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">链接</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="href" value="<%=menu.getHref() %>">
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">权限标识</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="permission" value="<%=menu.getPermission()%>">
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-3 control-label">权限级别</label>
								<div class="col-sm-7">
									<select class="form-control m-b" name="isShow" size="1" required>
										<option value="1">是</option>
										<option value="2">否</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-3 control-label">备注</label>
								<div class="col-sm-7">
									<input type="text" class="form-control" placeholder="" name="remarks" value="<%=menu.getRemarks()%>">
								</div>
							</div>
							<div class="hr-line-dashed"></div>
							<div class="form-group">
								<div class="col-sm-4 col-sm-offset-8">
									<button class="btn btn-success" type="submit">修&nbsp;&nbsp;改</button>&nbsp;&nbsp;&nbsp;&nbsp;
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
	<script src="<%=path %>/js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=path %>/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=path %>/js/plugins/jeditable/jquery.jeditable.js"></script>
	
	<!-- Data Tables -->
	<script src="<%=path %>/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="<%=path %>/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- 自定义js -->
	<script src="<%=path %>/js/content.js?v=1.0.0"></script>
	
	<!-- 表单验证 -->
	<script src="<%=path %>/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="<%=path %>/js/plugins/validate/messages_zh.min.js"></script>
	
	<!-- layer javascript -->
    <script src="<%=path %>/js/plugins/layer/layer.min.js"></script>
	<script>
	$().ready(function() {
	    $("#commentForm").validate();
	});
	$.validator.setDefaults({
	    submitHandler: function() {
	    	parent.layer.alert('修改成功！', {icon: 1}),
	    	form.submit();
	    }
	});
	</script>
</body>
</html>
