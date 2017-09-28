<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>交易大厅</h1>
	<table id="ul_market" border="1px">
		
	</table>
</body>
</html>
<script type="text/javascript"  src="../static/js/src/socket.io-2.0.3.js"></script>
<script type="text/javascript"  src="../static/js/src/lib/jquery/1.9.1.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
   if(typeof(WebSocket) != "function" ) {
      $('body').html("<h1>Error</h1><p>Your browser does not support HTML5 Web Sockets. Try Google Chrome instead.</p>");
   }
})

//var ws = new WebSocket("ws://127.0.0.1:8222");
//ws.onopen = function (e) {
   //console.log('Connection to server opened');
//}
var socket = io.connect('ws://127.0.0.1:8223');

socket.on('marketdata',function(data){debugger
	var html = '<tr align=\'center\'><td>深度</td><td>金额</td><td>时间</td></tr>';
	for(var i=0;i<data.obj.length;i++){
		html += "<tr align=\'center\'><td>"+i + '</td><td>' + data.obj[i].transactionMoney +'</td><td>'+ time(new Date(data.obj[1].created))+'</td></tr>';
	}
	$("#ul_market").html(html);
})


//格式化时间
function time(time){
    var datetime = time;
    var year = datetime.getFullYear();
    var month = datetime.getMonth() + 1 < 10 ? "0" + (datetime.getMonth() + 1) : datetime.getMonth() + 1;
    var date = datetime.getDate() < 10 ? "0" + datetime.getDate() : datetime.getDate();
    var hour = datetime.getHours()< 10 ? "0" + datetime.getHours() : datetime.getHours();
    var minute = datetime.getMinutes()< 10 ? "0" + datetime.getMinutes() : datetime.getMinutes();
    var second = datetime.getSeconds()< 10 ? "0" + datetime.getSeconds() : datetime.getSeconds();
    return year + "-" + month + "-" + date+" "+hour+":"+minute+":"+second;
}
</script>