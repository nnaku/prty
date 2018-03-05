<template>
  <div id="about">
     <div class="generalinfo">
       <!-- Messages are from locale/locales.json -->
       <p class="title"> {{ $t('message.generalTitle') }} </p>
       <p class="info">{{ $t('message.generalInfoLine1') }}<br><br>
       {{ $t('message.generalInfoLine2') }}<br><br>
        <ul>
          <li>{{ $t('message.generalLi1') }}</li>
          <li>{{ $t('message.generalLi2') }}</li>
          <li>{{ $t('message.generalLi3') }}</li>
        </ul>
      </p>
     </div>
     <div class="about">
      <p class="title">{{ $t('message.hostTitle') }}</p>
      <p class="info">{{ $t('message.hostInfoLine1') }}<br><br>
      <br>
        <ul>
          <li>{{ $t('message.hostLi1') }}</li>
          <li>{{ $t('message.hostLi2') }}</li>
          <li>{{ $t('message.hostLi3') }}</li>
          <li>{{ $t('message.hostLi4') }}</li>
        </ul>
      </p>
      <p class="title">{{ $t('message.userTitle') }}</p>
      <p class="info"> {{ $t('message.userInfo')}} <br><br>
        <ul>
          <li>{{ $t('message.userLi1') }}</li>
          <li>{{ $t('message.userLi2') }}</li>
          <li>{{ $t('message.userLi3') }}</li>
        </ul>
      </p>
     </div>
     </div>
</template>

<script>
export default {
  name: "about"
};
</script>

<style scoped>
.generalinfo {
  background-color: #8db9b0;
  width: 70%;
  display: block;
  margin: 1.1em auto 0 auto;
  height: 100%;
  padding-top: 0.5em;
  color: white;
  height: 100%;
  font-size: 21px;
}

.about {
  background-color: #8db9b0;
  width: 70%;
  margin: 0.3em auto;
  padding-top: 0.5em;
  padding-bottom: 0.5em;
  color: white;
  font-size: 21px;
}

.title {
  font-weight: bold;
  text-transform: uppercase;
  text-align: center;
  margin-bottom: -1em;
}

.info {
  text-align: justify;
  padding: 1.5em 3.5em 0.1em 3.5em;
}

li {
  font-weight: bold;
}

@media only screen and (max-width: 768px) {
  .generalinfo {
    width: 90%;
    font-size: 15px;
  }
  .about {
    margin: 1% auto 1%;
    width: 90%;
    height: 100%;
    font-size: 15px;
  }
  .info {
    text-align: justify;
    padding: 1.5em 2.2em 0.1em 2.2em;
  }
}
</style>



