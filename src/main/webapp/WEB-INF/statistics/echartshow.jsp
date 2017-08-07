<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%
    pageContext.setAttribute("basepath", request.getContextPath());
%>
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <title>曲线图</title>
    <script src="${basepath}/static/js/map/jquery-2.0.3.min.js"></script>
    <script src="${basepath}/static/js/echarts-2.2.7/build/dist/echarts.js"></script>
</head>
<body >
<div align="center">
    <div id="main" style="height:400px"></div>
</div>
<script type="text/javascript">
    require.config({
        paths: {
            echarts: '${basepath}/static/js/echarts-2.2.7/build/dist'
        }
    });
    require(
            [
                'echarts',                        // 这个“echarts”其实就是上面的paths中的echarts
                'echarts/chart/line',             // 按需加载所需图表，line折线图
            ],

            function (ec) {
                var myChart = ec.init(document.getElementById('main'));
                myChart.showLoading();
                var names=[];    //用来盛放X轴坐标值
                var nums=[];    //用来盛放Y坐标值
                //加载数据
                //日期转换
                function getLocalTime(ns) {
                    var tt=new Date(parseInt(ns/1000) * 1000).toLocaleString().replace(/:\d{1,2}$/,' ');
                    return tt;
                }
                jQuery.ajax({
                    url: "${basepath}/statistics/getAllEcharts",
                    type: 'get',
                    dataType: 'json',
                    success: function (jsons) {
                        var result = jsons.data;
                        if (result) {
                            for(var i=0;i<result.length;i++){
                                names.push(getLocalTime(result[i].monitorTime));    //挨个取出类别并填入类别数组
                            }
                            for(var i=0;i<result.length;i++){
                                nums.push(result[i].data);    //挨个取出销量并填入销量数组
                            }
                            myChart.hideLoading();    //隐藏加载动画

                            myChart.setOption({
                                title: {// 图表标题，可以通过show:true/false控制显示与否，还有subtext:'二级标题'等
                                    text: '水质变化曲线图'
                                },
                                tooltip: {// 这个是鼠标浮动时的工具条
                                    trigger: 'axis'
                                },
                                legend: {// 图例，每条折线或者项对应的示例
                                    data: ['水质等级']
                                },
                                toolbox: {
                                    show : true,
                                    feature : {
                                        mark : {show: true},
                                        dataView : {show: true, readOnly: false},
                                        magicType : {show: true, type: ['line', 'bar']},
                                        restore : {show: true},
                                        saveAsImage : {show: true}
                                    }
                                },
                                xAxis: [
                                    {
                                        type: 'category',
                                        boundaryGap: false,
                                        data: names// X轴的定义
                                    }
                                ],
                                yAxis: [
                                    {
                                        type: 'value'// Y轴的定义
                                    }
                                ],
                                series: [
                                    {
                                        name:'水质等级',
                                        type:'line',
                                        data:nums
                                    }
                                ]// Y轴数据
                            });
                        }
                    },
                    error: function () {
                        alert("数据加载失败！请检查数据链接是否正确");
                        myChart.hideLoading();
                    }
                });
                // 初次加载图表(无数据)
//                myChart.setOption(option);
            }
    );
</script>
</body>
</html>