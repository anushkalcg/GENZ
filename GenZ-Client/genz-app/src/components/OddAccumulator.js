import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Login.css'
import {Table} from 'react-bootstrap'
import Header from './Header'
import {Link} from 'react-router-dom'


class OddAccumulator extends Component {
  render() {
    return (
        <div>
            <Header/>
            <div style={{margin:'15px'}}>
            <Table striped bordered responsive>
                <thead>
                    <tr>
                    <th>Rank</th>
                    <th>Question</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    <td>1</td>
                    <td>Mark</td>
                    </tr>
                    <tr>
                    <td>2</td>
                    <td>Jacob</td>
                    </tr>
                    <tr>
                    <td>3</td>
                    <td>@twitter</td>
                    </tr>
                    <tr>
                    <td>4</td>
                    <td>@twitter</td>
                    </tr>
                    <tr>
                    <td>5</td>
                    <td>@twitter</td>
                    </tr>
                </tbody>
            </Table>
            </div>
        </div>
    );
  }
}

export default OddAccumulator;
