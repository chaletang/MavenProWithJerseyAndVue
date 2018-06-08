<template>
	<div id="paginationContainer"> 
			<ul class="pagination">
	     		<li class="p-first">
	     			<a>&lt;&lt;</a>
	     		</li>
	     		<li class="p-prev">
	     			<a>&lt;</a>
	     		</li>
		      	<li class="p-item">
		        	<a>1</a>
		      	</li>
		      	<li class="p-next">
		      		<a>&gt;</a>
		      	</li>
		      	<li class="p-last">
		      		<a>&gt;&gt;</a> 
		      	</li>
	    	</ul>
	  	</div>
</template>
 
<script>
export default { 
	name: 'pagination',
  	data () {
	    return {
	    	activePage: 1,
	    	records: 10,
	    	perpage: 6
	    };
  	}
  	/*computed:{
	  	PerPage: function() {
	    	return this.perpage?parseInt(this.perpage):25;
	    },
	    Records: function() {
	    	return this.records?parseInt(this.records):0;
	    },
	    TotalPages: function() {
	    	return this.$refs.table.totalCount/this.PerPage + 1;
	    }
  	},
    methods: {
	  	refresh: function() {
	    	this.$refs.table.setPage(1);
	    },
	    prev: function() {
	    	return this.$refs.table.prev();
	    },
	    next: function() {
	    	return this.$refs.table.next();
	    }
  	},
  	mounted: function() {
  		this.$on('v-pagination::table', function(page) {
    		this.activePage = page;	
    	});
  	}*/
}
</script>
