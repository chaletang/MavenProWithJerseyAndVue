/*var path = require('path');

module.exports = {
  entry: {
  	main: './app/js/main.js'
  },
  output: {
    filename: 'bundle.js', //[name].js
    path: path.resolve(__dirname, 'site/js')
  }
};*/

var build = {
  entry: './app/components/v-chart.vue',
  output: {
    path: __dirname,
    filename: 'dist/v-chart.js',
    library: 'VueECharts',
    libraryTarget: 'umd'
  },
  module: {
    loaders: [
      {
        test: /\.vue$/,
        loader: 'vue'
      },
      {
        test: /\.js$/,
        exclude: /(node_modules|bower_components)/,
        loader: 'babel?presets[]=es2015'
      }
    ]
  },
  vue: {
    autoprefixer: {
      browsers: ['last 2 versions']
    }
  }
}

module.exports = [ build]
