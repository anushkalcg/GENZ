import React, { Component } from 'react';
import { Panel, FormGroup, Radio } from 'react-bootstrap'

class Question extends Component {
    constructor(props){
        super(props)
        this.state = {
            questionOneText: 'Text',
            answerOne: 'text',
            answerOneID: '',
            answerTwo: 'text',
            answerTwoID:'',
            answerThree: 'text',
            answerThreeID: '',
            priority: 0,
            selectedAnswer: 0

        }
        console.log(this.props)
        if(this.props.info){
            this.setState({questionOneText: this.props.info.text});
        }

        this.handleOptionChange =this.handleOptionChange.bind(this)
    }

    componentDidUpdate(){
        if(this.props.info){
            if(this.state.questionOneText != this.props.info.text){
                this.setState({questionOneText: this.props.info.text});
                // console.log(this.state.questionOneText)
            }
            if(this.state.answerOne != this.props.info.answers[0].text){
                this.setState({answerOne: this.props.info.answers[0].text});
                this.setState({answerOneID: (''+this.props.info.answers[0].id)})
                // console.log(this.state.answerOne)
            }
            if(this.state.answerTwo != this.props.info.answers[1].text){
                this.setState({answerTwo: this.props.info.answers[1].text});
                this.setState({answerTwoID: (''+this.props.info.answers[1].id)})
                // console.log(this.state.answerOne)
            }
            if(this.state.answerThree !== this.props.info.answers[2].text){
                this.setState({answerThree: this.props.info.answers[2].text});
                this.setState({answerThreeID: (''+this.props.info.answers[2].id)})
                // console.log(this.state.answerOne)
            }
            if(this.state.priority !== this.props.info.priority){
                this.setState({priority: this.props.info.priority});
                // console.log(this.state.answerOne)
            }
        }
    }

    handleOptionChange(changeEvent) {
        const val = changeEvent.target.value
        this.setState({
          selectedAnswer:val
        });
        this.props.onSelectAnswer(changeEvent.target.value);
      }
    
  render() {
    
    return (
      <div>
        <Panel style={{margin: "20px"}}>
            <Panel.Heading>Question {this.state.priority}</Panel.Heading>
            <Panel.Body>
                <h3>{this.state.questionOneText}</h3>
                {/* <FormGroup>
                    <Radio name="radioGroup" inline >
                        {this.state.answerOne}
                    </Radio>{' '}
                    <Radio name="radioGroup" inline>
                    {this.state.answerTwo}
                    </Radio>{' '}
                    <Radio name="radioGroup" inline>
                    {this.state.answerThree}
                    </Radio>
                </FormGroup>             */}
                <form>
                <label style={{margin:'5px'}}>
                    <input type="radio" value={this.state.answerOneID} 
                                    checked={this.state.selectedAnswer === this.state.answerOneID} 
                                    onChange={this.handleOptionChange}/>
                        {this.state.answerOne}
                </label>
                <label style={{margin:'5px'}}>
                    <input type="radio" value={this.state.answerTwoID} 
                                    checked={this.state.selectedAnswer === this.state.answerTwoID} 
                                    onChange={this.handleOptionChange}/>
                        {this.state.answerTwo}
                </label>
                <label style={{margin:'5px'}}>
                    <input type="radio" value={this.state.answerThreeID}
                                    checked={this.state.selectedAnswer === this.state.answerThreeID} 
                                    onChange={this.handleOptionChange}/>
                        {this.state.answerThree}
                </label>
                </form>
            </Panel.Body>
        </Panel>
      </div>
    );
  }
}

export default Question;
