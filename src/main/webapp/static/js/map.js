$(function(){
    var map = new MachMap();
    Marker.init(map.map);
    Heatmap.init(map.map);   
});
//-------------------------------------------------------------------------------------------
function Marker(){}
Marker.init=function(map){
    $.ajax({
        type:"GET",
        url:"../../map/getStationList",
        dataType:"json",
        success:function(data,status){
            console.log(status);
            var points = data.data;
            console.log(points);
            for(var i=0;i<points.length;i++){                                   
                var point = Marker.strUtility(points[i].coordinate);
                var lng = point[0];
                var lat = point[1];
                //创建覆盖物
                var pt = new BMap.Point(lng,lat);
                var myIcon = new BMap.Icon("../images/sewage.png", new BMap.Size(50,50));
                var marker = new BMap.Marker(pt,{icon:myIcon});  // 创建覆盖物
                var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
                marker.addEventListener("click", function(){    //添加左键单击事件
                    map.setCenter(pt);                          //地图中心点变为监测点
                    map.setZoom(22);                            //地图缩放等级更改为20，比例尺最小。
                    this.openInfoWindow(infoWindow);            //打开覆盖物的信息窗口
                    //图片加载完毕重绘infowindow
                    document.getElementById('imgDemo').onload = function (){
                        infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
                    };
                });
                map.addOverlay(marker);              // 将覆盖物添加到地图中
            }
        },
        error:function(data,status){
            console.log(status);
        }
    });
    
};
Marker.strUtility = function(s){      
    s = s.replace("(","");
    s = s.replace(")","");
    var items = s.split(",");
    return items; 
} 
//-------------------------------------------------------------------------------------------
function Heatmap(){}
Heatmap.init = function(map){
    $.ajax({
        type:"GET",
        url:"../../map/heatmap_points", //相对路径，相对于引用该js文件的页面的路径
        dataType:"json",
        success:function(points,status){                     
            if(!Heatmap.isSupportCanvas()){                         //添加热力图图层
    	        alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
            }else{       
                map.addOverlay(Heatmap.heatmapOverlay);
                // console.log(points.points); 
                Heatmap.heatmapOverlay.setDataSet({data:points.points,max:300});
                Heatmap.openHeatmap();
            }
        },
        error:function(status){
            console.log(status);
        }
    });
};
Heatmap.heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});
//是否显示热力图
Heatmap.openHeatmap=function (){
    Heatmap.heatmapOverlay.show();
}
Heatmap.closeHeatmap=function(){
    Heatmap.heatmapOverlay.hide();
}
//判断浏览区是否支持canvas
Heatmap.isSupportCanvas=function(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
} 
//----------------------------------------------------------------------------------------------------------
function MachMap() {
    this.map = this.initMap(this.containerId);
}
MachMap.prototype.containerId = "map_container";
MachMap.prototype.map;
// 根据地图容器的ID,初始化地图
MachMap.prototype.initMap = function (containerID) {
    var map = new BMap.Map("map_container");            // 创建地图实例
    var point = new BMap.Point(117.636257, 34.022178);  // 创建中心点坐标   
    map.centerAndZoom(point, 15);                       // 初始化地图，设置中心点坐标和地图级别
    map.setMapType(BMAP_HYBRID_MAP);                //设置为卫星图
    map.enableScrollWheelZoom(true);                //鼠标滑动轮子可以滚动
    map.enableKeyboard(true);                       //启用键盘操作
    map.addControl(new BMap.MapTypeControl());      //添加地图类型控件
    map.addControl(new BMap.NavigationControl());   // 添加平移缩放控件
    map.addControl(new BMap.ScaleControl());        // 添加比例尺控件
    map.addControl(new BMap.OverviewMapControl());  //添加缩略地图控件   
    return map;
}      
//---------------------------------------------------------------------------------------------------------------   
var sContent =
    "<table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">"+
    "<tr>"+
    "<td><table background=\"images/index/b06_w.gif\" style=\"margin-top:5px; margin-left:18px;\" width=\"317\" height=\"270\" border=\"0\" cellpadding=\"0\" cellspacing=\"6\">"+
    "<tr>"+
    "<td style=\"height: 209px\" valign=\"top\">"+
    "<table width=\"317\" border=\"1\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\">"+
    "<tr>"+
    "<td valign=\"top\"><div align=\"center\" class=\"style_2\"><strong><span id=\"title\"></span>水质自动监测站</strong></div></td>"+
    "</tr>"+
    "<tr>"+
    "<td valign=\"top\">"+
    "<table width=\"315\" border=\"1\" align=\"center\" cellpadding=0 cellspacing=0 class=\"table1 style_2\">"+
    "<tr>"+
    "<td width=\"81\" height=\"30\" bgcolor=\"#f0f0f0\" align=\"center\"><strong>测量时间</strong></td>"+
    "<td width=\"82\" height=\"20\" bgcolor=\"#FFFFFF\" align=\"center\"><strong>项目</strong></td>"+
    "<td width=\"44\" bgcolor=\"#f0f0f0\" align=\"center\"><strong>测量值</strong></td>"+
    "<td width=\"53\" bgcolor=\"#FFFFFF\" align=\"center\"><strong>水质类别</strong></td>"+
    "<td width=\"53\" bgcolor=\"#FFFFFF\" align=\"center\"><strong>Ⅲ类标准</strong></td>"+
    "</tr>"+
    "<tr>"+
    "<td width=\"81\" height=\"30\" rowspan=\"2\" bgcolor=\"#f0f0f0\" class=\"style_2\" align=\"center\"><strong><span id=\"date\"></span></strong></td>"+
    "<td width=\"82\" height=\"20\" bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>pH</strong></div></td>"+
    "<td width=\"44\" bgcolor=\"#f0f0f0\" align=\"center\"><strong><span id=\"pH\"></span></strong></td>"+
    "<td width=\"53\" bgcolor=\"#FFFFFF\" align=\"center\"><strong><span id=\"levelpH\"></span></strong></td>"+
    "<td width=\"53\" bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>6-9</strong></div></td>"+
    "</tr>"+
    "<tr>"+
    "<td height=\"20\" bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>溶解氧</strong></div></td>"+
    "<td bgcolor=\"#f0f0f0\" align=\"center\"><strong><span id=\"DO\"></span></strong></td>"+
    "<td bgcolor=\"#FFFFFF\" align=\"center\"><strong><span id=\"levelDO\"></span></strong></td>"+
    "<td bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>≥5</strong></div></td>"+
    "</tr>"+
    "<tr>"+
    "<td id=\"td_time\" rowspan=\"3\" bgcolor=\"#f0f0f0\" align=\"center\"><strong><span id=\"time\"></span></strong></td>"+
    "<td height=\"20\" bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>氨氮</strong></div></td>"+
    "<td bgcolor=\"#f0f0f0\" align=\"center\"><strong><span id=\"NH3N\"></span></strong></td>"+
    "<td bgcolor=\"#FFFFFF\" align=\"center\"><strong><span id=\"levelNH3N\"></span></strong></td>"+
    "<td bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>≤1.0</strong></div></td>"+
    "</tr>"+
    "<tr id=\"tr_cod\">"+
    "<td id=\"td_cod\" height=\"20\" bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>高锰酸盐指数</strong></div></td>"+
    "<td id=\"td_codvalue\" bgcolor=\"#f0f0f0\" align=\"center\"><strong><span id=\"COD\"></span></strong></td>"+
    "<td id=\"td_codg\" bgcolor=\"#FFFFFF\" align=\"center\"><strong><span id=\"levelCOD\"></span></strong></td>"+
    "<td id=\"td_codm\" bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>≤6</strong></div></td>"+
    "</tr>"+
    "<tr id=\"tr_toc\">"+
    "<td height=\"20\" bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>总有机碳</strong></div></td>"+
    "<td bgcolor=\"#f0f0f0\" align=\"center\"><strong><span id=\"TOC\"></span></strong></td>"+
    "<td bgcolor=\"#FFFFFF\" align=\"center\"><strong><span id=\"levelTOC\"></span></strong></td>"+
    "<td bgcolor=\"#FFFFFF\" align=\"center\"><div align=\"center\"><strong>-</strong></div></td>"+
    "</tr>"+
    "<tr>"+
    "<td bgcolor=\"#f0f0f0\" style=\"height: 20px\" align=\"center\"><div align=\"center\"><strong>断面属性</strong></div></td>"+
    "<td colspan=\"4\" bgcolor=\"#FFFFFF\" style=\"height: 20px\" align=\"center\"><strong><span id=\"propertysta\"></span></strong></td>"+
    "</tr>"+
    "<tr>"+
    "<td height=\"20\" bgcolor=\"#f0f0f0\" align=\"center\"><div align=\"center\"><strong>站点情况</strong></div></td>"+
    "<td colspan=\"4\" bgcolor=\"#FFFFFF\" align=\"center\"><strong><a href=\"#\" onfocus=\"this.blur();\"><font style=\"color:Black\"><span id=\"statussta\" onmousedown=\"showstationinfo()\"></span></font></a></strong></td>"+
    "</tr>"+
    "<tr>"+
    "<td height=\"20\" bgcolor=\"#f0f0f0\" align=\"center\"><div align=\"center\"><strong>站点简介</strong></div></td>"+
    "<td colspan=\"4\" bgcolor=\"#FFFFFF\" align=\"center\"><strong><a href=\"#\" onfocus=\"this.blur();\"><font style=\"color:Black\"><span id=\"stationinfo\" onmousedown=\"showstationinfo_1()\"></span></font></a></strong></td>"+
    "</tr>"+
    "<tr>"+
    "<td height=\"20\" bgcolor=\"#f0f0f0\" align=\"center\"><div align=\"center\"><strong>备注</strong></div></td>"+
    "<td colspan=\"4\" bgcolor=\"#FFFFFF\" align=\"center\"><strong> * 为待更新仪器    - 为待检修仪器<br />pH无量纲，其他参数单位mg/L</strong></td>"+
    "</tr>"+
    "</table>"
;




