<script setup>
const { title, description, id } = defineProps({
  title: String,
  description: String,
  id: Number,
});
</script>

<template>
  <div class="vertical card" @click="$router.push(`/game/${id}`)">
    <h1>{{ title }}</h1>
    <p>{{ description }}</p>
  </div>
</template>

<style lang="css" scoped>
.vertical {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.card {
  background-color: #303030;
  border-radius: 20px;
  padding: 16px;
}

.card:hover {
  background-color: #404040;
  cursor: pointer;
}

h1 {
  font-size: large;
  font-weight: bold;
}

h2 {
  font-size: medium;
  font-weight: bold;
}

span {
  color: gray;
}
</style>
