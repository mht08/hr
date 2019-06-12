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
    <h1>菜单表管理</h1>
    <button onclick="add()">新增</button>
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
    $(function() {
    	$.ajax({
			type : "POST",
			url : "<%=path%>/menu/toListPage.do",
			dataType : "json",
			error : function(error) {
			},
			success : function(result) {
				console.log(result);
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
                { field: 'parentIdsStr',  title: '父节点' },
                { field: 'href',  title: '地址' },
               // {field: 'id', title: '编号', sortable: true, align: 'center'},
               // {field: 'parentId', title: '所属上级'},
                { field: 'hrefType',  title: '类型', sortable: true,  align: 'center', formatter: 'hrefTypeFormatter'  },
                { field: 'permission', title: '权限值'  },
                { field: 'operate', title: '操作', align: 'center', events : operateEvents, formatter: 'operateFormatter' },
            ],

            // bootstrap-table-treegrid.js 插件配置 -- start

            //在哪一列展开树形
            treeShowField: 'name',
            //指定父id列
            parentIdField: 'parentId',
            
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
                selectChilds(datas,row,"id","parentId",true);

                // 勾选父类
                selectParentChecked(datas,row,"id","parentId")

                // 刷新数据
                $table.bootstrapTable('load', datas);
            },

            onUncheck:function(row){
                var datas = $table.bootstrapTable('getData');
                selectChilds(datas,row,"id","parentId",false);
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
    function hrefTypeFormatter(value, row, index) {
        if (value === '1') {  return '头标题'; }
        if (value === '2') {  return '小标题'; }
        if (value === '3') {  return '菜单'; }
        if (value === '4') {  return '按钮'; }
        return '其他';
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
     * @param parentId 父id字段名
     */
    function selectChilds(datas,row,id,parentId,checked) {
        for(var i in datas){
            if(datas[i][parentId] == row[id]){
                datas[i].check=checked;
                selectChilds(datas,datas[i],id,parentId,checked);
            };
        }
    }

    function selectParentChecked(datas,row,id,parentId){
        for(var i in datas){
            if(datas[i][id] == row[parentId]){
                datas[i].check=true;
                selectParentChecked(datas,datas[i],id,parentId);
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
        /* alert("add 方法 , id = " + id); */
        if(id == null) {
        	id=0;
        }
        window.location.href = "<%=path%>/menu/"+id+"/addPage.do";
    }
    function del(id) {
    	var ss ="<%=path%>/menu/"+id+"/delete.do";
    	console.log(ss);
    	$.ajax({
    		type : "POST",
    		url : "<%=path%>/menu/"+id+"/delete.do",
    		
    		dataType : "json",
    		error : function(error) {
    		},
    		success : function(result) {
    			alert(result.message);
    			window.location.href=window.location.href;
    		}
    	});
    }
    function update(id) {
    	if(id == null) {
        	id=0;
        }
        window.location.href = "<%=path%>/menu/"+id+"/updatePage.do";
    }


</script>
</html>