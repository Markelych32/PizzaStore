import { createRouter, createWebHistory } from "vue-router";
import HomeView from "../views/HomeView.vue";

const routes = [
  {
    path: "/",
    name: "home",
    component: HomeView,
  },
  {
    path: "/login",
    name: "login",
    //component: () => import("../views/LoginView.vue"),
  },
  {
    path: "/register",
    name: "register",
    //component: () => import("../views/RegisterView.vue"),
  },
  {
    path: "/cart",
    name: "cart",
    //component: () => import("../views/CartView.vue"),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
