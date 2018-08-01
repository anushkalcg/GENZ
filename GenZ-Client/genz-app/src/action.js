export const REQUEST_HELLO_WORLD ='HELLO_WORLD';

export const RECEIVE_HELLO_WORLD ='HELLO_WORLD';

export const requestHelloWorld = () => ({type: REQUEST_HELLO_WORLD});

export const receiveHelloWorld = text => ({type: RECEIVE_HELLO_WORLD, text});