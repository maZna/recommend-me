import React, { Component } from 'react';
import axios from 'axios';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';


class FillSurvey extends Component {

    constructor() {
        super();
        this.state = {
            topics: [],
            selected: 0,
            currentQuestion: '',
            currentScore: 0,
            feedback: ''
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleRadioChange = this.handleRadioChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleFeedbackChange = this.handleFeedbackChange.bind(this);
    }

    handleChange(e) {
        console.log(e.target.value);
        this.setState({
            selected: e.target.value
        });
        axios.get('http://localhost:8080/topics/q/' + e.target.value, {
            responseType: 'text'
        }).then(response => {
            console.log(response);
            this.setState({
                currentQuestion: response.data
            });
        });
    }

    handleRadioChange(e) {
        this.setState({
            currentScore: e.target.value
        });
    }

    handleFeedbackChange(e) {
        this.setState({
            feedback: e.target.value
        });
    }

    handleSubmit(e) {
        fetch('http://localhost:8080/submissions', {
            method: 'post',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "score": this.state.currentScore,
                "feedback": this.state.feedback,
                "topic": {
                    "id": this.state.selected
                }
            })
        });
    }

    componentDidMount() {
        axios.get('http://localhost:8080/topics', {
            responseType: 'json'
        }).then(response => {
            if (response.data.length !== 0)
            {
                this.setState({
                    topics: response.data
                });
                console.log(this.state.topics[0].question);
                this.setState({
                    selected: this.state.topics[this.state.selected].id,
                    currentQuestion: this.state.topics[this.state.selected].question
                });
            }
        });
    }

    render() {
        if (this.state.topics.length === 0 )
        {
            return (
                <div>
                    <h2>
                        There are no surveys available
                    </h2>
                </div>
            );
        }
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <h2 className="title">Attempt Survey for </h2>
                    <select
                        className="form-control"
                        value={this.state.topics[this.state.selected]}
                        onChange={this.handleChange}
                    >
                        {this.state.topics.map((topic) => <option key={topic.id} value={topic.id}>{topic.topic}</option>)}
                    </select>
                    <br></br>
                    <h3>{this.state.currentQuestion}</h3>
                    <FormControl component="fieldset">
                        <FormLabel component="legend">Choose a score</FormLabel>
                        <RadioGroup aria-label="position" name="position" onChange={this.handleRadioChange} row>
                            <FormControlLabel
                                value="0"
                                control={<Radio color="primary" />}
                                label="0"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="1"
                                control={<Radio color="primary" />}
                                label="1"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="2"
                                control={<Radio color="primary" />}
                                label="2"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="3"
                                control={<Radio color="primary" />}
                                label="3"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="4"
                                control={<Radio color="primary" />}
                                label="4"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="5"
                                control={<Radio color="primary" />}
                                label="5"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="6"
                                control={<Radio color="primary" />}
                                label="6"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="7"
                                control={<Radio color="primary" />}
                                label="7"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="8"
                                control={<Radio color="primary" />}
                                label="8"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="9"
                                control={<Radio color="primary" />}
                                label="9"
                                labelPlacement="bottom"
                            />
                            <FormControlLabel
                                value="10"
                                control={<Radio color="primary" />}
                                label="10"
                                labelPlacement="bottom"
                            />
                        </RadioGroup>
                    </FormControl>
                    <div className="    form-group">
                        <label><strong>Enter your feedback: </strong></label>
                        <br></br>
                        <textarea
                            className="form-control"
                            placeholder="Say something about your score choice"
                            align="left"
                            rows="3"
                            onChange={this.handleFeedbackChange}>
                        </textarea>
                        <div className="form-group">
                            <button type="submit" className="btn btn-primary">Submit Survey</button>
                        </div>
                    </div>
                </form>
            </div>
        );
    }
}

export default FillSurvey;