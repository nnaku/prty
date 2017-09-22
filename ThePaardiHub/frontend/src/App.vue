<style src="./components/Styles/styles.css"></style>

<template>
  <div id="app">

    <div class="header">
        <p>App.vue: Joten navit headerit yms yms. tänne!</p>
    </div>
    <p>App.vue: Tää näkyy näkyy joka sivulle!</p>
    <router-view></router-view>
    <div class="footer">
        <p>App.vue: Esim footer route-view:n alle</p>
    </div>
  </div>
</template>

<!-- ei hajuakaan mitä tää tekee -->
<script>
export default {
  name: 'app',
}
</script>
