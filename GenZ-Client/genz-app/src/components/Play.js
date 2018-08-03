import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Play.css'
import Header from './Header'
import { ListGroup, ListGroupItem, Button } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import axios from 'axios'



class Play extends Component {
  constructor(props){
    super(props);
    this.state={
      userUID: props.match.params.id,
      userInformation: [],
      groups: []
    }

    // this.getUserInformation = this.getUserInformation.bind(this);
    // this.getUserInformation();
    // console.log(this.state.userInformation)
  }

  componentDidMount(){
    const url = 'http://localhost:8088/api/user/'

    axios.get(url+this.state.userUID)
      .then(response => {
        this.setState({ userInformation: response.data });
      }).catch(function (error) {
        console.log(error);
      })

  }

  

  render() {
    console.log(this.state.userInformation)
    const data =[{"name":"test1"},{"name":"test2"}];
    const listItems = data.map((d) => <ListGroupItem key={d.name}>{d.name}</ListGroupItem>);
    var groupList
    if(this.state.userInformation){
      if(this.state.userInformation.groups){
        groupList = this.state.userInformation.groups.map((group) => {
          group.name
          // <ListGroupItem key={group.id}>{group.name}</ListGroupItem>
          // <li key={index}>{group.id}</li>
        })
      }
    // this.state.userInformation.groups.map((group, index) => {
      console.log(groupList)
    // })
  }
    return (
      <div>
        <Header/>
        <div className="Play-buttons">
            <Link to={`/create/${this.state.userUID}`}><CustomButton buttonText="Create League"/></Link>
            <Link to={`/join/${this.state.userUID}`}><CustomButton buttonText="Join League"/></Link>
        </div>
        <div className="Your-Leagues">
            <h2>Your Leagues</h2>
            <ul>{groupList}</ul>
            <ListGroup>
                {groupList}
            </ListGroup>
        </div>
        <div className="Play-buttons">
            <Link to = "/quiz"><Button bsStyle="danger" bsSize="large"><h2>This Week's Questions</h2></Button></Link>
        </div>
      </div>
    );
  }
}

export default Play;
