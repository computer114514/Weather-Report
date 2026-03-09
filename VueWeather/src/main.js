import { createApp } from "vue";
import App from "./App.vue";
import ElmentPlus from "element-plus";
import "element-plus/dist/index.css";
import * as ElmentPlusIconVue from "@element-plus/icons-vue";
import { createPinia } from "pinia";

const app = createApp(App);

for (const [key, component] of Object.entries(ElmentPlusIconVue)) {
  app.component(key, component);
}
const pinia = createPinia();

app.use(ElmentPlus).use(pinia).mount("#app");
