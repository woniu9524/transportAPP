<template>
    <return-bar/>
    <p>未完成</p>
    <NavBar/>
</template>

<script>
    import ReturnBar from "../../components/returnBar/ReturnBar.vue";
    import NavBar from "../../components/navBar/NavBar.vue";

    export default {
        name: "Service",
        components:{
          ReturnBar,
          NavBar,
        },
    }
</script>

<style scoped>

</style>
