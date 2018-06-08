<template>
	<div class="dropBox" :class="{open:isOpened}">
		<a class="dropdown-toggle" @click="navToggle">Hi, {{title}}
			<span class="fa" :class="{'fa-caret-up':isOpened,'fa-caret-down':!isOpened}"></span>
		</a>
		<div class="dropdown-menu">
			<ul>
				<li v-for="item in items">
					<a>{{item.name}}</a>
					<!--<router-link :to="'/' + item.ad">{{item.name}}</router-link>-->
				</li>
			</ul>
		</div>
	</div>
</template>
 
<script>
export default { 
	name: 'drop',
  	data () {
	    return {
	    	title: 'Lily',
	      	items: [
	        	{name: 'Profile', ad: 'profile'}, 
	        	{name: 'Logout', ad: 'login'}   
	      	],
	      	isOpened: false
	    };
  	},
    methods: {
    	navToggle: function(){
    		this.isOpened = !this.isOpened;
    		return this.isOpened;
    	}
    } 
}
</script>
