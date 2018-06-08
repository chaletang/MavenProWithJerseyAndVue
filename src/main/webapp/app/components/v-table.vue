<template>
<div id="tableContainer">
    	<div class="container-fluid">
    		<div class="table-responsive">
    			<h3>Test Summary:</h3>
				<table class="table">
					<thead>
		        		<tr>
							<th>skipped</th>
							<th>failed</th>
							<th>ignored</th>
							<th>total</th>
							<th>passed</th>
		        		</tr>
		        	</thead>
		        	<tbody> 
		        		<tr>
							<td><a @click="makeActive('SKIP')">{{report.summary.skipped}}</a></td>
							<td><a @click="makeActive('FAIL')">{{report.summary.failed}}</a></td>
							<td><a @click="makeActive('IGNORE')">{{report.summary.ignored}}</a></td>
							<td><a @click="makeActive('ALL')">{{report.summary.total}}</a></td>
							<td><a @click="makeActive('PASS')">{{report.summary.passed}}</a></td>
		        		</tr>
		        	</tbody>
				</table>
			</div>
			<div class="table-detail" :class="currentChoice">
				<ul class="suites-info">
					<li v-for="suite in report.suites">
						<!--
						<p>{{suite.suiteName}}</p>
						<p>{{suite.suiteDuration}}</p>
						<p>{{suite.suiteStarted}}</p>
						<p>{{suite.suiteFinished}}</p>
						-->
						<ul class="tests-info">
							<li v-for="test in suite.tests"> 
								<p class="test-name">{{test.tname}}</p>
								<!--
								<p>{{test.tduration}}</p>
								<p>{{test.tstarted}}</p>
								<p>{{test.tfinished}}</p>
								-->
								<ul class="classes-info">
       								<li v-for="tclass in test.tclasses">
       									<p class="class-name">{{tclass.className}}</p>
       									<ul class="methods-info">
		       								<li>
			       								<div class="table-responsive">
				       								<table class="table table-bordered">
				       									<thead>
											        		<tr>
																<th>Name</th>
																<th>Duration(ms)</th>
																<th>Started</th>
																<th>Finished</th>
																<th>Status</th>
																<th>Message</th>
											        		</tr>
											        	</thead>
											        	<tbody>
											        		<tr v-for="tmethod in tclass.tmethods" :class="statusClass(tmethod.methodStatus)" v-show="isActiveTab('ALL') || isActiveTab(tmethod.methodStatus)">
																<td>{{tmethod.methodName}}</td>
																<td>{{tmethod.methodDuration}}</td>
																<td>{{tmethod.methodStarted}}</td>
																<td>{{tmethod.methodFinished}}</td>
																<td class="method-status">{{tmethod.methodStatus}}</td>
																<td>{{tmethod.methodMessage}}</td>
											        		</tr>
											        	</tbody> 
			       									</table>
		       									</div>
		       								</li>
		       							</ul>
       								</li>
       							</ul>
							</li>
						</ul> 
					</li>
				</ul>
			</div>	
    	</div>
    </div>
</template>
<script>
  import $ from '../../lib/js/jquery';

  export default {
    data() {
      return {
		choice: 'ALL',
		isActive: false
      };
    },
    props: ['report'],
    components: {
       	
    },
    watch: {
		'report': function() {
		 	//alert("child-table:" + this.report.summary.passed);
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
		
	},
	methods: {
		makeActive: function(val) {
            this.choice = val;
        },
        isActiveTab: function(val) {
          return this.choice === val;
        },
        statusClass: function (status) {
	    	return {
		      red: status == 'FAIL',
		      green: status == 'PASS',
		      Orange: status == 'SKIP',
		      Blue: status == 'IGNORE',
		    };
	  	}
	}
  }
</script>