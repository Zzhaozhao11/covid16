function timeout(ms) {
    return new Promise((resolve) => {
      setTimeout(()=>{
        console.log(111);
      resolve();
      }, ms);
    });
  }
  
  async function asyncPrint(value, ms) {
    await timeout(ms);
    console.log(value);
  }
  
  asyncPrint('hello world', 1000);