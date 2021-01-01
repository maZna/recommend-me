import React, { Component } from 'react';
import './Styling/createsurvey.css'

class CreateSurvey extends Component {

    constructor() {
        super();
        this.state = {
            topic: '',
            question: ''
        };

        this.handleTopicChange = this.handleTopicChange.bind(this);
        this.handleQuestionChange = this.handleQuestionChange.bind(this);
        //this.printState = this.printState.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleTopicChange(e) {
        this.setState({
            topic: e.target.value
        });
    }

    handleQuestionChange(e) {
        this.setState({
            question: e.target.value
        });
    }

    handleSubmit(e) {
        e.preventDefault();
        fetch('http://localhost:8080/topics', {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "npm": "0",
                "topic": this.state.topic,
                "question": this.state.question
            })
        });

    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <h2>Create a new Survey</h2>
                    <br></br>
                    <div className="form-group">
                        <label><strong>Enter the survery topic: </strong></label>
                        <br></br>
                        <input
                            className="form-control"
                            placeholder="Enter a cool topic to ask a question about"
                            align="left"
                            onChange={this.handleTopicChange}>
                        </input>
                    </div>
                    <div className="form-group">
                        <label><strong>Enter a survey question: </strong></label>
                        <br></br>
                        <textarea
                            className="form-control"
                            placeholder="Ask your question here. The customer will give an answer on a scale from 1 to 10."
                            align="left"
                            rows="3"
                            onChange={this.handleQuestionChange}>
                        </textarea>
                    </div>
                    <div className="form-group">
                        <button type="submit" className="btn btn-primary">Submit Your Question</button>
                    </div>
                </form>
            </div>
        );
    }

}

export default CreateSurvey;