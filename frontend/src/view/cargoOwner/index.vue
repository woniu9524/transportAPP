<template>
    <van-nav-bar title="首页">
        <template #right>
            <van-icon name="search" size="18" />
        </template>
    </van-nav-bar>
    <van-tabs v-model:active="active">
        <van-tab title="寻找车辆">
            寻找车辆
        </van-tab>
        <van-tab title="成交订单">
            <closing-order></closing-order>
        </van-tab>
        <van-tab title="申请资金">
            <apply-money></apply-money>
        </van-tab>
       <!-- <van-tab title="支付资金">支付资金</van-tab>
        <van-tab title="签单回收">签单回收</van-tab>
        <van-tab title="合同冲账">合同冲账</van-tab>-->
    </van-tabs>
    <NavBar></NavBar>
</template>

<script>
    import NavBar from "../../components/navBar/NavBar.vue";
    import ApplyMoney from "../../components/cargoOwner/ApplyMoney.vue";
    import ClosingOrder from "../../components/cargoOwner/ClosingOrder.vue";
    export default {
        name: "index",
        components:{
            ClosingOrder,
            NavBar,
            ApplyMoney,
        },
        data(){
            return{
                active:1,
            }
        },
        methods:{

        },
        mounted() {

        }
    }
</script>

<style scoped>

</style>
