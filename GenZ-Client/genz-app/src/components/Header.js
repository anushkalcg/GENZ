import React, { Component } from 'react';
import lcg from './lcg.png'
import './Header.css'
import {Navbar} from 'react-bootstrap';
import { Link } from 'react-router-dom';



export default class Header extends Component {
  render() {
    return (
        <Link to='/'>
          <Navbar inverse className="Header">
            <Navbar.Header>
                <Navbar.Brand style={{display:"flex", flexDirection:"row", justifyContent: 'center', alignItems: 'center', marginTop: 15}}>
                <img src={lcg} className="Header-logo"/>
                
                </Navbar.Brand>
            </Navbar.Header>
          </Navbar>
        </Link>
    );
  }
}

