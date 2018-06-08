<template>
	<div id="navContainer" :class="{open:isOpened}"> 
			<a class="fa nav-toggle" :class="{'fa-arrow-circle-left':isOpened,'fa-arrow-circle-right':!isOpened}" @click="navToggle"></a>
		    <ul class="nav-left">
	     		<li class="nav-header">
	     			<v-dropdown></v-dropdown>
	     			<img src="../../lib/images/face_1.jpg" width="48" height="48"/> 
	     		</li>
		      	<li class="nav-item" v-for="item in items"> 
		        <!-- <a href="#">{{item.name}}</a> -->
		        	<router-link :to="'/' + item.ad">{{item.name}}</router-link>
		      	</li>
	    	</ul>
	  	</div>
</template>
 
<script>
import vDropdown from '../../app/components/v-dropdown.vue';
export default { 
	name: 'side',
  	data () {
	    return {
	      items: [
	        {name: 'Category Test', ad: 'test'}, 
	        {name: 'Single Test', ad: 'test2'}, 
	        {name: 'Report', ad: 'report'}   
	      ],
	      isOpened: true
	    };
  	},
  	components: {
       	vDropdown
    },
    methods: {
    	navToggle: function(){
    		this.isOpened = !this.isOpened;
    		return this.isOpened;
    	}
    } 
}
</script>
