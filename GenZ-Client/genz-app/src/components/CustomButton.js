import React, { Component } from 'react';
import {Button} from 'react-bootstrap';
import '../CustomButton.css'

class App extends Component {
  render() {
    const {
      buttonText
    } = this.props
    return (
      <div className="App"> 
          <Button className="Button" bsStyle="success" onClick={this.props.onClick}>{buttonText}</Button>
      </div>
    );
  }
}

export default App;
