import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Login.css'
import axios from 'axios'
import Header from './Header'
// import DatePicker from 'react-datepicker';
// import moment from 'moment';
// import 'react-datepicker/dist/react-datepicker.css';
// import 'react-datepicker/dist/react-datepicker-cssmodules.css';



class Signup extends Component {

  constructor (props) {
    super(props)
    this.state = {
      age: '',
      email: '',
      name: '',
      surname: '',
      password: '',
      phoneNumber: '0289128',
      username: '',
      score: 0,
      userStatus: "NOT_STARTED"
    };
    this.signupRequest = this.signupRequest.bind(this);
    this.handleChangedPassword = this.handleChangedPassword.bind(this);
    this.handleChangedUsername = this.handleChangedUsername.bind(this);
    this.handleChangedName = this.handleChangedName.bind(this);
    this.handleChangedSurname = this.handleChangedSurname.bind(this);
    this.handleChangedEmail = this.handleChangedEmail.bind(this);
    this.handleChangedPhoneNumber = this.handleChangedPhoneNumber.bind(this);
    this.handleChangedAge = this.handleChangedAge.bind(this);

  }

  handleChangedUsername(event){
    this.setState({username: event.target.value})
  }

  handleChangedPassword(event){
    this.setState({password: event.target.value})
  }

  
  handleChangedName(event){
    this.setState({name: event.target.value})
  }
  
  handleChangedSurname(event){
    this.setState({surname: event.target.value})
  }
  
  handleChangedEmail(event){
    this.setState({email: event.target.value})
  }
  
  handleChangedPhoneNumber(event){
    this.setState({phoneNumber: event.target.value})
  }

  
  handleChangedAge(event){
    this.setState({age: event.target.value})
  }
  signupRequest() {
    const url = 'http://localhost:8088/api/user'
    axios.post(url,this.state).then(this.props.history.push("/login")).catch(function(error){
      console.log(error)
    })
  }
  render() {
    return (
      <div>
        <Header/>
        <div className="Login">
          <div className="login">
            <div className="row"> 
              <div className="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">
                <h1> Signup </h1>
                <form role="form" className="ng-pristine ng-valid"> 
                  <div className="form-content"> 
                    <div className="form-group"> 
                      <input type="text" className="form-control input-underline input-lg" placeholder="First Name" value={this.state.name} onChange={(e) => this.handleChangedName(e)}/>  
                    </div> 
                    <div className="form-group"> 
                      <input type="text" className="form-control input-underline input-lg" placeholder="Last Name" value={this.state.surname} onChange={(e) => this.handleChangedSurname(e)}/> 
                    </div> 
                    <div className="form-group"> 
                      <input type="text" className="form-control input-underline input-lg" placeholder="Email" value={this.state.email} onChange={(e) => this.handleChangedEmail(e)}/> 
                    </div> 
                    <div className="form-group"> 
                      <input type="text" className="form-control input-underline input-lg" placeholder="Username" value={this.state.username} onChange={(e) => this.handleChangedUsername(e)}/> 
                    </div> 
                    <div className="form-group"> 
                      <input type="password" className="form-control input-underline input-lg" placeholder="Password" value={this.state.password} onChange={(e) => this.handleChangedPassword(e)}/>  
                    </div> 
                    {/* <div className="form-group"> 
                      <input type="password" className="form-control input-underline input-lg" placeholder="Confirm Password" /> 
                    </div>  */}
                    <div className="form-group">
                      <input type="number" className="form-control input-underline input-lg" placeholder="Age" value={this.state.age} onChange={(e) => this.handleChangedAge(e)}/>  
                    </div>
                  </div> 
                  <CustomButton buttonText="Sign Up" onClick={this.signupRequest}/>
                </form> 
              </div> 
            </div> 
          </div>
        </div>
      </div>
    );
  }
}

export default Signup;
