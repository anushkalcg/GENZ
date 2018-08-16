import React, { Component } from 'react';
import CustomButton from './CustomButton';
import './Play.css'
import Header from './Header'
import { Panel, FormGroup, Radio } from 'react-bootstrap'
import { Link } from 'react-router-dom'
import Question from './Question';
import axios from 'axios'



class Quiz extends Component {
  constructor(props){
    super(props)
    this.state = {
      questions : '',
      questionOne : '',
      questionTwo : '',
      questionThree : '',
      questionFour : '',
      questionFive : '',
      answerOne: '',
      answerTwo: '',
      answerThree: '',
      answerFour: '',
      answerFive: ''
      

    }
    this.onChangeAnswerOne = this.onChangeAnswerOne.bind(this);
    this.onChangeAnswerTwo = this.onChangeAnswerTwo.bind(this);
    this.onChangeAnswerThree = this.onChangeAnswerThree.bind(this);
    this.onChangeAnswerFour = this.onChangeAnswerFour.bind(this);
    this.onChangeAnswerFive = this.onChangeAnswerFive.bind(this);
    this.onSaveAnswers = this.onSaveAnswers.bind(this)
    const url = 'http://localhost:8088/api/group/1';
    axios.get(url).then(res=>(this.setState({questions : res.data.questions})))

  }

  componentDidUpdate(){
    if(this.state.questions != ''){
        if(this.state.questionOne != this.state.questions[0]){
            this.setState({questionOne: this.state.questions[0]});
            // console.log(this.state.questionOneText)
        }
        if(this.state.questionTwo != this.state.questions[3]){
          this.setState({questionTwo: this.state.questions[3]});
          // console.log(this.state.questionOneText)
        }
        if(this.state.questionThree != this.state.questions[6]){
          this.setState({questionThree: this.state.questions[6]});
          // console.log(this.state.questionOneText)
        }
        // // if(this.state.questionFour != this.state.questions[9]){
        //   this.setState({questionTwo: this.state.questions[9]});
        //   // console.log(this.state.questionOneText)
        // }
        // if(this.state.questionFive != this.state.questions[12]){
        //   this.setState({questionTwo: this.state.questions[12]});
        //   // console.log(this.state.questionOneText)
        // }

    }
}

      onChangeAnswerOne(ans){
        console.log(ans)
        this.setState({answerOne: ans})
      }

      onChangeAnswerTwo(ans){
        console.log(ans)
        this.setState({answerTwo: ans})
      }
      onChangeAnswerThree(ans){
        console.log(ans)
        this.setState({answerThree: ans})
      }
      onChangeAnswerFour(ans){
        console.log(ans)
        this.setState({answerFour: ans})
      }
      onChangeAnswerFive(ans){
        console.log(ans)
        this.setState({answerFive: ans})
      }
      
      onSaveAnswers(){
        const url = 'http://localhost:8088/api/game/play'
        const data = {
          answerIds: [
            this.state.answerOne
          ],
          groupId: 1,
          questionIds: [
            1
          ],
          userId: 1,
          weekNumber: 1
        }
        axios.post(url,data).then(res=> console.log(res.data)).catch(function(error){
          console.log(error)
        })
      }
  render() {
    return (
      <div>
        <Header/>
        <Question info={this.state.questionOne} onSelectAnswer={this.onChangeAnswerOne}/>
        <Question info={this.state.questionTwo} onSelectAnswer={this.onChangeAnswerTwo}/>
        <Question info={this.state.questionThree} onSelectAnswer={this.onChangeAnswerThree}/>
        <Question info={this.state.questionFour} onSelectAnswer={this.onChangeAnswerFour}/>
        <Question info={this.state.questionFive} onSelectAnswer={this.onChangeAnswerFive}/>
        <div className="Play-buttons">
            <CustomButton buttonText="Save"/>
            <CustomButton buttonText="Submit" onClick={this.onSaveAnswers}/>
        </div>
      </div>
    );
  }
}

export default Quiz;
