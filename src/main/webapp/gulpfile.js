var gulp = require('gulp');
var webpack = require('gulp-webpack');
var named = require('vinyl-named');
var browserSync = require('browser-sync').create();
var runSequence = require('run-sequence');
var sass = require('gulp-sass');
var rev = require('gulp-rev');
var revCollector = require('gulp-rev-collector');
var cache = require('gulp-cache');
var del = require('del');
var path = require('path');


var appList = ['main'];

gulp.task('default', ['bundle'], function() {
  console.log('done');
});

//Clean css folder
gulp.task('clean:css',function(){
	//delete folder
	return del.sync('dist/css/');
});

//Clean cache
gulp.task('cache:clear', function (callback) {
	return cache.clearAll(callback);
});

//add timestamp to css
gulp.task('rev', function() {
	return gulp.src(['rev/*.json', 'lib/html/**/*.html'])                    
 	.pipe(revCollector())                                   
 	.pipe(gulp.dest('./'));                                
});

gulp.task('sass',['clean:css'],function(){
	return gulp.src('lib/sass/main.scss')
		.pipe(sass())
		.pipe(rev())
		.pipe(gulp.dest('dist/css'))
		.pipe(rev.manifest())
		.pipe(gulp.dest('./rev'))
		/*.pipe(browserSync.reload({
		stream: true
		}));*/
});

gulp.task('bundle', function() {
	return gulp.src(mapFiles(appList, 'js'))
    	.pipe(named())
    	.pipe(webpack(getConfig()))
    	.pipe(gulp.dest('dist/js/'));
});

gulp.task('css', function(callback) {	
	runSequence('sass', 'rev',callback);
});

gulp.task('watch', function(callback) {	
	gulp.watch('lib/sass/**/*.scss', ['css']);
	return gulp.src(mapFiles(appList, 'js'))
    	.pipe(named())
    	.pipe(webpack(getConfig({watch: true})))
		.pipe(gulp.dest('dist/js/'));
});

gulp.task('browserSync', function(callback){
	browserSync.init({
	    files: ['./**/*'],
	    port: 8001,
	    server: {
	      baseDir: '.'
	    }
  	});
  	runSequence(['watch'],callback);
});

gulp.task('serve',['browserSync']);


/**
 * @private
 */
function getConfig(opt) {
  var config = {
    module: {
      loaders: [
        { test: /\.vue$/, loader: 'vue'},
        {
        	test: [/\.js$/],
        	exclude: /(node_modules|bower_components)/,
        	loader: 'babel?presets[]=es2015'
      	},
      	{
        	test: [/\.json$/],
        	loader: 'json-loader'
      	},
      	{
            test: /\.(png|jpe?g|gif|svg)(\?.*)?$/,
            loader: 'url'
	      },
	      {
	        test: /\.(woff2?|eot|ttf|otf)(\?.*)?$/,
	        loader: 'url'
	      }
      ]
    },
    vue: {
	  
	},
    devtool: 'source-map'
  }
  if (!opt) {
    return config;
  }
  for (var i in opt) {
    config[i] = opt[i]
  }
  return config;
};

/**
 * @private
 */
function mapFiles(list, extname) {
  return list.map(function (app) {return 'app/' + app + '.' + extname});
};