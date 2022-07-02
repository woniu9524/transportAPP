<template>
    <van-tabbar route>
        <van-tabbar-item replace to="/cargoOwner" icon="home-o">首页</van-tabbar-item>
        <van-tabbar-item replace to="/service" icon="search">服务</van-tabbar-item>
        <van-tabbar-item replace to="/mySpace" icon="manager-o">我的</van-tabbar-item>
    </van-tabbar>
</template>

<script>
    export default {
        name: "NavBar"
    }
</script>

<style scoped>

</style>
