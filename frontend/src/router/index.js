import {createRouter, createWebHashHistory} from "vue-router";
//创建路由规则
const routes = [
    {
        path: "/",
        redirect: '/mySpace',
    },
    {
        path: "/login",
        name: "login",
        component: () => import("../view/login/index.vue")
    },
    {
        path: "/register",
        name: "register",
        component: () => import("../view/register/index.vue")
    },
    {
        path: "/mySpace",
        name: "mySpace",
        component: () => import("../view/mySpace/index.vue")
    },
    {
        path: "/updateInfo",
        name: "updateInfo",
        component:()=>import("../view/mySpace/updateInfo.vue")
    }
]
//创建路由实例
const router=createRouter({
    history:createWebHashHistory(),
    routes,
})
//到APP.vue指定路由出口 <router-view></router-view>
export default router;
