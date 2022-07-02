<template>
    <div class="card">
        <div class="title">
            <p>* 现金</p>
        </div>
        <div class="context">
            <van-field v-model="card.cash" placeholder="请输入金额" />
        </div>
    </div>
    <div class="card">
        <div class="title">
            <p>* 油卡</p>
        </div>
        <div class="context">
            <van-field v-model="card.oilCardNum" placeholder="请输入油卡数量" />
            <van-field v-model="card.oilCard" placeholder="请输入油卡面额" />
            <van-field v-model="card.oilStation" placeholder="请输入加油站" />
        </div>
    </div>
    <div class="bottom">
        <div class="label">
            <span>合计申请金额<span>{{sum}}</span>元</span>
        </div>
        <div class="label">
            <span style="color: red;font-weight: bold;font-size: 18px">余额剩余{{moneyForm.leftMoney}}</span>
            <span style="color: red">(油卡：{{moneyForm.oilCard}}元      现金：{{moneyForm.cash}}元)</span>
        </div>
        <van-button type="success" block @click="alert('还没做')">提交申请</van-button>
    </div>
</template>

<script>
    export default {
        name: "ApplyMoney",
        data(){
            return{
                card:{
                    cash:"",
                    oilCardNum:"",
                    oilCard:"",
                    oilStation:"",
                    sum:"",
                },
                moneyForm:{
                    leftMoney:"",
                    oilCard:"",
                    cash:"",
                }

            }
        },
        methods:{

        },

    }
</script>

<style scoped>
    .card{
        transition: transform .2s, box-shadow .2s;
        transform: translateY(-4px);
        box-shadow: 1px 1px 2px rgba(0, 0, 0, .1), -1px -1px 2px rgba(0, 0, 0, .1);
        width: 90%;
        margin: auto;
        margin-top: 20px;
    }
    .title{
        padding:10px 15px 10px 15px;
        font-weight: bold;
    }
    .bottom{
        width: 98%;
        margin-left: auto;
        margin-right: auto;
        margin-top: 120px;
    }
    .label{
        transition: transform .2s, box-shadow .2s;
        transform: translateY(-4px);
        box-shadow: 4px 4px 8px rgba(0, 0, 0, .1), -4px -4px 8px rgba(0, 0, 0, .1);
        text-align: center;
    }
</style>
