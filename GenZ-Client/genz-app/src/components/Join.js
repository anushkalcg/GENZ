import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Login.css'
import Header from './Header'
import {Link} from 'react-router-dom'
import axios from 'axios'


class Join extends Component {

  constructor(props){
    super(props);
    this.state={
      groupName: '',
      userInformation: [],
      userUID: props.match.params.id
    }
    this.createLeagueRequest = this.createLeagueRequest.bind(this);
    this.handleChangedGroupName = this.handleChangedGroupName.bind(this);
  }



  createLeagueRequest() {
    const url = 'http://localhost:8088/api/group'
    console.log('hello')
    axios.get(
      url+'?group_name='+this.state.groupName)
          .then(
            response =>
            // console.log(response.data[0].id)
            axios.put(url+'/'+response.data[0].id+"/users/"+this.state.userUID)
          ).then(this.props.history.push("/play/"+this.state.userUID))
          .catch(function(error){
            console.log(error)
          })
          

  }

  handleChangedGroupName(event){
    this.setState({groupName: event.target.value})
  }
  render() {
    return (
      <div>
        <Header/>
      <div className="Login">
        <div className="login">
          <div className="row"> 
            <div className="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">
              <h1>Join Your Friends</h1>
              <form role="form" className="ng-pristine ng-valid"> 
                <div className="form-content">
                  <div className="form-group"> 
                    <input type="text" className="form-control input-underline input-lg" placeholder="League Code" value={this.state.groupName} onChange={(e) => this.handleChangedGroupName(e)}/>  
                  </div> 
                </div> 
                <CustomButton buttonText="Join" onClick={this.createLeagueRequest}/>
              </form> 
            </div> 
          </div> 
        </div>
      </div>
      </div>
    );
  }
}

export default Join;
