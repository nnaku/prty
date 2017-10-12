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
					<b-btn v-b-tooltip.hover.auto title="As a host you can create a question game and hosts that anywhere you are! When you are, for example, with your buddies or in a party with guests, invite them to a special game where the questions are made by yourself! Nothing is stopping you to create questions of animals, movies or for example of yourself or your buddies. How about wedding where all of the guests can take a part to a question game which include questions of a newly married? Yeah right, sounds cool! This is a fun way to mix up the typical party where everyone just hangs out or plays bingo, and it gives you a low pressure to order parties - an agenda is already planned and you have an impact on contents!">Why become to a host?</b-btn>
					<b-nav-item :to="{ name: 'Register'}" href="#">Register to a host</b-nav-item>
					<b-nav-item :to="{ name: 'Login'}" href="#">Login</b-nav-item>
				</b-nav>
			</b-collapse>
		</b-navbar>
	</div>
	<div class="image">
		<img src="../../assets/letsplay.png" alt="Let's Play">
	</div>
	<div class="code">
		<b-form-input v-model="text1" type="text" placeholder="PLEASE, ADD YOUR CODE HERE">
		</b-form-input>
	</div>
</div>
</template>
