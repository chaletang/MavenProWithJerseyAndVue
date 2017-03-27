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
		checked: false,
		seen: false,
		testIndex: 0,
		testName: '',
		testDes: ''
	},
	watch: {
		
	},
	computed: {
		currentTestId: function (){
			return this.editing? this.testIndex.toString():(this.testIndex + 1).toString();
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
				type: 'GET', 
				contentType: 'application/json; charSet=UTF-8',
				url: config.rootURL,
				success: function(data, textStatus, jqXHR){
					//alert('Test get successfully! ');
					self.tests = data;
					self.testIndex = data.length;
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
			self.testIndex = self.tests.length;
			self.editing = false;
		},
		add: function(){
			var self = this;
			var name = self.testName && self.testName.trim();
			var des = self.testDes && self.testDes.trim();
			if (!name) {
				return;
			}
			var	newId = ++self.testIndex,
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
			self.testIndex = Number(test.testId);
			self.testName = test.testName;
			self.testDes =  test.testDes;
			self.editing = true;
		},
		update: function(id){
			var self = this;
			var name = self.testName && self.testName.trim();
			var des = self.testDes && self.testDes.trim();
			if (!name) {
				return;
			}
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
		runOne: function(id){
			var self = this;
			var currentID = Number(id);
			$.ajax({
				type: 'GET',
				contentType: 'application/json;charset=UTF-8',
				url: config.rootURL + "/run/" + currentID,
				dataType: "json",
				data: currentID,
				success: function(data, textStatus, jqXHR){
					//alert('Run successfully');
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
		}
	}
});

//app.$mount('.testApp');