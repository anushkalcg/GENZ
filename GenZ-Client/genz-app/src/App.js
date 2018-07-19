import React, { Component, input, view, button, form } from 'react';
import logo from './football.jpg';
import './App.css';
import { Button } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import SideNav, {MenuIcon} from 'react-simple-sidenav';
 


class App extends Component {
  
  render() {
    return (
      <div className="App">
        <header className="App-header">
          <img src={logo} className="App-logo" alt="logo" />
          <h1 className="App-title">Predikt the Prem</h1>
          
        </header>
        {/* <p className="App-intro">
          To get started, edit <code>src/App.js</code> and save to reload.
    </p> */}
        {/* <Input>Danger!</Input> <Input>Danger!</Input> */}
        {/* <view style={{paddingTop: "10px", paddingLeft: "10px"}} class="float-right">
        <input type="text" name="name" style= {{height: "40px"}}/> 
        <input type="text" name="name" style= {{height: "40px"}}/> 
        <button style= {{height: "40px"}}> Log In </button>
        </view> */}
        <form>
          <label>
            Username:  
            <input type="text" name="username" style={{margin: "10px"}}/>
          </label>
          <label>
            Password:  
            <input type="text" name="password" style={{margin: "10px"}}/>
          </label>
        </form>
        <Button type="submit" value="Submit" style={{margin: "10px"}}> Log In </Button>
        <Button type="submit" value="Submit" style={{margin: "10px"}}> Sign Up </Button>

  </div>

      
    );
  }
}

export default App;
