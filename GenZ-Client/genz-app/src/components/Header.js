import React, { Component } from 'react';
import logo from './logo.jpg'
import lad from './ladbroke.jpg'
import './Header.css'
import {Navbar} from 'react-bootstrap';
import { Link } from 'react-router-dom';



export default class Header extends Component {
  render() {
    return (
      <div style={{marginTop: "0.7cm"}}>
        <Link to='/'>
          <div style={{width:"100%", height: "1.25cm", backgroundColor: "#F01E28", paddingTop: "0.125cm", paddingLeft: "90%", paddingRight:"25px"}}>
          <img src={lad} style={{height:"1cm"}}/>          
          </div>
          <img src={logo} className="Header-logo"/>

          {/* <Navbar inverse className="Header">
            <Navbar.Header>
                <Navbar.Brand style={{display:"flex", flexDirection:"row", justifyContent: 'center', alignItems: 'center', marginTop: 15}}>
                <img src={lcg} className="Header-logo"/>
                
                </Navbar.Brand>
            </Navbar.Header>
          </Navbar> */}
        </Link>
      </div>
    );
  }
}

