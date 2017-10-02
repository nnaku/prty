export default {
    data() {
      return {
        activeIndex: '1',
        text1: ''
      };
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      }
    }
  }
