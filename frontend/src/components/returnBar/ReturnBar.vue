<template>
    <van-nav-bar
            :title="title"
            left-text="返回"
            left-arrow
            @click-left="onClickLeft"
    />
</template>

<script>
    export default {
        name: "returnBar",
        props:{
            title: { type: String, required: true, default: "" }
        },
        methods:{
            onClickLeft() {
                history.back();
            },
        }
    }
</script>

<style scoped>

</style>
