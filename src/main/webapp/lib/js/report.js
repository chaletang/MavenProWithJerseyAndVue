var config = {
		rootURL: "http://localhost:8080/MyMavenWebTest/rest/test"
};

var app = new Vue({ 
	el: '#reportApp',
	data: {
		summary: {},
		methods: []
	},
	watch: {
		
	},
	computed: {
	
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
					//alert('Test get successfully! ');
					self.summary = data.summary;
					self.methods = data.suites[0].tests[0].tclasses[0].tmethods;
				},
				error: function(jqXHR, textStatus, errorThrown){
					alert('Test error: ' + textStatus);
				}
			});
		}
	}
});

//app.$mount('.testApp');