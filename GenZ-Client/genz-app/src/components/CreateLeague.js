import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Login.css'
import Header from './Header'
import {Link} from 'react-router-dom'
import axios from 'axios';


class CreateLeague extends Component {
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

  componentDidMount(){
    const url = 'http://localhost:8088/api/user/'

    axios.get(url+this.state.userUID)
      .then(response => {
        this.setState({ userInformation: response.data });
      })
      .catch(function (error) {
        console.log(error);
      })  
  }

  createLeagueRequest() {
    const url = 'http://localhost:8088/api/group'
    axios.post(url,{"name": this.state.groupName,})
          .then(
            response => axios.put(url+"/"+response.data.id+"/users/"+this.state.userUID)
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
              <h1>Play with Friends</h1>
              <form role="form" className="ng-pristine ng-valid"> 
                <div className="form-content"> 
                  <div className="form-group"> 
                    <input type="text" className="form-control input-underline input-lg" placeholder="Unique League Name" value={this.state.groupName} onChange={(e) => this.handleChangedGroupName(e)}/> 
                  </div> 
                </div> 
                <CustomButton buttonText="Create League" onClick={this.createLeagueRequest}/>
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
