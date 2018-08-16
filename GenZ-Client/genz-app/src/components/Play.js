import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Play.css'
import Header from './Header'
import { ListGroup, ListGroupItem, Button, Table } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import axios from 'axios'



class Play extends Component {
  constructor(props){
    super(props);
    this.state={
      userUID: props.match.params.id,
      userInformation: [],
      groups: [{name: "No groups yet"}],
      groupList: ['You have not joined a league yet'],
      groupListCheck: '',
      userInfo: [],
      change: true,
      rows: <tr><td>{"No data"}</td></tr>
    }
    this.onRequestUserData = this.onRequestUserData.bind(this)
    const url = 'http://localhost:8088/api/user/'

    axios.get(url+this.state.userUID)
      .then(response => {
        this.setState({ groups: response.data.groups });
      }).catch(function (error) {
        console.log(error);
      })

    // this.getUserInformation = this.getUserInformation.bind(this);
    // this.getUserInformation();
    // console.log(this.state.userInformation)
  }

  onRequestUserData(uid){
    const url = 'http://localhost:8088/api/user/'
    axios.get(url+uid).then(res => {
      var data = this.state.userInfo
      data.push(res.data)
      this.setState({userInfo: data})})
    console.log(this.state.userInfo)
    return(
      <tr><td>{this.state.userInfo}</td></tr>
    );
  }
  

  componentDidUpdate(){
    if(this.state.groups[0]){
    if(this.state.groupList[0]){
      // console.log(this.state.groupList)
      if (this.state.groupList[0].key !== this.state.groups[0].name){
        console.log(this.state.groups[0])
        this.setState({ groupList: this.state.groups.map((d) => <ListGroupItem key={d.name}>{d.name}</ListGroupItem>)});
        this.setState({ groupListCheck: this.state.groups.map((d) =>{d.name})});
      }
    }
    }
    // if(this.state.change){
    //   // this.setState({rows:this.onRequestUserData(1)})
    //   this.setState({change: false})
    // }
    // if(this.state.groups){
    //   if(this.state.rows.props){
    //     this.setState({rows: 'hello'})
    //     console.log(this.state.groups[0].users)
    //     this.setState({ groupList: this.state.groups[0].users.map((user) =>{
         
        
    //   })})
    //   }}    
  }

  render() {
    console.log(this.state.userInformation)
    const data =[{"name":"test1"},{"name":"test2"}];
    return (
      <div>
        <Header/>

        <div className="Your-Leagues">
           {this.state.groups[0]? 
           <div>
           <h2>League - {this.state.groups[0].name.toUpperCase()}</h2>
           <Table striped condensed hover style={{backgroundColor: "white", borderRadius:"50",  borderRadius:10, borderWidth: 0}}>
             <thead>
               <tr>
                 <th>#</th>
                 <th>Username</th>
                 <th>Score</th>
               </tr>
             </thead>
             <tbody>
               <tr>
                 <td>1</td>
                 <td>Mark</td>
                 <td>20</td>
               </tr>
               <tr>
                 <td>2</td>
                 <td>Jacob</td>
                 <td>30</td>
               </tr>
               <tr>
                 <td>3</td>
                 <td>Larry the Bird</td>
                 <td>20</td>
               </tr>
             </tbody>
           </Table> </div>
           :        <div className="Play-buttons">
           <Link to={`/create/${this.state.userUID}`}><CustomButton buttonText="Create League"/></Link>
           <Link to={`/join/${this.state.userUID}`}><CustomButton buttonText="Join League"/></Link>
       </div>
           }
           
          
            {/* <ul>{listItems}</ul> */}
            {/* <ListGroup>{this.state.groupList}</ListGroup> */}
        </div>
        <div className="Play-buttons">
            <Link to = {`/quiz/${this.state.groups}`}><Button bsStyle="danger" bsSize="large"><h2>This Week's Questions</h2></Button></Link>
        </div>
      </div>
    );
  }
}

// const mapStateToProps = (state) => {
//   return {
//   }
// }

// const mapDispatchToProps = (dispatch) => {
//   return {
//   }
// }

// export default connect(mapStateToProps, mapDispatchToProps)(Chat)

export default Play;
