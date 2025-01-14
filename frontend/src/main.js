import { createApp } from "vue";
import "./style.css";
import App from "./App.vue";
import { createRouter, createWebHistory } from "vue-router";
import HomePage from "./HomePage.vue";
import GamePage from "./GamePage.vue";

const routes = [
  { path: "/", component: HomePage },
  { path: "/game/:id", component: GamePage },
];
const router = createRouter({
  history: createWebHistory(),
  routes,
});

createApp(App).use(router).mount("#app");
