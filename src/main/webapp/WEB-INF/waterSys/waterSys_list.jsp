<%--
  Created by IntelliJ IDEA.
  User: ThinkPad
  Date: 2017-07-11
  Time: 14:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>


<head>
    <title>Title</title>
    <%--<link rel="stylesheet" href="${basepath }/assets/zTree_v3-master/css/zTreeStyle/zTreeStyle.css" type="text/css">--%>
    <%--<script type="text/javascript" src="${basepath }/assets/jquery/jquery-1.9.1.min.js"></script>--%>
    <%--<script type="text/javascript" src="${basepath }/assets/zTree_v3-master/js/jquery.ztree.core.min.js"></script>--%>
    <%--<script type="text/javascript" src="${basepath }/assets/zTree_v3-master/js/jquery.ztree.excheck.min.js"></script>--%>

    <%--<script type="text/javascript" src="${basepath }/assets/jquery/jquery-1.9.1.min.js"></script>--%>
    <script type="text/javascript" src="${basepath }/static/js/listpage/jquery-2.0.3.min.js"></script>
    <link rel="stylesheet" href="${basepath }/static/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script type="text/javascript" src="${basepath }/static/js/listpage/jquery.ztree.core.min.js"></script>
    <script type="text/javascript" src="${basepath }/static/js/listpage/jquery.ztree.excheck.min.js"></script>

    <script type="text/javascript">

        jQuery(document).ready(function() {
            var url = "${basepath }/waterSys/getList";

            $.ajax({
                url : url,
                type : "post",
                data : {},
                dataType : "json",
                success : function(data) {

                    if (data == null || data.total == 0) {
                        return;
                    }

                    initTree(data);


//                    $.each(data, function(index, item) { //遍历返回的json
//                        console.log(item.stationName);
//                    });
                }
            });
        });





        //定义zTree各项参数
        var zTree;
        var setting = {
            view: {
                dblClickExpand: false,//双击节点时，是否自动展开父节点的标识
                showLine: true,//是否显示节点之间的连线
                fontCss:{'color':'black','font-weight':'bold'},//字体样式函数
                selectedMulti: false //设置是否允许同时选中多个节点
            },
//            check:{
//                //chkboxType: { "Y": "ps", "N": "ps" },
//                chkStyle: "checkbox",//复选框类型
//                enable: true //每个节点上是否显示 CheckBox
//            },
            data: {
                simpleData: {//简单数据模式
                    enable:true,
                    idKey: "id",
                    pIdKey: "stationId",
                    rootPId: ""
                }
            },
            callback: {
                beforeClick: function(treeId, treeNode) {
                    zTree = $.fn.zTree.getZTreeObj("user_tree");
                    if (treeNode.isParent) {
                        zTree.expandNode(treeNode);//如果是父节点，则展开该节点
                    }else{
                        zTree.checkNode(treeNode, !treeNode.checked, true, true);//单击勾选，再次单击取消勾选
                    }
                },
                //设置每个节点的点击事件
                onClick: zTreeOnClick
            }
        };

//        //数据模拟
//        var zNodes = [{id:1,name:"古黄河监测站",children:[
//            {id:1,name:"一号监测设备",pid:1},{id:2,name:"二号监测设备",pId:1}
//        ]},{id:2,name:"长江监测站",children:[
//            {id:3,name:"一号监测设备",pid:1},{id:4,name:"二号监测设备",pId:1}
//        ]}];

//        //初始化树状图
//        $(document).ready(function(){
//            $.fn.zTree.init($("#user_tree"), setting, zNodes);
//        });
        
        function initTree(zNodes) {
            $.fn.zTree.init($("#user_tree"), setting, zNodes);
        }


        
        function onCheck(e,treeId,treeNode){
//            var treeObj=$.fn.zTree.getZTreeObj("user_tree"),
//                    nodes=treeObj.getCheckedNodes(true),
//                    v="";
//            for(var i=0;i<nodes.length;i++){
//                v+=nodes[i].name + ",";
//                alert(nodes[i].id); //获取选中节点的值
//                console.log(nodes[i].id);
//            }
            var treeObj = $.fn.zTree.getZTreeObj("user_tree");
            var sNodes = treeObj.getSelectedNodes();
            if (sNodes.length > 0) {
                var isParent = sNodes[0].isParent;
            }
        }

        //节点点击事件
        function zTreeOnClick(event, treeId, treeNode) {
//            var treeObj = $.fn.zTree.getZTreeObj("user_tree");
//            var sNodes = treeObj.getSelectedNodes();
//            if (sNodes.length > 0) {
//                return false;
//            }else {
//                alert(treeNode.id);
//            }
            if (treeNode.level == 0){
                return false;//如果是父节点，则不发送请求
            }else{
                alert(treeNode.id);
            }
        }



    </script>
</head>
<body>
    <div style="width: 30%;float: left">
        <ul id="user_tree" class="ztree" style="border: 1px solid #617775;overflow-y: scroll;height: 90%;width: 300px"></ul>
    </div>
    <div>
        jsdijso
    </div>
    <div class="row">
        <iframe id='iframe' src="" width="100%" style='overflow-y :auto;border: 0px'>
        </iframe>
    </div>
</body>
</html>
