import React, { Component, input, view, button, form } from 'react';
import logo from '../football.jpg';
import '../App.css';
import { Button } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import SideNav, {MenuIcon} from 'react-simple-sidenav';
 


class TestInput extends Component {
  
  render() {
    return (
            <div>
            <div class="form-group">
                <label for="uname1">Username</label>
                <input type="text" class="form-control form-control-lg rounded-0" name="uname1" id="uname1" required=""/>
                <div class="invalid-feedback">Oops, you missed this one.</div>
            </div>
            <div class="form-group">
                <label>Password</label>
                <input type="password" class="form-control form-control-lg rounded-0" id="pwd1" required="" autocomplete="new-password"/>
                <div class="invalid-feedback">Enter your password too!</div>
            </div>
            </div>
    );
  }
}

export default TextInput;
