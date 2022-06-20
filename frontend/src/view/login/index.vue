<template>
    <van-nav-bar title="登录"/>
    <div class="login-body">
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field
                        v-model="userId"
                        name="用户名"
                        label="用户名"
                        placeholder="用户名"
                />
                <van-field
                        v-model="password"
                        type="password"
                        name="密码"
                        label="密码"
                        placeholder="密码"
                />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                    登录
                </van-button>
                <van-button round block type="success" class="common-margin-top" @click="toRegister">
                    注册
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script>
    import {addLogin,isLogin} from "../../api/loginAndRegister";
    import Toast from 'vant/lib/toast';
    export default {
        name: "login",
        data() {
            return {
                type: "hasLogin",
                userId: "",
                password: "",
            }
        },
        methods: {
            toRegister(){
                this.$router.push({path: '/register'})
            },
            onSubmit(){
                const that=this;
                addLogin({"userId":this.userId,"password":this.password}).then(function (res) {
                    if (res.code===200){
                        that.goHome(res.data.user_type);
                    }else {
                        Toast({
                            message: res.message,
                            position: 'top',
                        });
                    }
                });
            },
            goHome(val) {
                this.$router.push({path: '/mySpace'})
            },
            //判断是否登录
            hasLogin(){
                const that=this;
                if (localStorage.getItem("token")){
                    isLogin().then(function (res) {
                        if (res.code===200){
                            console.log(res.data.userType);
                            that.$router.push({path: '/mySpace'})
                        }else {
                            localStorage.removeItem("token");
                        }
                    })
                }
            }

        },
        mounted() {
            this.hasLogin();
        }
    }
</script>

<style scoped>
    .login-body {
        text-align: center;
        margin-top: 200px;

    }
</style>
