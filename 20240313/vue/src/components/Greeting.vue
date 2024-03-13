<script setup>
import { ref } from 'vue';

defineProps({
  name: String,
});

const count = ref(0);

function increment() {
  count.value += 1;
}
</script>

<template>
  <div class="greeting">
    <h1>Hello, {{ name }}!</h1>
    <div>
      <button type="button" @click="increment">
        Counter: {{ count }}
      </button>
    </div>
  </div>
</template>

<style scoped>
h1 {
  margin-bottom: 2rem;
  font-size: 4rem;
}
</style>
