import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Play.css'
import Header from './Header'
import { ListGroup, ListGroupItem, Button } from 'react-bootstrap'
import { Link } from 'react-router-dom'



class Play extends Component {
  render() {
    return (
      <div>
        <Header/>
        <div className="Play-buttons">
            <Link to='/create'><CustomButton buttonText="Create League"/></Link>
            <Link to='/join'><CustomButton buttonText="Join League"/></Link>
        </div>
        <div className="Your-Leagues">
            <h2>Your Leagues</h2>
            <ListGroup>
                <ListGroupItem>Item 1</ListGroupItem>
                <ListGroupItem>Item 2</ListGroupItem>
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
