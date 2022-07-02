<template>
    <div class="fill-order">
        <van-cell-group inset>
            <van-field v-model="orderForm.clientName" label="客户名称" placeholder="请输入客户名"/>
            <van-cell title="选择日期" :value="orderForm.startTime" @click="dateShow = true" />
            <van-calendar v-model:show="dateShow" @confirm="onConfirm" />
            <van-field v-model="orderForm.receivingCompany" label="收货单位" placeholder="请输入收货单位"/>
            <van-field v-model="orderForm.receivingAddress" label="收货地址" placeholder="请输入收货地址"/>
            <van-field v-model="orderForm.paymentCompany" label="付款单位" placeholder="请输入付款单位"/>
            <van-field v-model="orderForm.carId" label="车&emsp;&emsp;号" placeholder="车牌号"/>
            <van-field v-model="orderForm.goodsName" label="货物名称" placeholder="货物名称"/>
            <van-field v-model="orderForm.contractId" label="合&nbsp;同&nbsp;号" placeholder="合同号"/>
            <van-field v-model="orderForm.cargoListId" label="交货单号" placeholder="交货单号"/>
            <van-field v-model="orderForm.from" label="出&nbsp;发&nbsp;地" placeholder="出发地"/>
            <van-field v-model="orderForm.to" label="目&nbsp;的&nbsp;地" placeholder="目的地"/>
            <van-field v-model="orderForm.cargoNum" label="发货数量" placeholder="发货数量"/>
            <van-field v-model="orderForm.price" label="承运运费" placeholder="承运运费"/>
        </van-cell-group>
    </div>
    <div style="text-align: center;margin-top: 10px">
        <van-row justify="center">
            <van-col span="4">
                <van-button round type="plain" @click="returnCard">返回</van-button>
            </van-col>
            <van-col span="8">
                <van-button round type="primary" @click="updateOrder">提交报表</van-button>
            </van-col>
        </van-row>
    </div>
</template>

<script>
    import ReturnBar from "../returnBar/ReturnBar.vue";
    import NavBar from "../navBar/NavBar.vue";
    import {getCarInfo, getOrder, updateOrder} from "../../api/carOwner";
    import {Toast} from "vant";

    export default {
        name: "FillOrder",
        components:{
            ReturnBar,
            NavBar
        },
        props:{
            carId:{type:String,required:true,default:""},
            from:{type:String,required:true,default:""},
            to:{type:String,required:true,default:""},
        },
        data(){
            return{
                orderForm:{
                    clientName:"",
                    startTime:"",
                    receivingCompany:"",
                    receivingAddress:"",
                    paymentCompany:"",
                    carId:this.carId,
                    goodsName:"",
                    contractId:"",
                    cargoListId:"",
                    from:this.from,
                    to:this.to,
                    cargoNum:"",
                    price:"",
                    remarks:"",
                },
                dateShow:false,
            }
        },
        methods:{
            formatDate(date) {
                return `${date.getFullYear()}/${date.getMonth() + 1}/${date.getDate()}`;
            },
            onConfirm(date) {
                this.dateShow = false;
                this.orderForm.startTime = this.formatDate(date);
            },
            returnCard(){
                this.$emit("fillOrderReturn");
            },
            updateOrder(){
                const that=this;
                updateOrder(this.orderForm).then(function (res) {
                    if (res.code===200){
                        Toast({
                            message: res.data,
                            position: 'top',
                        });
                        that.returnCard();
                    }else {
                        Toast({
                            message: res.message,
                            position: 'top',
                        });
                    }
                })
            },
            getOrder(){
                const that=this;
                getOrder({carId:this.carId}).then(function (res) {
                    if (res.code===200){
                        that.orderForm=res.data[0];
                        that.orderForm.from=that.from;
                        that.orderForm.to=that.to;
                    }else {

                    }
                })
            }
        },
        mounted() {
            this.getOrder();
        }
    }
</script>

<style scoped>

</style>
