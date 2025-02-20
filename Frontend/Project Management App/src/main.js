// main.js
import {createApp} from 'vue'
import {createPinia} from "pinia";
import {createBootstrap} from 'bootstrap-vue-next'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-next/dist/bootstrap-vue-next.css'
import App from './App.vue'

const app = createApp(App)
const pinia = createPinia();

app.use(pinia)
app.use(createBootstrap)
app.mount('#app')
