<template>
    <div class="test">
        <h1>TestResti</h1>
            <ul v-if="posts.length">
                <li v-for="post in posts">
                    <h3>{{ post.title}}</h3>
                    <p>{{ post.body}}</p>
                </li>
            </ul>   
        <p v-else>No todos left!</p>    
    </div>
</template>

<script>
import axios from "axios";
export default {
  name: "test",
  data() {
    return {
      posts: [],
      errors: []
    };
  },
  created() {
    axios
      .get(`https://jsonplaceholder.typicode.com/posts`)
      .then(response => {
        this.posts = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  }
};
</script>

<style>
ul {
  list-style-type: none;
}
</style>
