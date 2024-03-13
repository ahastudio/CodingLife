<script setup>
import Header from './components/Header.vue';
</script>

<template>
  <div class="app">
    <Header />
    <router-view></router-view>
  </div>
</template>

<style scoped>
.app {
  margin-block: 2rem;
  margin-inline: auto;
  width: 80%;
}
</style>
