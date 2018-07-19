import React, { Component, input, view, button, form } from 'react';
import logo from '../football.jpg';
import '../App.css';
import { Button } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import SideNav, {MenuIcon} from 'react-simple-sidenav';
 


class Signup extends Component {
  
  render() {
    return (
        <div class="col-md-8 offset-md-2">
        <span class="anchor" id="formUserEdit"></span>
        <hr class="my-5"/>
        <div class="card card-outline-secondary">
            <div class="card-header">
                <h3 class="mb-0">User Information</h3>
            </div>
            <div class="card-body">
                <form class="form" role="form" autocomplete="off">
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>First name*</label>
                        <div class="col-lg-9">
                            <input class="form-control" type="text" value="Jane"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>Last name*</label>
                        <div class="col-lg-9">
                            <input class="form-control" type="text" value="Bishop"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>Email*</label>
                        <div class="col-lg-9">
                            <input class="form-control" type="email" value="email@gmail.com"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>Username*</label>
                        <div class="col-lg-9">
                            <input class="form-control" type="text" value="janeuser"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>Phone Number</label>
                        <div class="col-lg-9">
                            <input class="form-control" type="url" value=""/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>Date of Birth</label>
                        <div class="col-lg-9">
                            <select id="user_time_zone" class="form-control" size="0">
                                <option value="Hawaii">(GMT-10:00) Hawaii</option>
                                <option value="Alaska">(GMT-09:00) Alaska</option>
                                <option value="Pacific Time (US &amp; Canada)">(GMT-08:00) Pacific Time (US &amp; Canada)</option>
                                <option value="Arizona">(GMT-07:00) Arizona</option>
                                <option value="Mountain Time (US &amp; Canada)">(GMT-07:00) Mountain Time (US &amp; Canada)</option>
                                <option value="Central Time (US &amp; Canada)" selected="selected">(GMT-06:00) Central Time (US &amp; Canada)</option>
                                <option value="Eastern Time (US &amp; Canada)">(GMT-05:00) Eastern Time (US &amp; Canada)</option>
                                <option value="Indiana (East)">(GMT-05:00) Indiana (East)</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>Password*</label>
                        <div class="col-lg-9">
                            <input class="form-control" type="password" value="11111122333"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}>Confirm*</label>
                        <div class="col-lg-9">
                            <input class="form-control" type="password" value="11111122333"/>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label style={{marginLeft: "20px"}}></label>
                        <div class="col-lg-9">
                            <input type="button" class="btn btn-primary" value="Sign Up"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
   
               
    );
  }
}

export default Signup;
