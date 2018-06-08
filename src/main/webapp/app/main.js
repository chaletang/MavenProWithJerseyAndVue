import Vue from 'vue';
import App from './app.vue';
import VueRouter from 'vue-router';
import VueResource from 'vue-resource';
//import vSide from './components/v-side.vue';

Vue.use(VueRouter);

// define router components 
const VTest = require('./components/v-test.vue');
const VTest2 = require('./components/v-test2.vue');
const VReport = require('./components/v-report.vue');

// define router
const routes = [
  { path: '/', redirect: '/test' },
  { path: '/test', component: VTest },
  { path: '/test2', component: VTest2 },
  { path: '/report', component: VReport },
];

// create router instance
const router = new VueRouter({   
  routes
});


/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App), 
  router
});