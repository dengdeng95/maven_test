// 获取 gulp
var gulp = require('gulp'); //require() 是 node （CommonJS）中获取模块的语法。
// 获取 uglify 模块（用于压缩 JS）
var uglify = require('gulp-uglify');
//var babel = require('gulp-babel');
var del = require('del'); 
//var pump = require('pump');
var concat = require('gulp-seajs-concat');//合并模块
var transport = require('gulp-seajs-transport');//将匿名模块变成具名模块
var merge = require('merge-stream');//合并多个流

// 压缩 js 文件
// 在命令行使用 gulp script 启动此任务
//gulp.task(name, fn) - 定义任务，第一个参数是任务名，第二个参数是任务内容。
//gulp.src(path) - 选择文件，传入参数是文件路径。
//gulp.dest(path) - 输出文件
//gulp.pipe() - 管道，你可以暂时将 pipe 理解为将操作加入执行队列
gulp.task('script', function() {
	// 1. 找到文件
	gulp.src('js/dist/**/*.js')
	// 2. 压缩文件
	//.pipe(babel())
	.pipe(uglify({
		  mangle: {except: ['require' ,'exports' ,'module' ,'$']}
		}))
	// 3. 另存压缩后的文件
	.pipe(gulp.dest('js/dist'))
})

//复制src文件下的所有的文件到dist中
gulp.task('copy', function(cb) {
  // 将你的默认的任务代码放在这
  pump([
        gulp.src('js/**/*'),
        gulp.dest('./dist')
    ],
    cb
  );
});

//压缩src下的全部js文件复制到dist中
gulp.task('one',['copy'], function(cb) {
  // 将你的默认的任务代码放在这
  pump([
        gulp.src('js/**/*.js'),
        uglify({
          mangle: {except: ['require' ,'exports' ,'module' ,'$']}
        }),
        gulp.dest('./dist')
    ],
    cb
  );
});


gulp.task('clean', function (cb) {
  del([
    'dist/**/*',
    // 我们不希望删掉这个文件，所以我们取反这个匹配模式
    '!dist/mobile/deploy.json'
  ], cb);
}); 
    
// 合并seajs
gulp.task('seajs', function () { 
	return merge(
		gulp.src('js/src/!(lib)/**/*.js')  //base：  类型：String  设置输出路径以某个路径的某个组成部分为基础向后拼接
		.pipe(transport())//匿名变具名
        .pipe(concat({
			base: 'js/src'
		}))  
        .pipe(gulp.dest('js/dist'))  
	);
});  

//输入gulp命令即可运行[]里的内容
gulp.task("default",['script']);
