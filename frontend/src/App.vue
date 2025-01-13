<script setup>
import Game from './Game.vue';
import { ref, effect } from 'vue';

let games = ref([]);
effect(() => {
  fetch('http://localhost:8080/games')
    .then(res => res.json())
    .then(data => games.value = data)
});

let titleInput = ref('');
let descriptionInput = ref('');

function handleGameSubmit() {
  if (titleInput.value === '' || descriptionInput.value === '') {
    return;
  }

  console.log(titleInput.value, descriptionInput.value);

  fetch('http://localhost:8080/games', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json'
    },
    body: JSON.stringify({
      title: titleInput.value,
      description: descriptionInput.value
    })
  }).catch(console.error).finally(() => {
    titleInput.value = '';
    descriptionInput.value = '';
    window.location.reload();
  });
}
</script>

<template>
  <div class="horizontal stretch">
    <div class="vertical">
      <h1>Games</h1>
      <Game v-for="game in games" :title="game.title" :description="game.description" :reviews="game.reviews" />
    </div>
    <div class="vertical card">
      <h2>Add a game</h2>
      <input type="text" name="title" placeholder="Title" v-model="titleInput" />
      <input type="text" name="description" placeholder="Description" v-model="descriptionInput" />
      <button type="button" @click="handleGameSubmit">Add</button>
    </div>
  </div>
</template>

<style scoped>
.vertical {
  display: flex;
  flex-direction: column;
  gap: 16px;
}

.horizontal {
  display: flex;
  flex-direction: row;
  gap: 16px;
}

.stretch {
  width: 100%;
  height: 100%;
  justify-items: stretch;
  align-items: stretch;
}

button {
  color: white;
}
</style>
