var config = {
		rootURL: "http://localhost:8080/MyMavenWebTest/rest/test",	
		formToJSON: function(ele1, ele2, ele3){
			return JSON.stringify({ 
			"testId": ele1, 
			"testName": ele2,
			"testDes": ele3
			})
		}
};

var app = new Vue({ 
	el: '#testApp',
	data: {
		tests: [],
		checkedTests: [],
		editing: false,
		checkedAll: false,
		seen: false,
		testIndex: 0,
		testPrefix: 't-',
		testName: '',
		testDes: ''
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
		},
		report: function(){ 
			var self = this;
			$.ajax({
				type: 'GET',
				//contentType: 'application/json;charset=UTF-8',
				url: config.rootURL + "/report",
				dataType: "html",
				success: function(data, textStatus, jqXHR){
					alert('Redirect To Report Page');
					location.href = "./report.html";
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		}
	}
});

//app.$mount('.testApp');