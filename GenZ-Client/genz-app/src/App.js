import React, { Component, input, view, button, form } from 'react';
import logo from './football.jpg';
import './App.css';
import LeagueTable from './components/LeagueTable'
import { Button } from 'reactstrap';
// import 'bootstrap/dist/css/bootstrap.min.css';
import SideNav, {MenuIcon} from 'react-simple-sidenav';
import Navigation, {NavLink} from 'react-navigate'
import Login from './components/Login';
import Signup from './components/Signup';
 


class App extends Component {
  
  render() {
    return (
    <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Predikt the Prem</h1>
        </header>
        <Login/>
        <Signup/>
        <LeagueTable/>
       
    </div>

      
    );
  }
}

export default App;
