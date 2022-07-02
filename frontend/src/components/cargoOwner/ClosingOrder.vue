<template>
    <van-list
            v-model:loading="loading"
            :finished="finished"
            finished-text="没有更多了"
            @load="onLoad"
            v-if="listShow"
    >
        <OrderCard
                v-for="(item,index) in carInfoList"
                :name="item.name"
                :bad-mark="item.opposition"
                :good-mark="item.support"
                :car-id="item.carId"
                :car-info="[item.carSize,item.carGood,item.carWeight]"
                :from="item.from"
                :to="item.to"
                :head-url="item.photo"

                @getCardData="getCardData"
        />
        <!--<OrderCard
                name="小丁"
                bad-mark="0"
                car-id="苏LUS837"
                :car-info="['17.5/13.7米','高低板','8~20吨']"
                from="常州 天宇"
                to="宿迁 泗洪"
                head-url="http://pic.616pic.com/ys_bnew_img/00/10/83/54xXAJVRIn.jpg"
                good-mark="0"

                @getCardData="getCardData"
        />-->

    </van-list>
    <fill-order
            :car-id="fillForm.carId"
            :from="fillForm.from"
            :to="fillForm.to"
            v-if="!listShow"
            @fillOrderReturn="listShow=!listShow"
    />

</template>

<script>
    import OrderCard from "./orderCard/OrderCard.vue";
    import FillOrder from "./FillOrder.vue";
    import {getCarInfo} from "../../api/carOwner";


    export default {
        name: "ClosingOrder",
        components: {
            OrderCard,
            FillOrder,
        },
        data() {
            return {
                loading: false,
                finished: true,
                list: [1, 2, 3, 4],
                listShow:true,
                fillForm:{
                    carId:"",
                    from:"",
                    to:"",
                },
                carInfoList:[]
            }
        },
        methods: {
            //获取车辆信息
            getCarInfo(){
                const that=this;
                getCarInfo().then(function (res) {
                    if (res.code===200){
                        that.carInfoList=res.data;

                    }else {
                        Toast({
                            message: res.message,
                            position: 'top',
                        });
                    }
                })
            },
            getCardData(card){
                this.fillForm.carId=card.carId;
                this.fillForm.from=card.from;
                this.fillForm.to=card.to;
                this.listShow=!this.listShow;
            }
        },
        mounted() {
            this.getCarInfo();
        }
    }
</script>

<style scoped>

</style>
