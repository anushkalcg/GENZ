import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Login.css'
import Header from './Header'
import {Link} from 'react-router-dom'


class CreateLeague extends Component {
  render() {
    return (
      <div>
        <Header/>
      <div className="Login">
        <div className="login">
          <div className="row"> 
            <div className="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">
              <h1>Play with Friends</h1>
              <form role="form" className="ng-pristine ng-valid"> 
                <div className="form-content"> 
                  <div className="form-group"> 
                    <input type="text" className="form-control input-underline input-lg" placeholder="League Name" /> 
                  </div> 
                  <div className="form-group"> 
                    <input type="text" className="form-control input-underline input-lg" placeholder="Unique League Code" /> 
                  </div> 
                </div> 
                <Link to="/play"><CustomButton buttonText="Create League"/></Link>
              </form> 
            </div> 
          </div> 
        </div>
      </div>
      </div>
    );
  }
}

export default CreateLeague;