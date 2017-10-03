<script src="./register.js"></script>
<style src="./styles.css"></style>

<template>
    <div id="Register">
        <div class="registerheader">
            <h2 class="title">Register</h2>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                        </div>
                        <div class="panel-body">
                            <form @submit.prevent="submitLogin">
                                <div class="form-group has-feedback" v-bind:class="[emailSuccessClass]">
                                    <label for="email" class="control-label sr-only">Add your email address.</label>
                                    <input ref="txtEmail" type="email" class="form-control" @input="checkEmailValidation" id="email" placeholder="Email" autofocus required>
                                    <span v-bind:class="[emailIconClass]"></span>
                                </div>
                                <div class="form-group has-feedback" v-bind:class="[passwordSuccessClass]">
                                    <label for="password" class="control-label sr-only">Add your password.</label>
                                    <input ref="txtPassword" type="password" class="form-control" @input="checkPasswordValidation" id="password" placeholder="Password" required :pattern="passwordPattern" aria-describedby="passwordHelpSpan">
                                    <span v-bind:class="[passwordIconClass]"></span>
                                    <small id="passwordHelpSpan">{{ passwordMessage }}</small>
                                </div>
                                <div v-if="errorMessage" class="text-center text-danger">{{ errorMessage }}</div>
                                <button :disabled="submitBtnDisabled" class="btn btn-primary pull-right" onclick="register()">Sign In</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>