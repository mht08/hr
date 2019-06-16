<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="edu.hebeu.entity.*"%>
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
						<h5>分配人员</h5>
					</div>
					<div class="ibox-content">
						<div style="margin-bottom: 8px">
							<button onclick="test()">添加</button>
						</div>
					</div>
					<table id="table"></table>
				</div>
			</div>
		</div>
	</div>
	<!-- </form> -->
	<!-- 全局js -->
	<script src="<%=path%>/js/jquery.min.js?v=2.1.4"></script>
	<script src="<%=path%>/js/bootstrap.min.js?v=3.3.6"></script>
	<script src="<%=path%>/js/plugins/jeditable/jquery.jeditable.js"></script>

	<!-- Data Tables -->
	<script src="<%=path%>/js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="<%=path%>/js/plugins/dataTables/dataTables.bootstrap.js"></script>

	<!-- 自定义js -->
	<script src="<%=path%>/js/content.js?v=1.0.0"></script>
	
	<script src="<%=path%>/js/plugins/bootstrap-table/bootstrap-table.min.js?1"></script>
	<script src="<%=path%>/js/plugins/treegrid/bootstrap-table-treegrid.js?1"></script>
	<script src="<%=path%>/js/jquery.treegrid.min.js"></script>
	<script>
	  var $table;
	  function test() {
	        var selRows = $table.bootstrapTable("getSelections");
	        if(selRows.length == 0){
	            alert("请至少选择一行");
	            return;
	        }

	        var postData = "";
	        $.each(selRows,function(i) {
	            postData +=  this.id;
	            if (i < selRows.length - 1) {
	                postData += "， ";
	            }
	        });
	        alert("你选中行的 id 为："+postData);

	    }
	
        $(document).ready(function () {
        	
        	$.ajax({
				type : "POST",
				url : "<%=path%>/employee/list.do?pageNo=1",
				dataType : "json",
				error : function(error) {
				},
				success : function(result) {
					console.log(result);
					InitMainTable(result.records);
				}
			});
        	
        });
        
        //初始化bootstrap-table的内容
        function InitMainTable (data) {
            //记录页面bootstrap-table全局变量$table，方便应用
            var queryUrl = '<%=path%>/employee/list.do?pageNo=1';
            $table = $('#table').bootstrapTable({
                url: queryUrl,                      //请求后台的URL（*）
                method: 'GET',                      //请求方式（*）
                //toolbar: '#toolbar',              //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                pagination: true,                   //是否显示分页（*）
                sortable: true,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页,并记录
                //pageSize: records,                     //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
                search: false,                      //是否显示表格搜索
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列（选择显示的列）
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                //height: 500,                      //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
                uniqueId: "ID",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                   //是否显示详细视图和列表视图的切换按钮
                cardView: false,                    //是否显示详细视图
                detailView: false,                  //是否显示父子表
                data: data,
                //得到查询的参数
                queryParams : function (params) {
                	console.log(params);
                    //这里的键的名字和控制器的变量名必须一致，这边改动，控制器也需要改成一样的
                    /* var temp = {   
                        rows: params.limit,                         //页面大小
                        page: (params.offset / params.limit) + 1,   //页码
                        sort: params.sort,      //排序列名  
                        sortOrder: params.order //排位命令（desc，asc） 
                    };
                    return temp; */
                },
                columns: [{
                    checkbox: true,  
                    visible: true                  //是否显示复选框  
                }, {
                    field: 'name',
                    title: '姓名',
                    sortable: true
                }, {
                    field: 'photo',
                    title: '手机',
                    sortable: true
                }/* , {
                    field: 'Email',
                    title: '邮箱',
                    sortable: true,
                    formatter: emailFormatter
                }, {
                    field: 'Homepage',
                    title: '主页',
                    formatter: linkFormatter
                }, {
                    field: 'Hobby',
                    title: '兴趣爱好'
                }, {
                    field: 'Gender',
                    title: '性别',
                    sortable: true
                }, {
                    field: 'Age',
                    title: '年龄'
                }, {
                    field: 'BirthDate',
                    title: '出生日期',
                    formatter: dateFormatter
                }, {
                    field: 'Height',
                    title: '身高'
                }, {
                    field: 'Note',
                    title: '备注'
                }, {
                    field:'ID',
                    title: '操作',
                    width: 120,
                    align: 'center',
                    valign: 'middle',
                    formatter: actionFormatter
                },  */],
                onLoadSuccess: function () {
                },
                onLoadError: function () {
                    showTips("数据加载失败！");
                },
                onDblClickRow: function (row, $element) {
                    var id = row.ID;
                    EditViewById(id, 'view');
                },
            });
        };
    </script>
</body>
</html>
