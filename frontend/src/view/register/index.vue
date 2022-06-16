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
                            v-model="registerForm.username"
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
    export default {
        name: "register",
        data(){
            return{
                registerForm:{
                    username:"",
                    password:"",

                }
            }
        },
        methods:{
            onSubmit:function () {

            },
            onClickLeft:function () {
                history.back();
            }
        }
    }
</script>

<style scoped>
    .register-form{
        margin-top: 50px;
    }

</style>
