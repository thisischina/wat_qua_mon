$(function(){
    var map = new MachMap();
});
var points;
function MachMap() {
    this.map = this.initMap(this.containerId);
}
MachMap.prototype.containerId = "map_container";
MachMap.prototype.map;
MachMap.prototype.gpsRecordLines = new Map();

var heatmapOverlay = new BMapLib.HeatmapOverlay({"radius":20});

// 根据地图容器的ID,初始化地图
MachMap.prototype.initMap = function (containerID) {
    var map = new BMap.Map("map_container");            // 创建地图实例
    var point = new BMap.Point(116.418261, 39.921984);  // 创建中心点坐标
    //创建覆盖物
    var pt = new BMap.Point(116.418261, 39.921984);
    var myIcon = new BMap.Icon("../assets/images/sewage.png", new BMap.Size(50,50));
    var marker = new BMap.Marker(pt,{icon:myIcon});  // 创建覆盖物
    var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
    marker.addEventListener("click", function(){    //添加左键单击事件
        map.setCenter(pt);                          //地图中心点变为监测点
        map.setZoom(20);                            //地图缩放等级更改为20，比例尺最小。
        this.openInfoWindow(infoWindow);            //打开覆盖物的信息窗口
        //图片加载完毕重绘infowindow
        document.getElementById('imgDemo').onload = function (){
            infoWindow.redraw();   //防止在网速较慢，图片未加载时，生成的信息框高度比图片的总高度小，导致图片部分被隐藏
        };
    });
    map.centerAndZoom(point, 15);                       // 初始化地图，设置中心点坐标和地图级别

    map.enableScrollWheelZoom(true);                //鼠标滑动轮子可以滚动
    map.enableKeyboard(true);                       //启用键盘操作

    map.addControl(new BMap.MapTypeControl());      //添加地图类型控件
    map.addControl(new BMap.NavigationControl());   // 添加平移缩放控件
    map.addControl(new BMap.ScaleControl());        // 添加比例尺控件
    map.addControl(new BMap.OverviewMapControl());  //添加缩略地图控件
    map.addOverlay(marker);              // 将覆盖物添加到地图中
    if(!isSupportCanvas()){                         //添加热力图图层
    	alert('热力图目前只支持有canvas支持的浏览器,您所使用的浏览器不能使用热力图功能~');
    }else{       
        map.addOverlay(heatmapOverlay);
        heatmapOverlay.setDataSet({data:points,max:100});
        openHeatmap();
    }
    return map;
}
    //是否显示热力图
    function openHeatmap(){
        heatmapOverlay.show();
    }
	function closeHeatmap(){
        heatmapOverlay.hide();
    }
    function setGradient(){
     	/*格式如下所示:
		{
	  		0:'rgb(102, 255, 0)',
	 	 	.5:'rgb(255, 170, 0)',
		  	1:'rgb(255, 0, 0)'
		}*/
     	var gradient = {};
     	var colors = document.querySelectorAll("input[type='color']");
     	colors = [].slice.call(colors,0);
     	colors.forEach(function(ele){
			gradient[ele.getAttribute("data-key")] = ele.value; 
     	});
        heatmapOverlay.setOptions({"gradient":gradient});
    }
	//判断浏览区是否支持canvas
    function isSupportCanvas(){
        var elem = document.createElement('canvas');
        return !!(elem.getContext && elem.getContext('2d'));
    }

    points =[
    {"lng":116.418261,"lat":39.921984,"count":50},
    {"lng":116.423332,"lat":39.916532,"count":51},
    {"lng":116.419787,"lat":39.930658,"count":15},
    {"lng":116.418455,"lat":39.920921,"count":40},
    {"lng":116.418843,"lat":39.915516,"count":100},
    {"lng":116.42546,"lat":39.918503,"count":6},
    {"lng":116.423289,"lat":39.919989,"count":18},
    {"lng":116.418162,"lat":39.915051,"count":80},
    {"lng":116.422039,"lat":39.91782,"count":11},
    {"lng":116.41387,"lat":39.917253,"count":7},
    {"lng":116.41773,"lat":39.919426,"count":42},
    {"lng":116.421107,"lat":39.916445,"count":4},
    {"lng":116.417521,"lat":39.917943,"count":27},
    {"lng":116.419812,"lat":39.920836,"count":23},
    {"lng":116.420682,"lat":39.91463,"count":60},
    {"lng":116.415424,"lat":39.924675,"count":8},
    {"lng":116.419242,"lat":39.914509,"count":15},
    {"lng":116.422766,"lat":39.921408,"count":25},
    {"lng":116.421674,"lat":39.924396,"count":21},
    {"lng":116.427268,"lat":39.92267,"count":1},
    {"lng":116.417721,"lat":39.920034,"count":51},
    {"lng":116.412456,"lat":39.92667,"count":7},
    {"lng":116.420432,"lat":39.919114,"count":11},
    {"lng":116.425013,"lat":39.921611,"count":35},
    {"lng":116.418733,"lat":39.931037,"count":22},
    {"lng":116.419336,"lat":39.931134,"count":4},
    {"lng":116.413557,"lat":39.923254,"count":5},
    {"lng":116.418367,"lat":39.92943,"count":3},
    {"lng":116.424312,"lat":39.919621,"count":100},
    {"lng":116.423874,"lat":39.919447,"count":87},
    {"lng":116.424225,"lat":39.923091,"count":32},
    {"lng":116.417801,"lat":39.921854,"count":44},
    {"lng":116.417129,"lat":39.928227,"count":21},
    {"lng":116.426426,"lat":39.922286,"count":80},
    {"lng":116.421597,"lat":39.91948,"count":32},
    {"lng":116.423895,"lat":39.920787,"count":26},
    {"lng":116.423563,"lat":39.921197,"count":17},
    {"lng":116.417982,"lat":39.922547,"count":17},
    {"lng":116.426126,"lat":39.921938,"count":25},
    {"lng":116.42326,"lat":39.915782,"count":100},
    {"lng":116.419239,"lat":39.916759,"count":39},
    {"lng":116.417185,"lat":39.929123,"count":11},
    {"lng":116.417237,"lat":39.927518,"count":9},
    {"lng":116.417784,"lat":39.915754,"count":47},
    {"lng":116.420193,"lat":39.917061,"count":52},
    {"lng":116.422735,"lat":39.915619,"count":100},
    {"lng":116.418495,"lat":39.915958,"count":46},
    {"lng":116.416292,"lat":39.931166,"count":9},
    {"lng":116.419916,"lat":39.924055,"count":8},
    {"lng":116.42189,"lat":39.921308,"count":11},
    {"lng":116.413765,"lat":39.929376,"count":3},
    {"lng":116.418232,"lat":39.920348,"count":50},
    {"lng":116.417554,"lat":39.930511,"count":15},
    {"lng":116.418568,"lat":39.918161,"count":23},
    {"lng":116.413461,"lat":39.926306,"count":3},
    {"lng":116.42232,"lat":39.92161,"count":13},
    {"lng":116.4174,"lat":39.928616,"count":6},
    {"lng":116.424679,"lat":39.915499,"count":21},
    {"lng":116.42171,"lat":39.915738,"count":29},
    {"lng":116.417836,"lat":39.916998,"count":99},
    {"lng":116.420755,"lat":39.928001,"count":10},
    {"lng":116.414077,"lat":39.930655,"count":14},
    {"lng":116.426092,"lat":39.922995,"count":16},
    {"lng":116.41535,"lat":39.931054,"count":15},
    {"lng":116.413022,"lat":39.921895,"count":13},
    {"lng":116.415551,"lat":39.913373,"count":17},
    {"lng":116.421191,"lat":39.926572,"count":1},
    {"lng":116.419612,"lat":39.917119,"count":9},
    {"lng":116.418237,"lat":39.921337,"count":54},
    {"lng":116.423776,"lat":39.921919,"count":26},
    {"lng":116.417694,"lat":39.92536,"count":17},
    {"lng":116.415377,"lat":39.914137,"count":19},
    {"lng":116.417434,"lat":39.914394,"count":43},
    {"lng":116.42588,"lat":39.922622,"count":27},
    {"lng":116.418345,"lat":39.919467,"count":8},
    {"lng":116.426883,"lat":39.917171,"count":3},
    {"lng":116.423877,"lat":39.916659,"count":34},
    {"lng":116.415712,"lat":39.915613,"count":14},
    {"lng":116.419869,"lat":39.931416,"count":12},
    {"lng":116.416956,"lat":39.925377,"count":11},
    {"lng":116.42066,"lat":39.925017,"count":38},
    {"lng":116.416244,"lat":39.920215,"count":91},
    {"lng":116.41929,"lat":39.915908,"count":54},
    {"lng":116.422116,"lat":39.919658,"count":21},
    {"lng":116.4183,"lat":39.925015,"count":15},
    {"lng":116.421969,"lat":39.913527,"count":3},
    {"lng":116.422936,"lat":39.921854,"count":24},
    {"lng":116.41905,"lat":39.929217,"count":12},
    {"lng":116.424579,"lat":39.914987,"count":57},
    {"lng":116.42076,"lat":39.915251,"count":70},
    {"lng":116.425867,"lat":39.918989,"count":8}];
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




