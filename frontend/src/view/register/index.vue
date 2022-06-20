<template>
    <div class="register">
        <van-nav-bar
                title="注册"
                left-text="返回"
                left-arrow
                @click-left="onClickLeft"
        />
        <div class="register-form">
            <van-form @submit="onSubmit">
                <van-cell-group inset>
                    <van-field
                            v-model="registerForm.userId"
                            name="用户名"
                            label="用户名"
                            placeholder="用户名"
                            :rules="[{ required: true, message: '请填写用户名' }]"
                    />
                    <van-field
                            v-model="registerForm.password"
                            type="password"
                            name="密码"
                            label="密码"
                            placeholder="密码"
                            :rules="[{ required: true, message: '请填写密码' }]"
                    />
                    <van-field
                            v-model="registerForm.name"
                            name="姓名"
                            label="姓名"
                            placeholder="姓名"
                            :rules="[{ required: true, message: '请填写姓名' }]"
                    />

                    <!--TODO 选择性别-->
                    <van-radio-group
                            v-model="registerForm.gender"
                            direction="horizontal">
                        <van-radio name="男">男</van-radio>
                        <van-radio name="女">女</van-radio>
                    </van-radio-group>
                    <!--选择身份-->
                    <van-field
                            v-model="type_value"
                            is-link
                            readonly
                            label="身份"
                            placeholder="选择身份"
                            @click="showPicker = true"
                    />
                    <van-popup v-model:show="showPicker" round position="bottom">
                        <van-picker
                                :columns="type_columns"
                                @cancel="showPicker = false"
                                @confirm="onConfirm"
                        />
                    </van-popup>

                    <van-field
                            v-model="registerForm.phone"
                            name="手机号码"
                            label="手机号码"
                            placeholder="手机号码"
                            :rules="[{ phone_pattern, message: '请输入正确内容' }]"
                    />
                    <van-field
                            v-model="registerForm.email"
                            name="邮箱"
                            label="邮箱"
                            placeholder="邮箱"
                    />
                    <van-field
                            v-model="registerForm.address"
                            name="居住地"
                            label="居住地"
                            placeholder="居住地"
                    />
                </van-cell-group>
                <div style="margin: 16px;">
                    <van-button round block type="primary" native-type="submit">
                        注册
                    </van-button>
                </div>
            </van-form>
        </div>
    </div>
</template>

<script>
    import {addUser} from "../../api/loginAndRegister";
    import Toast from 'vant/lib/toast';
    export default {
        name: "register",
        data(){
            return{
                registerForm:{
                    userId:"",
                    password:"",
                    userType:"0",
                    name:"",
                    gender:"",
                    phone:"",
                    email:"",
                    address:""
                },
                showPicker:false,
                type_columns:["司机","车主","车主和司机","货主"],
                type_value:"",//角色类型的值
                phone_pattern:'/^\d{15,17}$/',
            }
        },
        methods:{
            onSubmit:function () {
                const that=this;
                addUser(this.registerForm).then(function (res) {
                    if (res.code===200){
                        Toast({
                            message: "注册成功",
                            position: 'top',
                        });
                        //页面跳转
                        that.goHome();

                    }else {
                        Toast({
                            message: res.message,
                            position: 'top',
                        });
                    }
                })

            },
            //进入主页
            goHome(val){
                this.$router.push({path: '/mySpace'})
            },
            onClickLeft:function () {
                history.back();
            },
            onConfirm:function(value, index){
                this.registerForm.userType=index;
                this.showPicker=false;
                this.type_value=this.type_columns[index];
            }
        }
    }
</script>

<style scoped>
    .register-form{
        margin-top: 50px;
    }

</style>
