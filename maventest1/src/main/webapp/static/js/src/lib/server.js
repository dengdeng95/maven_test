const express = require('express');
const app = express();
const http = require('http').Server(app);
const io = require('socket.io')(http);
const request = require('request');

var url = 'http://localhost:9090/maventest1';

app.get('/', (req, res) => {
	res.send('<h1>连接成功才能访问到</h1>');
});


io.on('connection', (socket) => {
	socket.on('fromWebClient', function (msg) {
		console.log(msg);
	})
});


setInterval(function () {
	request(url + '/shiro/marketdata', function (error, response, body) {
		if (!error && response.statusCode == 200) {
			var data = JSON.parse(body);
			if(data.success){
				io.emit('marketdata',data);
			}
		}
	})
}, 1000);

//开启监听
http.listen(8223, () => {
	console.log('listening on *:8223');
});