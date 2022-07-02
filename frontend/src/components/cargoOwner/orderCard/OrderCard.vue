<template>
    <div class="order-card">
        <van-row>
            <van-col span="16">
                <div class="order-card-left">
                    <div>
                        <span style="font-size: 20px;font-weight: bold">{{from}} → {{to}}</span>
                    </div>
                    <div>
                        <span>{{carId}}</span>
                    </div>
                    <div>
                        <span v-for="(item,index) in carInfo">
                            <van-tag color="#909ca4" plain >{{item}}</van-tag>
                            <span style="margin:5px"></span>
                        </span>

                    </div>
                    <div style="margin-top: 10px">
                        <van-row>
                            <van-col span="8">
                                <van-image
                                        width="50"
                                        height="50"
                                        :src="headUrl"
                                />
                            </van-col>
                            <van-col span="16">
                                <div>
                                    <span style="color: #909ca4;margin: 5px">{{name}}</span>
                                </div>
                                <div>
                                    <van-tag round type="primary">好评{{goodMark}}次</van-tag>
                                    <span style="margin: 5px"></span>
                                    <van-tag round type="danger">差评{{badMark}}次</van-tag>
                                </div>
                            </van-col>
                        </van-row>
                    </div>
                </div>
            </van-col>
            <van-col span="8">
                <div class="order-card-right">
                    <van-icon name="phone-o" size="40" style="margin-top: 20px"/>
                    <van-button type="primary" size="small" style="margin-top: 20px" @click="fillOrder">报表录入</van-button>
                </div>
            </van-col>
        </van-row>
        <van-divider/>
    </div>
</template>

<script>
    export default {
        name: "OrderCard",
        props:{
            from:{type:String,required:true,default:""},
            to:{type:String,required:true,default:""},
            carId:{type:String,required:true,default:""},
            carInfo:{type:Array,required:true,default:[]},
            headUrl:{type:String,required:true,default:""},
            name:{type:String,required:true,default:""},
            goodMark:{type:String,required:true,default:""},
            badMark:{type:String,required:true,default:""},
        },
        methods:{
            fillOrder(){
                this.$emit("getCardData",{
                    carId:this.carId,
                    from:this.from,
                    to:this.to
                })
                /*this.$router.push({
                    path:'/FillOrder',
                    query:{
                        carId:this.carId,
                        from:this.from,
                        to:this.to
                    }
                });*/

            },
        }
    }
</script>

<style scoped>
    .order-card{
        margin-top: 10px;
    }
    .order-card-left{
        padding-left: 20px;
    }
    .order-card-right{
        text-align: right;
        padding-right: 20px;
    }
</style>
