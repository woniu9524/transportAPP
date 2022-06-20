import { createApp } from 'vue'
import App from './App.vue'
import Vant from 'vant';
import 'vant/lib/index.css';
//import 'amfe-flexible';
import router from './router';
import NutUI from "@nutui/nutui";
import "@nutui/nutui/dist/style.css";
import "./common/style/commonStyle.css"
import { createPinia } from 'pinia'
createApp(App).use(Vant).use(router).use(NutUI).use(createPinia()).mount('#app')
