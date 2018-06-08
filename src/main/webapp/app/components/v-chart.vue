<template>
  <div class="chartContainer">
  	<div class="container-fluid">
  		<div class="row">
	  		<div class="col-md-6">
			  	<h3>Bar Chart:</h3>
			  	<div id="testOverviewBar" class="testOverview"></div>
		  	</div>
	  		<div class="col-md-6">
			  	<h3>Pie Chart:</h3>
			    <div id="testOverviewPie" class="testOverview"></div>
		    </div>
	    </div>
    </div>
  </div>
</template>

<script>
  import echarts from 'echarts';
  
  export default {
    data() {
      return {
        chart: null,
        chartData: {}
      };
    },
    props: ['report'],
    computed: {
    	
    },
    watch: {
		'report': function() {
		 	//alert("child-chart:" + this.report.summary.passed);
			this.chartData = this.$parent.toChartData(this.report.summary);
			this.$nextTick(function() {
	        	this.drawBar('testOverviewBar');
            	this.drawPie('testOverviewPie');
	        	this.resize();
	      	});
		}
	},
    methods: {	
    	drawBar(id) {
	    	this.chart = echarts.init(document.getElementById(id), 'vintage');
	        this.chart.setOption({
	          tooltip: {
	            trigger: 'axis'
	          },
	          toolbox: {
	            feature: {
	              magicType: {
	                type: ['line', 'bar']
	              },
	              saveAsImage: {},
	              dataView: {}             
	            },
	            right: 15,
	            top: 10
	          },
	          grid: {
	            left: '3%',
	            right: '4%',
	            bottom: '3%',
	            containLabel: true
	          },
	          xAxis: [
	            {
	              type: 'category',
	              boundrayGap: false,
	              data: this.chartData.keys
	            }
	          ],
	          yAxis: [
	            {
	              type: 'value',
	              name: 'Number'
	            }
	          ],
	          series: [
	            {
	              name: 'Test',
	              type: 'bar',
	              label: {
	                normal: {
	                  show: true,
	                  position: 'top'
	                }
	              },
	              data: this.chartData.values
	            }
	          ]
	        });
      },
      drawPie (id) {  
      	this.chart = echarts.init(document.getElementById(id));
        this.chart.setOption({
          tooltip: {
            trigger: 'item',
            formatte: "{b}: {c} ({d}%)"
          },
          toolbox: {
            feature: {
              saveAsImage: {},
              dataView: {}
            },
            right: 15,
            top: 10
          },
          legend: {
              orient: 'vertical',
              left: 5,
              top: 10,
              data: this.chartData.keys,
          },
          series: [
            {
              name: 'Test',
              type: 'pie',
              radius: ['50%', '70%'],
              center: ['50%', '60%'],
              avoidLabelOverlap: false,
              label: {
                emphasis: {
                  show: true,
                  textStyle: {
                    fontSize: '24',
                    fontWeight: 'bold'
                  }
                }
              },
              labelLine: {
                normal: {
                  show: false
                }
              },
              data: this.chartData.keyValues,
              itemStyle: {
                emphasis: {
                  shadowBlur: 10,
                  shadowOffset: 0,
                  shadowColor: 'rgba(0, 0, 0, 0.5)'
                }
              }
            }
          ]
        });
      },
      resize: function(){
      	var that = this;
    	var resizeTimer = null;
    	window.onresize = function() {
          if (resizeTimer) clearTimeout(resizeTimer);
          resizeTimer = setTimeout(function() {
          	that.drawBar('testOverviewBar');
            that.drawPie('testOverviewPie');
          }, 100);
    	}
      }
    },
    mounted() {
    
	}
  }
</script>
