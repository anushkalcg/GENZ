import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Play.css'
import Header from './Header'
import { Panel, FormGroup, Radio } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Question from './Question';



class Quiz extends Component {
  render() {
    return (
      <div>
        <Header/>
        <Question/>
        <Question/>
        <Question/>
        <Question/>
        <Question/>
        <div className="Play-buttons">
            <CustomButton buttonText="Save"/>
            <CustomButton buttonText="Submit"/>
        </div>
      </div>
    );
  }
}

export default Quiz;
