import React, { Component } from 'react';
import logo from './logo.svg';
import slogan from './components/slogan.jpg'
import './App.css';
import CustomButton from './components/CustomButton';
import Header from './components/Header'
import { Link } from 'react-router-dom'
import about from './components/about.jpg'
import rules from './components/rules.jpg'


class App extends Component {
  render() {
    return (
      <div className="App">
          
        <div >
          {/* <h2>BACK YOURSELF</h2>
          <h3>BE YOUR GROUP CHAMPION</h3> */}
          <img src={slogan} className="App-slogan"/>
        </div>
        <div style={{marginTop: "-40px"}}>
        <Header className="App-header"/>
        </div>

         
        <div className="App-start">
          <Link to='/login'><CustomButton buttonText="Log In"/></Link><Link to='/signup'><CustomButton buttonText="Join Now"/></Link>
        </div>
        <img src={about} style={{width: "100%"}}/>
        <br/><br/>
        <img src={rules} style={{width: "100%"}}/>

        {/* <div className="App-intro">
          <h5 style={{alignContent: "left"}}>
          How to play:
          <br/><br/>- Create a league and share with your friends!
          <br/><br/>- 5 questions about the upcoming matches each week!
          <br/><br/>- Gain points and see results on your League Table!
          <br/><br/>- Compete against your friends for bragging rights!
          <br/><br/>- £2000 worth of free bets up for grabs every week!

          </h5>
        </div> */}
      </div>
    );
  }
}

export default App;
