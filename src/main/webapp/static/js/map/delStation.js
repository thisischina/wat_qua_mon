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

function cancel(){
    window.close();
}

function del(){
    var markerID = window.opener.markerID;
    $.ajax({
        type:"Get",
        data:{
            id:markerID
        },
        url:"../../map/deleteStation",
        datatype:"json",
        success:function(station,status){
            if(station.data == "success"){
                window.opener.location.reload();
                window.close();
            }else{
                alert("数据删除失败！");
            }
        }
    });
}