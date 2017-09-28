const express = require('express');
const app = express();
//约定俗成的公式
const http = require('http').Server(app);
const io = require('socket.io')(http);

//session公式
const session = require('express-session');
app.use(session({
    secret: 'keycoard cat',
    resave: false,
    saveUninitialized: true
}))

//模板引擎
app.set("view engine", "ejs");
//静态服务
app.use(express.static("./public"));

//定义数组，为了统计在线人数
var alluser = [];

//中间件
//去首页
app.get('/', (req, res) => {
    res.render('index');
})

//确认登录，检查此人是否输入用户名，并且昵称不重复
app.get('/check', (req, res) => {
    var yonghuming = req.query.yonghuming;
    if (!yonghuming) {
        res.send('必须填写用户名');
        return;
    }
    if (alluser.indexOf(yonghuming) != -1) {
        res.send('用户名已经被占用');
        return;
    }
    alluser.push(yonghuming);

    req.session.yonghuming = yonghuming;
    res.redirect('/chat');
})

//聊天室
app.get('/chat', (req, res) => {
    //这个页面必须要用session
    if(!req.session.yonghuming){
        res.redirect('/');
        return;
    }
    res.render('chat',{
        'yonghuming':req.session.yonghuming
        //'onlineLength':alluser.length
    });
})

//退出
app.get('/logout',(req,res)=>{
    var yonghuming = req.session.yonghuming;
    for(var i=0;i<alluser.length;i++){
        if(alluser[i]==yonghuming){
            alluser.splice(i,1);
        }
    }
    //req.session.destroy;
    io.emit('onlineLength',alluser.length);
    res.redirect('/');
})


//客户端连接之后，监听聊天内容以及在线人数
io.on('connection',function(socket){
    socket.on('liaotian',function(msg){
        console.log(msg);
        io.emit('liaotian',msg);
    })
    //向客户端推送在线人数
    io.emit('onlineLength',alluser.length);
})

//监听
console.log('2000端口已监听');
http.listen(2000);