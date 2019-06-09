<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE HTML>
<html lang="zh-cn">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width,initial-scale=1.0" name="viewport">
    <meta content="yes" name="apple-mobile-web-app-capable">
    <meta content="black" name="apple-mobile-web-app-status-bar-style">
    <meta content="telephone=no" name="format-detection">
    <meta content="email=no" name="format-detection">
    <title>系统管理</title>
    <link href="<%=path %>/css/bootstrap.min.css" rel="stylesheet">
    <link href="<%=path %>/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link rel="stylesheet" href="<%=path %>/css/jquery.treegrid.min.css">
</head>

<body>
<div class="container">
    <h1>树形表格 ： Table Treegrid</h1>
    <table id="table"></table>
    <br/>
    <button onclick="test()">选择</button>
</div>
</body>

<script src="<%=path%>/js/jquery.min.js"></script>
<script src="<%=path%>/js/plugins/bootstrap-table/bootstrap-table.min.js?1"></script>
<script src="<%=path%>/js/plugins/treegrid/bootstrap-table-treegrid.js?1"></script>
<script src="<%=path%>/js/jquery.treegrid.min.js"></script>
  
<script type="text/javascript">
    var $table = $('#table');
    var json = '[{"id":1,"pid":0,"status":1,"name":"用户管理","permissionValue":"open:user:manage"},' +
       /*  '{"id":2,"pid":0,"status":1,"name":"系统管理","permissionValue":"open:system:manage"},' +
        '{"id":3,"pid":1,"status":1,"name":"新增用户","permissionValue":"open:user:add"},' +
        '{"id":4,"pid":1,"status":1,"name":"修改用户","permissionValue":"open:user:edit"},' +
        '{"id":5,"pid":1,"status":0,"name":"删除用户","permissionValue":"open:user:del"},' +
        '{"id":6,"pid":2,"status":1,"name":"系统配置管理","permissionValue":"open:systemconfig:manage"},' +
        '{"id":7,"pid":6,"status":1,"name":"新增配置","permissionValue":"open:systemconfig:add"},' +
        '{"id":8,"pid":6,"status":1,"name":"修改配置","permissionValue":"open:systemconfig:edit"},' +
        '{"id":9,"pid":6,"status":0,"name":"删除配置","permissionValue":"open:systemconfig:del"},' +
        '{"id":10,"pid":2,"status":1,"name":"系统日志管理","permissionValue":"open:log:manage"},' +
        '{"id":11,"pid":10,"status":1,"name":"新增日志","permissionValue":"open:log:add"},' +
        '{"id":12,"pid":10,"status":1,"name":"修改日志","permissionValue":"open:log:edit"},' + */
        '{"id":13,"pid":10,"status":0,"name":"删除日志","permissionValue":"open:log:del"}]';
    var data = JSON.parse(json); 
   	console.log(data);

    $(function() {
    	$.ajax({
			type : "POST",
			url : "<%=path%>/employee/treeJson.do",
			dataType : "json",
			error : function(error) {
			},
			success : function(result) {
				console.log(result.data);
				initData(result.data);
			},
		});
    });
    
    function initData(data) {
    	console.log(data);
    	$table.bootstrapTable({
            data:data,
            idField: 'id',
            dataType:'jsonp',
            columns: [
                { field: 'check',  checkbox: true, formatter: function (value, row, index) {
                        if (row.check == true) {
                           // console.log(row.serverName);
                            //设置选中
                            return {  checked: true };
                        }
                    }
                },
                { field: 'name',  title: '名称' },
               // {field: 'id', title: '编号', sortable: true, align: 'center'},
               // {field: 'pid', title: '所属上级'},
                { field: 'status',  title: '状态', sortable: true,  align: 'center', formatter: 'statusFormatter'  },
                { field: 'permissionValue', title: '权限值'  },
                { field: 'operate', title: '操作', align: 'center', events : operateEvents, formatter: 'operateFormatter' },
            ],

            // bootstrap-table-treegrid.js 插件配置 -- start

            //在哪一列展开树形
            treeShowField: 'name',
            //指定父id列
            parentIdField: 'pid',
            
            onResetView: function(data) {
                //console.log('load');
                $table.treegrid({
                    initialState: 'collapsed',// 所有节点都折叠
                    // initialState: 'expanded',// 所有节点都展开，默认展开
                    treeColumn: 1,
                    // expanderExpandedClass: 'glyphicon glyphicon-minus',  //图标样式
                    // expanderCollapsedClass: 'glyphicon glyphicon-plus',
                    onChange: function() {
                        $table.bootstrapTable('resetWidth');
                    }
                });

                //只展开树形的第一级节点
                $table.treegrid('getRootNodes').treegrid('expand');

            },
            onCheck:function(row){
                var datas = $table.bootstrapTable('getData');
                // 勾选子类
                selectChilds(datas,row,"id","pid",true);

                // 勾选父类
                selectParentChecked(datas,row,"id","pid")

                // 刷新数据
                $table.bootstrapTable('load', datas);
            },

            onUncheck:function(row){
                var datas = $table.bootstrapTable('getData');
                selectChilds(datas,row,"id","pid",false);
                $table.bootstrapTable('load', datas);
            },
            // bootstrap-table-treetreegrid.js 插件配置 -- end
        });
    }

    // 格式化按钮
    function operateFormatter(value, row, index) {
        return [
            '<button type="button" class="RoleOfadd btn-small  btn-primary" style="margin-right:15px;"><i class="fa fa-plus" ></i>&nbsp;新增</button>',
            '<button type="button" class="RoleOfedit btn-small   btn-primary" style="margin-right:15px;"><i class="fa fa-pencil-square-o" ></i>&nbsp;修改</button>',
            '<button type="button" class="RoleOfdelete btn-small   btn-primary" style="margin-right:15px;"><i class="fa fa-trash-o" ></i>&nbsp;删除</button>'
        ].join('');

    }
    // 格式化类型
    function typeFormatter(value, row, index) {
        if (value === 'menu') {  return '菜单';  }
        if (value === 'button') {  return '按钮'; }
        if (value === 'api') {  return '接口'; }
        return '-';
    }
    // 格式化状态
    function statusFormatter(value, row, index) {
        if (value === 1) {
            return '<span class="label label-success">正常</span>';
        } else {
            return '<span class="label label-default">锁定</span>';
        }
    }

    //初始化操作按钮的方法
    window.operateEvents = {
        'click .RoleOfadd': function (e, value, row, index) {
            add(row.id);
        },
        'click .RoleOfdelete': function (e, value, row, index) {
            del(row.id);
        },
        'click .RoleOfedit': function (e, value, row, index) {
            update(row.id);
        }
    };
</script>
<script>
    /**
     * 选中父项时，同时选中子项
     * @param datas 所有的数据
     * @param row 当前数据
     * @param id id 字段名
     * @param pid 父id字段名
     */
    function selectChilds(datas,row,id,pid,checked) {
        for(var i in datas){
            if(datas[i][pid] == row[id]){
                datas[i].check=checked;
                selectChilds(datas,datas[i],id,pid,checked);
            };
        }
    }

    function selectParentChecked(datas,row,id,pid){
        for(var i in datas){
            if(datas[i][id] == row[pid]){
                datas[i].check=true;
                selectParentChecked(datas,datas[i],id,pid);
            };
        }
    }

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

    function add(id) {
        alert("add 方法 , id = " + id);
    }
    function del(id) {
        alert("del 方法 , id = " + id);
    }
    function update(id) {
        alert("update 方法 , id = " + id);
    }


</script>
</html>