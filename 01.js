async function f(){
    return Promise.reject('123');
}

console.log(f());