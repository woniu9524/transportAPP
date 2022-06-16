<template>
    <van-nav-bar title="登录"/>
    <div class="login-body">
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field
                        v-model="username"
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
    export default {
        name: "login",
        data() {
            return {
                type: "hasLogin",
                username: "",
                password: "",
            }
        },
        methods: {
            toRegister(){
                this.$router.push({path: '/register'})
            },
        }
    }
</script>

<style scoped>
    .login-body {
        text-align: center;
        margin-top: 200px;

    }
</style>
