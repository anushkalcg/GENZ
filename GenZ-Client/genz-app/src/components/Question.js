import React, { Component } from 'react';
import { Panel, FormGroup, Radio } from 'react-bootstrap'

class Question extends Component {
  render() {
    return (
      <div>
        <Panel style={{margin: "20px"}}>
            <Panel.Heading>Question 1</Panel.Heading>
            <Panel.Body>
                <h3>Arsenal vs Manchester City</h3>
                <h4> Who will win </h4>
                <FormGroup>
                    <Radio name="radioGroup" inline>
                        1
                    </Radio>{' '}
                    <Radio name="radioGroup" inline>
                        2
                    </Radio>{' '}
                    <Radio name="radioGroup" inline>
                        3
                    </Radio>
                </FormGroup>            
            </Panel.Body>
        </Panel>
      </div>
    );
  }
}

export default Question;
