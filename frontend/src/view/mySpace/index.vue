<template>
    <van-nav-bar
            title="个人信息"
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
    />
    <div class="person-info">
        <div class="basic-info">
            <van-row>
                <van-col span="8">
                    <van-image
                            round
                            src="https://fastly.jsdelivr.net/npm/@vant/assets/cat.jpeg"
                            fit="cover"
                            class="head-portrait"
                    />
                    <div >
                        <van-tag plain type="primary" size="large">{{username}}</van-tag>
                    </div>
                </van-col>
                <van-col span="8" offset="8">offset: 4, span: 10</van-col>
            </van-row>
        </div>
    </div>
    <NavBar></NavBar>
</template>

<script>
    import NavBar from "../../components/navBar/NavBar.vue"
    export default {
        name: "mySpace",
        components:{
          NavBar,
        },
        data(){
            return{
                username:"一只猫",
            }
        },
        methods:{
            onClickLeft(){
                history.back();
            },
        },
    }
</script>

<style scoped>
    .head-portrait{
        width: 2rem;
        height: 2rem;
    }
</style>
