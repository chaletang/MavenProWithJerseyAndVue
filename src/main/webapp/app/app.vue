<template>
	<div id="app">
		<v-header></v-header>
		<v-side></v-side>
		
		<router-view></router-view> 
	</div>
</template>

<script>
	import vHeader from '../app/components/v-header.vue';
	import vSide from '../app/components/v-side.vue'; 
  	export default { 
    	name: 'app',
    	components: {
    		vHeader,
	       	vSide
	    }
	    
  	}
</script>
