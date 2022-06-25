<template>
    <van-nav-bar
            title="个人信息"
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
    />

    <div class="user-info">
        <div class="head">
            <van-image
                    round
                    :src="imageUrl"
                    fit="cover"
                    class="head-portrait"
                    @click="showPopup=true"
            />
            <div class="user-desc">
                <span>{{ infoForm.userId }}</span>
            </div>
        </div>
        <div class="info common-margin-top">
            <van-cell-group>
                <van-cell title="姓名" :value="infoForm.name"/>
                <van-cell title="性别" :value="infoForm.gender"/>
                <van-cell title="手机" :value="infoForm.phone"/>
                <van-cell title="邮箱" :value="infoForm.email"/>
                <van-cell title="居住地" :value="infoForm.address"/>
            </van-cell-group>
        </div>

        <div class="btns">
            <van-button class="common-margin-top" type="plain" size="large" round @click="changeUserInfo">修改信息</van-button>
            <van-button class="common-margin-top" type="plain" size="large" round @click="logout">退出登录</van-button>
        </div>
    </div>


    <NavBar></NavBar>

    <van-popup
            v-model:show="showPopup"
            position="bottom"
            :style="{ height: '20%' }"
            round
    >
        <div class="popup-box">
            <van-uploader :after-read="afterRead">
                <van-button style="width: 350px;">更换头像</van-button>
            </van-uploader>
            <van-button block @click="showImage">查看大图</van-button>
            <van-button block>取消</van-button>
        </div>
    </van-popup>
</template>

<script>
    import NavBar from "../../components/navBar/NavBar.vue"
    import "../../common/style/commonStyle.css"
    import {addHeadPhoto, getUserInfo, loadHeadPhoto, updateUserInfo} from "../../api/mySpace";
    import {ImagePreview} from "vant";

    export default {
        name: "mySpace",
        components: {
            NavBar,
        },
        data() {
            return {
                username: "一只猫",
                loading: false,
                showPopup: false,
                infoForm: {},
                imageUrl: "http://pic.616pic.com/ys_bnew_img/00/10/83/54xXAJVRIn.jpg"
            }
        },
        methods: {
            onClickLeft() {
                history.back();
            },
            //更换头像
            afterRead(fileObj) {
                fileObj.status = "uploading";
                // 状态提示
                fileObj.message = "上传中...";
                // 声明form表单数据
                const formData = new FormData();
                // 添加文件信息
                formData.append("file", fileObj.file);
                // 上传接口调用
                const that = this;
                addHeadPhoto(formData).then(function (res) {
                    if (res.code === 200) {
                        that.imageUrl = res.data.imageUrl
                    } else {
                        Toast(res.message);
                    }
                })
                this.showPopup = false;

            },
            //加载头像
            loadPhoto() {
                const that = this;
                loadHeadPhoto().then(function (res) {
                    if (res.code === 200) {
                        that.imageUrl = res.data.imageUrl
                    }
                })
            },
            //查看大图
            showImage() {
                this.showPopup = false;
                ImagePreview([this.imageUrl]);
            },
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
            //修改信息
            changeUserInfo(){
                this.$router.push({path: '/updateInfo'});
            },
            //登出
            logout(){
                localStorage.clear();
                this.$router.push({path: '/login'})

            },
        },
        mounted() {
            this.loadPhoto();
            this.getInfo();
        }
    }
</script>

<style scoped>
    .head {
        margin-top: 10px;
        text-align: center;
    }

    .head-portrait {
        width: 100px;
        height: 100px;
    }

    .btns{
        margin: auto;
        margin-top: 30px;
        width: 80%;
        text-align: center;
    }

    .popup-box {
        text-align: center;
    }


</style>
