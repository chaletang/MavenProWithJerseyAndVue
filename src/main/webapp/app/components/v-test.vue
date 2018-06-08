<template>
<div id="testApp" class="testApp viewApp">
	        <div class="container-fluid">
	        	<p>
	        		<button id="btnRefresh" @click="get">Refresh</button>
	        		<button id="btnCreate" @click="create">New</button> 
	        		<button id="btnRemove" @click="remove(checkedTests)">Remove</button> 
	        		<button id="btnRun" class="pull-right" @click="run(checkedTests)">Run</button>
	        	</p>
	        	<div id="tablecontainer">
	        		<div class="tables-and-grids ">
	        			<div class="tg-selectable-rows">
	        				<div class="table-responsive">
					        	<table class="table" id="table">
						        	<thead>
						        		<tr>
						        			<th>
												<div class="checkbox selectState">
													<input type="checkbox" id="checkboxCustom-00" class="checkbox-custom" v-model="checkedAll" @click="checkAll">
													<label for="checkboxCustom-00">
														<span class="all"><i class="fa fa-check"></i></span>
														<span class="any"><i class="fa fa-minus one"></i></span>
													</label>
												</div>
												</th>
											<th>ID</th>
											<th>Name</th>
											<th>Description</th>
											<th></th>
						        	</tr>
						        	</thead>
						        	<tbody>
						        		<tr v-for="test in tests">
							        		<td>
												<div class="checkbox selectState">
													<input type="checkbox" class="checkbox-custom" 
													v-bind:id="'checkbox-custom-'+test.testId" v-bind:value="test.testId" v-model="checkedTests">
													<label v-bind:for="'checkbox-custom-'+test.testId">
														<span><i class="fa fa-check"></i></span>
													</label>
												</div>
											</td>
											<td>{{test.testId}}</td>
											<td>{{test.testName}}</td>
											<td>{{test.testDes}}</td>
											<td><a class='btnEdit' @click="edit(test)">Edit</a></td>
						        		</tr>
						        	</tbody>
					        	</table>
				        	</div>
			        	</div>
		        	</div>
	        		
	        	</div>
	        	<!-- You select: {{checkedTests}}</p> --><p>
	        	<div class="editorContainer" v-if="seen" :class="{inEdit:editing}">
		        	<div class="form-group">
			            <label for="testId">ID</label>
			            <input type="text" name="testId" id="testId" class="form-control" v-model="currentTestId" readonly/>
			        </div>
			        <div class="form-group">
			            <label for="testName">Name</label>
			            <input type="text" name="testName" id="testName" class="form-control" v-model="testName"/>
			        </div>
			        <div class="form-group">
			            <label for="testDes">Description</label>
			            <input type="text" name="testDes" id="testDes" class="form-control" v-model="testDes"/>
			        </div>
			        <button id="btnAdd" @click="add">Add</button>
			        <button id="btnUpdate" @click="update(currentTestId)">Update</button>
			        <button id="btnCancel" @click="reset">cancel</button>
		        </div>
	        </div>
        </div>
</template>

<script>
  import $ from '../../lib/js/jquery';
  
  const config = {
		rootURL: "http://localhost:8080/MyMavenWebTest/rest/test",	
		formToJSON: function(ele1, ele2, ele3){
			return JSON.stringify({ 
			"testId": ele1, 
			"testName": ele2,
			"testDes": ele3
			})
		}
	};

  export default {
    data() {
      return {
        tests: [],
		checkedTests: [],
		editing: false,
		checkedAll: false,
		seen: false,
		testIndex: 0,
		testPrefix: 't-',
		testName: '',
		testDes: ''
      };
    },
    watch:{
		'checkedTests':function(){
			if(this.tests.length === this.checkedTests.length){
				this.checkedAll = true;
			}else{
				this.checkedAll = false;
			}
		}
	},
	computed: {
		currentTestId: function() {
			var self = this;
			return self.testPrefix + self.testIndex.toString();
	    },
	    maxTestId: function() {
			var self = this;
			return self.tests.length == 0? 1 : Number(self.tests[self.tests.length-1].testId.replace(self.testPrefix, '')) + 1;
	    }
	},
	filters: {
		
	},
	mounted: function() {
		this.get();
	},
	methods: {
		checkAll: function() {
			var self = this;
			var checkedTests = [];
			if (self.checkedAll) {
				self.tests.forEach(function (test) {
					checkedTests.push(test.testId);
				});
			}	
			self.checkedTests = checkedTests;
		},
		
		get: function(){
			var self = this;
			$.ajax({
				type: 'GET', 
				contentType: 'application/json; charSet=UTF-8',
				url: config.rootURL,
				success: function(data, textStatus, jqXHR){
					//alert('Test get successfully! ');
					self.tests = data;
					self.testIndex = self.maxTestId;
					self.seen = false;
					self.editing = false;
					
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		},
		create: function(){
			var self = this;
			self.showEditor();
			self.reset();
			self.testIndex = self.maxTestId;
			self.editing = false;
		},
		add: function(){
			var self = this;
			var name = self.testName && self.testName.trim();
			var des = self.testDes && self.testDes.trim();
			if (!name) {
				return;
			}
			var	newId = self.currentTestId,
				newName = name,
				newDes = des;

			$.ajax({
				type: 'POST',
				contentType: 'application/json;charset=UTF-8',
				url: config.rootURL,
				dataType: "json",
				data: config.formToJSON(newId,newName,newDes),
				success: function(data, textStatus, jqXHR){
					alert('Save successfully! ');
					self.reset();
					self.get();
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
			
		},
		
		showEditor: function(){ 
			var self = this;
			self.seen = true;
		},
		edit: function(test){
			var self = this;
			self.showEditor();
			self.testIndex = Number(test.testId.replace(self.testPrefix, ''));
			self.testName = test.testName;
			self.testDes =  test.testDes;
			self.editing = true;
		},
		update: function(id){
			var self = this;
			var name = self.testName && self.testName.trim();
			var des = self.testDes && self.testDes.trim();
			//if (!name) {
			//	return;
			//}
			var	editId = id,
				editName = name,
				editDes = des;

			$.ajax({
				type: 'PUT',
				contentType: 'application/json;charset=UTF-8',
				url: config.rootURL,
				dataType: "json",
				data: config.formToJSON(editId,editName,editDes),
				success: function(data, textStatus, jqXHR){
					//alert('Update successfully! ');
					self.reset();
					self.get();
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		},
		reset: function(){
			var self = this;
			self.testName = "";
			self.testDes = "";
		},
		removeOne: function(id){
			var self = this;
			var currentID = Number(id);
			$.ajax({
				type: 'DELETE',
				contentType: 'application/json;charset=UTF-8',
				url: config.rootURL + "/remove/" + currentID,
				dataType: "json",
				data: currentID,
				success: function(data, textStatus, jqXHR){
					//alert('Remove successfully');
					self.get();
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		},
		remove: function(list){
			var self = this;
			var currentIDs = list.join('&id=');
			currentIDs = 'id=' + currentIDs;
			$.ajax({
				type: 'DELETE',
				contentType: 'application/json;charset=UTF-8',
				url: config.rootURL + "/remove?" + currentIDs,
				dataType: "json",
				data: list.pop(),
				success: function(data, textStatus, jqXHR){
					//alert('Remove successfully');
					self.get();
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		},
		/* Redirect to other url
		report: function(){ 
			var self = this;
			$.ajax({
				type: 'GET',
				//contentType: 'application/json;charset=UTF-8',
				url: config.rootURL + "/report",
				dataType: "html",
				success: function(data, textStatus, jqXHR){
					alert('Redirect To Report Page');
					location.href = "./report";
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		}*/
		run: function(list){
			var self = this;
			var currentIDs = list.join('&id=');
			currentIDs = 'id=' + currentIDs;
			$.ajax({
				type: 'GET',
				contentType: 'application/json;charset=UTF-8',
				url: config.rootURL + "/run?" + currentIDs,
				dataType: "json",
				data: list.pop(),
				success: function(data, textStatus, jqXHR){
					//alert('Run successfully');
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		}
	}
  }
</script>
