<template>
    <div class="test">
        <ul v-if="posts && posts.lenght">
         <li v-for = "post in posts" :key="post.ID">
                <p><strong>{{post.ID}}</strong></p>
                <p>{{post.RoleName}}</p>
            </li>
        </ul>
    </div>
</template>

<script>
import axios from 'axios';
 export default {
   name: "test",
   data: () =>({
       posts:[],
       errors:[]
   }),

    created(){
            axios.get("http://localhost:8080/roles")
            .then(response => {
                console.log(response.data)
            })
            .catch(e => {
                this.errors.push(e)
            })
    }

 }
 </script>
  