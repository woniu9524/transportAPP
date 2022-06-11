import {createRouter, createWebHashHistory} from "vue-router";
//创建路由规则
const routes = [
    {
        path: "/",
        redirect: '/login',
        component: () => import("../view/login/index.vue")
    },
    {
        path: "/login",
        name: "login",
        component: () => import("../view/login/index.vue")
    },

]
//创建路由实例
const router=createRouter({
    history:createWebHashHistory(),
    routes,
})
//到APP.vue指定路由出口 <router-view></router-view>
export default router;
