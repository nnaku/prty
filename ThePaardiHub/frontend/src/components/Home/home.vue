<script src="./home.js"></script>
<style src="./styles.css"></style>

<template>
<div id="Home">
<div class="homeheader">
<h2 class="title">PARTY HUB</h2>
<!-- nav -->
<b-navbar toggleable="md">
  <b-nav-toggle target="nav_collapse"></b-nav-toggle>
  <b-collapse is-nav id="nav_collapse">
    <b-nav is-nav-bar>
      <b-nav-item active href="#">Let's Play!</b-nav-item>
      <b-nav-item :to="{ name: 'Login'}" href="#">Login</b-nav-item>
      <b-nav-item :to="{ name: 'Register'}" href="#">Register</b-nav-item>
    </b-nav>
  </b-collapse>
</b-navbar>
</div>
<div class="image">
<img src="../../assets/letsplay.png" alt="Let's Play">
</div>
<div class="code">
    <b-form-input v-model="text1"
                  type="text"
                  placeholder="PLEASE, ADD YOUR CODE HERE">
    </b-form-input>
</div>
</div>
</template>
