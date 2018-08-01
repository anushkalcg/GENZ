import React, { Component } from 'react';
import logo from './logo.svg';
import lcg from './components/lcg.png'

import './App.css';
import CustomButton from './components/CustomButton';
import Header from './components/Header'
import { Link } from 'react-router-dom'


class App extends Component {
  render() {
    return (
      <div className="App">
        <Header/>
        <div className="App-slogan">
          <h2>BACK YOURSELF</h2>
          <h3>BE YOUR GROUP CHAMPION</h3>
        </div>

        <div className="App-start">
          <Link to='/login'><CustomButton buttonText="Log In"/></Link><Link to='/signup'><CustomButton buttonText="Join Now"/></Link>
        </div>

        <div className="App-intro">
          <h3>About Us</h3>
          <h5>Predict the Prem is a free to play premier league prediction game where you compete against your friends with the chance of winning real prizes!s</h5>
          <h5 style={{alignContent: "left"}}>
          How to play:
          <br/><br/>- Create a league and share with your friends!
          <br/><br/>- 5 questions about the upcoming matches each week!
          <br/><br/>- Gain points and see results on your League Table!
          <br/><br/>- Compete against your friends for bragging rights!
          <br/><br/>- £2000 worth of free bets up for grabs every week!

          </h5>
        </div>
      </div>
    );
  }
}

export default App;
