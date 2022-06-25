<template>
    <ReturnBar title="修改信息"></ReturnBar>
    <div class="register-form">
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field
                        v-model="infoForm.name"
                        name="姓名"
                        label="姓名"
                        placeholder="姓名"
                        :rules="[{ required: true, message: '请填写姓名' }]"
                />

                <!--TODO 选择性别-->
                <van-radio-group
                        v-model="infoForm.gender"
                        direction="horizontal">
                    <van-radio name="男">男</van-radio>
                    <van-radio name="女">女</van-radio>
                </van-radio-group>


                <van-field
                        v-model="infoForm.phone"
                        name="手机号码"
                        label="手机号码"
                        placeholder="手机号码"
                        :rules="[{ phone_pattern, message: '请输入正确内容' }]"
                />
                <van-field
                        v-model="infoForm.email"
                        name="邮箱"
                        label="邮箱"
                        placeholder="邮箱"
                />
                <van-field
                        v-model="infoForm.address"
                        name="居住地"
                        label="居住地"
                        placeholder="居住地"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                    修改
                </van-button>
            </div>
        </van-form>
    </div>

</template>

<script>
    import ReturnBar from "../../components/returnBar/ReturnBar.vue";
    import {getUserInfo, updateUserInfo} from "../../api/mySpace";

    export default {
        name: "updateInfo",
        components:{
          ReturnBar,
        },
        data(){
            return{
                infoForm:{
                    name:"",
                    gender:"",
                    phone:"",
                    email:"",
                    address:""
                },
                phone_pattern:'/^\d{15,17}$/',
            }
        },
        methods:{
            //获取个人信息
            getInfo() {
                const that = this;
                getUserInfo().then(function (res) {
                    if (res.code === 200) {
                        that.infoForm=res.data;

                    } else {
                        Toast(res.message);
                    }
                })
            },
            onSubmit(){
                const that = this;
                updateUserInfo().then(function (res) {
                    if (res.code === 200) {
                        this.$router.push({path: '/mySpace'});
                    } else {
                        Toast(res.message);
                    }
                })
            }
        },
        mounted() {
            this.getInfo();
        }
    }
</script>

<style scoped>

</style>
