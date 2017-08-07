$(function(){
    load();
});
function load(){
    var markerID = window.opener.markerID;
    $.ajax({
        type:"Get",
        data:{
            id:markerID
        },
        url:"../../map/getStation",
        datatype:"json",
        success:function(station,status){
            var data = station.data;
            $('#station_name').val(data.name);
            $("#station_address").val(data.address);
            $("#station_type").val(data.type);
        }
    });
}

function edit(){
    var markerID = window.opener.markerID;
    var station = {
        id:markerID,
        name : $('#station_name').val(),
        address : $("#station_address").val(),
        type : $("#station_type").val(),
        coordinate : "("+window.opener.station.lng+","+window.opener.station.lat+")"
    };
    $.ajax({
        type:"Get",
        data:station,
        url:"../../map/editStation",
        datatype:"json",
        success:function(station,status){
            if(station.data == "success"){
                window.opener.location.reload();
                window.close();
            }else{
                alert("修改数据失败！");
            }
        }
    });
}