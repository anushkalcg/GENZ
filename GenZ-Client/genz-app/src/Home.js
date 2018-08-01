import  React, {Component} from 'react';
import {Provider} from 'react-redux';
import store from './store'
import HomeScreen from './HomeScreen';

export default () => (
    <Provider store={store}>
    <HomeScreen/>
    </Provider>
);