<template>
	<button
	 	v-bind:id="id"
	 	v-on:click="go"
	 >
	 	<slot></slot>
	 </button>
</template>

<script>
  export default {
    props: {
      id: String
    },
    methods: {
      go (event) {
        alert(this.id);
        )
      }
    }
  }
</script>
