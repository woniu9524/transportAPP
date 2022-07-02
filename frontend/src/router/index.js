import {createRouter, createWebHashHistory,createWebHistory} from "vue-router";
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
        path: "/service",
        name: "Service",
        component: () => import("../view/service/index.vue")
    },
    {
        path: "/updateInfo",
        name: "updateInfo",
        component:()=>import("../view/mySpace/updateInfo.vue")
    },
    {
        path: "/cargoOwner",
        name: "cargoOwner",
        component:()=>import("../view/cargoOwner/index.vue"),
    },
    {
        path: "/ClosingOrder",
        name: "ClosingOrder",
        component:()=>import("../components/cargoOwner/ClosingOrder.vue")
    },
    {
        path: "/FillOrder",
        name: "FillOrder",
        component:()=>import("../components/cargoOwner/FillOrder.vue")
    }


]
//创建路由实例
const router=createRouter({
    history:createWebHashHistory(),
    routes,
})
//到APP.vue指定路由出口 <router-view></router-view>
export default router;
