import React, { Component, input, view, button, form, table } from 'react';
import logo from '../football.jpg';
import '../App.css';
import { Button } from 'reactstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
 

const columns = [{
    dataField: 'rank',
    text: 'Product ID'
  }, {
    dataField: 'username',
    text: 'Product Name'
  }, {
    dataField: 'score',
    text: 'Product Price'
  }];
const products = [{
    rank: '1',
    username: "user1",
    score: 9
},{
    rank: '2',
    username: "user2",
    score: 8
},{
    rank: '3',
    username: "user3",
    score: 7
},{
    rank: '4',
    username: "user4",
    score: 6
}]
class LeagueTable extends Component {
  
  render() {
    return (
        <div>
            <div class="col-md-8 offset-md-2">
            <span class="anchor" id="formUserEdit"></span>
            <hr class="my-5"/>
            <div class="card card-outline-secondary">
                <div class="card-header">
                    <h3 class="mb-0">Global Rankings</h3>
                </div>
            <div class="card-body">
                <BootstrapTable keyField='id' data={ products } columns={ columns } />
            </div>
            </div>
            </div>
        </div>
    );
  }
}

export default LeagueTable;
