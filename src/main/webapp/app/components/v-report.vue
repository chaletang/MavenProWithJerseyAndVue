<template>
<div id="reportApp" class="reportApp viewApp">
	<div class="container-fluid">
		<ul class="typeLinks">
			<li :class="{active:currentChoice == 'Chart'}"><a @click="makeActive('Chart')" class="typeLink">Chart</a></li>
			<li :class="{active:currentChoice == 'Table'}"><a @click="makeActive('Table')" class="typeLink">Table</a></li>
		</ul>
	</div>		
	<v-table :report="report" v-show="isActiveTab('Table')"></v-table>
	<v-chart :report="report" v-show="isActiveTab('Chart')"></v-chart>
			
 </div>
</template>
<script>
  import $ from '../../lib/js/jquery';
  import vChart from '../../app/components/v-chart.vue';
  import vTable from '../../app/components/v-table.vue';
  
  const config = {
		rootURL: "http://localhost:8080/MyMavenWebTest/rest/test"
	};

  export default {
    data() {
      return {
      	report: {},
		choice: 'Chart',
		isActive: false
      };
    },
   
    components: {
       	vChart,
       	vTable
    },
    watch: {
		'report': function() {
		 	//alert("parent:" + this.report.summary.passed);
			return this.report;
		}
	},
	computed: {	
		currentChoice: function(){
			return this.choice;
		}
	},
	filters: {
		
	},
	mounted: function() {
		this.get();
	},
	methods: {
		get: function(){
			var self = this;
			$.ajax({
				url:config.rootURL + "/reportData",
				type: 'GET',
				dataType: 'json',
				success: function(data, textStatus, jqXHR){
					//alert('Test get successfully!' + JSON.stringify(data));
					self.report = data;
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		},
		toKeyValueArray: function(data){
			var arr = new Array();
			for (var key in data) {
				arr.push({"name":key, "value": data[key]});
			}
			return arr;
		},
		toChartData: function(data) {
			return {"keys": this.getKeys(data), "values": this.getValues(data), "keyValues": this.toKeyValueArray(data)};
		},
		getKeys: function(data) {
			var keys = new Array();
			for (var key in data) {
				keys.push(key);
			}
			return keys;
		},
		getValues: function(data) {
			var values = new Array();
			for (var key in data) {
				values.push(data[key]);
			}
			return values;
		},
		makeActive: function(val) {
            this.choice = val;
        },
        isActiveTab: function(val) {
          return this.choice === val;
        }
	}
  }
</script>