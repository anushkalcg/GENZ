import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Login.css'
import Header from './Header'
import {Redirect} from 'react-router-dom'
import axios from 'axios'


class Login extends Component {

  constructor(props) {
    super(props)
    this.state = {
    username: '',
    password: '',
    status: ''
    }
    this.handleChangedUsername=this.handleChangedUsername.bind(this);
    this.handleChangedPassword=this.handleChangedPassword.bind(this);
    this.loginRequest=this.loginRequest.bind(this);

  }

  handleChangedUsername(event){
    this.setState({username: event.target.value})
  }

  handleChangedPassword(event){
    this.setState({password: event.target.value})
  }

  loginRequest(){
    
    const url = 'http://localhost:8088/api/login?password='+this.state.password+'&username='+this.state.username
    axios.get(url).then(res=>this.props.history.push("/play")).catch(function(error){
      console.log(error)
    })
    console.log(this.state.username);
    console.log(this.state.status)
    // if (this.state.data.status === 200){
    //   console.log('je')
      
    // }

  }

  render() {
    return (
      <div>
        <Header/>
      <div className="Login">
        <div className="login">
          <div className="row"> 
            <div className="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">
              <h1>Get Read To Play</h1>
              <form role="form" className="ng-pristine ng-valid"> 
                <div className="form-content"> 
                  <div className="form-group"> 
                    <input type="text" className="form-control input-underline input-lg" placeholder="Username" value={this.state.username} onChange={(e) => this.handleChangedUsername(e)}/> 
                  </div> 
                  <div className="form-group"> 
                    <input type="password" className="form-control input-underline input-lg" placeholder="Password" value={this.state.password} onChange={(e) => this.handleChangedPassword(e)}/> 
                  </div> 
                </div> 
                <CustomButton buttonText="Login" onClick={this.loginRequest}/>
              </form> 
            </div> 
          </div> 
        </div>
      </div>
      </div>
    );
  }
}

export default Login;
