<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.hebeu.entity.*"%>
<%@ page import="edu.hebeu.entity.format.*"%>
<%@ page import="edu.hebeu.util.MTimeUtil"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>角色</title>
<meta name="keywords" content="">
<meta name="description" content="">

<link rel="shortcut icon" href="favicon.ico">
<link href="<%=path%>/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
<link href="<%=path%>/css/font-awesome.css?v=4.4.0" rel="stylesheet">

<!-- Data Tables -->
<link href="<%=path%>/css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="<%=path%>/css/animate.css" rel="stylesheet">
<link href="<%=path%>/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>角色列表</h5>
					</div>
					<div class="ibox-content">
					<div style="margin-bottom: 8px">
							<a href="<%=path %>/role/toAdd.do" class="btn btn-success">添加角色</a>
						</div>
						<table
							class="table table-striped table-bordered table-hover dataTables-example">
							<thead>
								<tr>
									<th>编号</th>
									<th>角色名称号</th>
									<th>英文名称</th>
									<th>角色类型</th>
									<th>数据范围</th>
									<th>是否系统数据</th>
									<th>是否可用</th>
									<th>创建者</th>
									<th>创建时间</th>
									<th>更新者</th>
									<th>更新时间</th>
									<th>备注信息</th>
									<th>删除标记</th>
									<th>管理</th>
								</tr>
							</thead>
							<tbody>
								<%
									List<RoleFormat> list = (List<RoleFormat>)request.getAttribute("roleList");
								                            	int index=1;
								                            	for(RoleFormat role : list){
								%>
								<tr class="gradeA">
									<td><%=index++%></td>
									<td><%=role.getName()%></td>
									<td><%=role.getEnname()%></td>
									<td><%=role.getRoleType()%></td>
									<td><%=role.getDataScope()%></td>
									<td><%=role.getIsSys()%></td>
									<td><%=role.getUseable()%></td>
									<td><%=role.getCreateBy()%></td>
									<td><%=role.getCreateDateStr()%></td>
									<td><%=role.getUpdateBy()%></td>
									<td><%=role.getUpdateDateStr()%></td>
									<td><%=role.getRemarks()%></td>
									<td><%=role.getDelFlag()%></td>
									<td><a
										href="<%=path%>/role/<%=role.getId()%>/toUpdateRole.do"
										class="btn btn-primary">修改</a>&nbsp;&nbsp; <a
										onclick="del(<%=role.getId()%>)"
										class="btn btn-danger delete">删除</a>&nbsp;&nbsp;</td>


								</tr>
								<%
									}
								%>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- </form> -->
	</div>
	<!-- 全局js -->
	<script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=path%>/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=path%>/js/plugins/jeditable/jquery.jeditable.js"></script>

	<!-- Data Tables -->
	<script src="<%=path%>/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="<%=path%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- 自定义js -->
	<script src="<%=path%>/js/content.js?v=1.0.0"></script>
	<script>
	function del(id){
		parent.layer.confirm('确认删除？', {
		    btn: ['确认','取消'], //按钮
		    shade: false //不显示遮罩
		}, function(){
		    parent.layer.msg('删除成功！', {icon: 1});
		   // location.href="./"+ id +"/delete.do";
		   
		   var ss = "./"+ id +"/delete.do";
		   console.log("自己看下跳转的url"+ss);
		    // 所有的URL跳转写全路径，不要用上面的写法。
		    // 你上面的直接跳到EMPLOYEE 方法去了
		    window.location.href = "<%=path%>/role/"+id+"/delete.do";
		});
	}
	
	
	
	
	
	
        $(document).ready(function () {
            $('.dataTables-example').dataTable();
            
            var oTable = $('#editable').dataTable();
            
            oTable.$('td').editable('../example_ajax.php', {
                "callback": function (sValue, y) {
                    var aPos = oTable.fnGetPosition(this);
                    oTable.fnUpdate(sValue, aPos[0], aPos[1]);
                },
                "submitdata": function (value, settings) {
                    return {
                        "row_id": this.parentNode.getAttribute('id'),
                        "column": oTable.fnGetPosition(this)[2]
                    };
                },
                "width": "90%",
                "height": "100%"
            });
        });
    </script>
</body>
</html>
