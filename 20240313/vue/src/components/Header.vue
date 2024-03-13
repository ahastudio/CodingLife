<script setup>
</script>

<template>
  <header>
    <nav>
      <ul>
        <li>
          <router-link to="/">Home</router-link>
        </li>
        <li>
          <router-link to="/about">About</router-link>
        </li>
      </ul>
    </nav>
  </header>
</template>

<style scoped>
header {
  padding-block: 2rem;
}

li {
  display: inline-block;
  margin-right: 1rem;
}
</style>
