<!DOCTYPE html>
<html>
<head>
    <title>Ajax</title>
    <script src="../../assets/js/jquery-2.0.3.min.js"></script>
</head>
<body>
<div id="text">
    <input type="button" id="data"/>
</div>
</body>
<script type="text/javascript">
    $("#data").click(function(){
        $.ajax({
            type: "GET",
            url: "http://localhost:8080/waterSys/getStationList",
            dataType: "json",
            success: function(data,status){
                  $('#text').empty();
                  var html = '';
                  console.log(this);
                  console.log(data);
                  console.log(data.data);
                  console.log(data.data[0]);
                  console.log(status);
//                      $.each(data,function(commentIndex, comment){
//                            html += '<div class="comment"><h6>' + comment['name']
//                                      + ':</h6><p class="para"' + comment['address']
//                                      + '</p></div>';
//                      });
//                      $('#data').html(html);
            },
            error:function(){
                console.log(this);
            }
        });
    });
</script>
</html>