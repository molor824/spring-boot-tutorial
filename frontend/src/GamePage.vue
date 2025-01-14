<script setup>
import { effect, ref } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();
let id = route.params.id;
let game = ref(null);

effect(() => {
    fetch(`http://localhost:8080/games/${id}`)
        .then(res => res.json())
        .then(res => game.value = res);
})

let titleInput = ref('');
let authorInput = ref('');
let contentInput = ref('');
let ratingInput = ref('');
const handleReviewSubmit = () => {
    if (!titleInput.value || !authorInput.value || !contentInput.value || !ratingInput.value) {
        return;
    }
    fetch('http://localhost:8080/reviews', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
            title: titleInput.value,
            author: authorInput.value,
            content: contentInput.value,
            rating: ratingInput.value,
            game: { id },
        })
    }).catch(console.error).finally(() => {
        titleInput.value = '';
        authorInput.value = '';
        contentInput.value = '';
        ratingInput.value = '';
        window.location.reload();
    });
}
</script>

<template>
    <div class="horizontal-screen">
        <div class="vertical">
            <h1>Add review</h1>
            <input type="text" placeholder="Title" v-model="titleInput" />
            <input type="text" placeholder="Author" v-model="authorInput" />
            <textarea type="text" placeholder="Content" v-model="contentInput" />
            <div>
                <input type="radio" name="rating" value="1" v-model="ratingInput" />
                <input type="radio" name="rating" value="2" v-model="ratingInput" />
                <input type="radio" name="rating" value="3" v-model="ratingInput" />
                <input type="radio" name="rating" value="4" v-model="ratingInput" />
                <input type="radio" name="rating" value="5" v-model="ratingInput" />
            </div>
            <button type="button" @click="handleReviewSubmit">Add</button>
        </div>
        <div>
            <h1>{{ game.title }}</h1>
            <p>{{ game.description }}</p>
            <h2>Reviews</h2>
            <div class="horizontal-wrapped">
                <div v-for="review in game.reviews" class="vertical">
                    <div class="horizontal-wrapped">
                        <h3>{{ review.title }}</h3>
                        <span>{{ review.rating }} / 5</span>
                    </div>
                    <small>{{ review.author }}</small>
                    <p>{{ review.content }}</p>
                </div>
            </div>
        </div>
    </div>
</template>

<style scoped>
.horizontal-wrapped {
    display: flex;
    flex-direction: row;
    flex-wrap: wrap;
    align-items: start;
    justify-content: center;
    gap: 16px;
}

.horizontal-screen {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    gap: 16px;
    width: 100%;
    height: 100%;
}

.vertical {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 16px;
}

button {
    color: white;
}
</style>