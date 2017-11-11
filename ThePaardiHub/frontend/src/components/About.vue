<template>
  <div id="about">
     <div class="abouthost">
      <p class="title">Host</p>
      <p class="info">As a user you can ... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas elit nunc, volutpat et eros ut, 
        convallis scelerisque urna. Nullam sodales felis et risus sollicitudin, ac feugiat nisl pretium. Donec ultrices, est in
        bibendum commodo, tortor felis congue sapien, et ornare nibh sem non diam. Etiam vel mattis arcu, sed lacinia diam. Interdum 
        et malesuada fames ac ante ipsum primis in faucibus.<br><br>
        primis in faucibus:<br>
        <ul>
          <li>Ut nulla ante, placerat sit amet aliquam molestie</li>
          <li>dignissim nec eros</li>
          <li>Praesent luctus scelerisque</li>
        </ul>
      </p>
     </div>
     <div class="aboutuser">
      <p class="title">User</p>
      <p class="info">As a user you can ... Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas elit nunc, volutpat et eros ut, 
        convallis scelerisque urna. Nullam sodales felis et risus sollicitudin, ac feugiat nisl pretium. Donec ultrices, est in
        bibendum commodo, tortor felis congue sapien, et ornare nibh sem non diam. Etiam vel mattis arcu, sed lacinia diam. Interdum 
        et malesuada fames ac ante ipsum primis in faucibus.<br><br>
        primis in faucibus:<br>
        <ul>
          <li>Ut nulla ante, placerat sit amet aliquam molestie</li>
          <li>dignissim nec eros</li>
          <li>Praesent luctus scelerisque</li>
        </ul>
      </p>
     </div>
  </div>
</template>

<script>

export default{
  name: "about"
};
</script>

<style scoped>

  .abouthost, .aboutuser {
    background-color: #8DB9B0;
    width: 40%;
    display: inline-block;
    margin-top: 3%;
    height: 100%;
    padding-top: 0.5em;
    color: white;
  }

  .title {
    font-weight: bold;
    text-transform: uppercase;
    text-align: center;
  }

  .info {
    text-align: justify;
    padding: 1.5em;
  }

@media screen and (max-width: 790px) {

  .abouthost, .aboutuser {
    display: block;
    margin: 3% auto 3%;
    width: 80%;
    height: 100%;
  }
}

</style>



