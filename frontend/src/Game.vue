<script setup>
import { defineProps } from "vue";

const { title, description, reviews } = defineProps({
  title: String,
  description: String,
  reviews: Array,
});
</script>

<template>
  <div class="vertical">
    <h1>{{ title }}</h1>
    <p>{{ description }}</p>
    <h2>Reviews</h2>
    <div class="vertical" v-for="review in reviews">
      <h3>{{ review.title }}</h3>
      <small>{{ review.author }}</small>
      <p>{{ review.content }}</p>
      <span>Rating: {{ review.rating }} / 5</span>
    </div>
  </div>
</template>

<style lang="css" scoped>
.vertical {
  display: flex;
  flex-direction: column;
  gap: 16px;
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
