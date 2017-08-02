$(function() {
    var father = window.opener;
    console.log(father.station);
});
function reset(){
    $('#station_name').val("");
    $("#station_address").val("");
    $("#station_type").val("");
}
function submit(){
    var station = {
        name : $('#station_name').val(),
        address : $("#station_address").val(),
        stationtype : $("#station_type").val(),
        coordinate : "("+window.opener.station.lng+","+window.opener.station.lat+")"
    };
     $.ajax({
                    type:"GET",
                    url:"../../map/addStationFromPage",
                    data:station,    
                    dataType:"json",
                    success:function(points,status) {
                        if(points.data == "success"){
                             window.opener.location.reload();
                             window.close();
                        }
                    }
    });
}
