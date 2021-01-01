import React, { Component } from 'react';
import axios from 'axios'
import './Styling/viewsubmissions.css'
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';

class ViewSubmissions extends Component {

    constructor() {
        super();
        this.state = {
            tableData: [],
            topics: [],
            selected: 0,
            message: '',
            //extra here
            isUpdate: false,
            updatedItemId: 0
        };
        this.handleChange = this.handleChange.bind(this);
        this.loadData = this.loadData.bind(this);
        this.toggleState = this.toggleState.bind(this);
    }

    handleChange(e) {
        this.setState({
            selected: e.target.value
        });
        axios.get('http://localhost:8080/submissions/t/' + e.target.value, {
            responseType: 'json'
        })
            .then(response => {
                if (response.data.length !== 0) {
                    this.setState({
                        tableData: response.data
                    })
                }
                else {
                    this.setState({
                        tableData: [],
                        message: 'No submissions to display'
                    })
                }
            });

    }

    componentDidMount() {
        this.loadData();
    }

    loadData() {
        axios.get('http://localhost:8080/topics', {
            responseType: 'json'
        }).then(response => {
            console.log(response);
            if (response.data.length !== 0) {
                this.setState({
                    topics: response.data,
                    selected: response.data[0].id
                });
                axios.get('http://localhost:8080/submissions/t/' + this.state.selected, {
                    responseType: 'json'
                }).then(response => {
                    console.log(response);
                    if (response.data.length !== 0) {
                        this.setState({
                            tableData: response.data
                        });
                    }
                    else {
                        this.setState({
                            message: 'No submissions to display'
                        });
                    }
                })
            }
            else {
                this.setState({
                    message: 'No submissions to display'
                });
            }
        });
    }

    toggleState(e) {
        if (this.state.isUpdate) {
            this.setState({
                isUpdate: false,
                updatedItemId: e.target.value
            });
        } else {
            this.setState({
                isUpdate: true,
                updatedItemId: e.target.value
            });
        }
    }

    render() {
        if (this.state.isUpdate) {
            return (
                <UpdateForm toggle={this.toggleState} updateURL={'http://localhost:8080/submissions/' + this.state.updatedItemId} updateTopic={this.state.selected}></UpdateForm>
            );
        }
        if (this.state.tableData.length === 0) {
            return (
                <div>
                    <h2 className="title">Submissions for</h2>
                    <select
                        className="form-control"
                        value={this.state.topics[this.state.selected]}
                        onChange={this.handleChange}
                    >
                        {this.state.topics.map((topic) => <option key={topic.id} value={topic.id}>{topic.topic}</option>)}
                    </select>
                    <h3>{this.state.message}</h3>
                </div>
            );
        }
        return (
            <div>
                <h2 className="title">Submissions for </h2>
                <select
                    className="form-control"
                    value={this.state.topics[this.state.selected]}
                    onChange={this.handleChange}
                >
                    {this.state.topics.map((topic) => <option key={topic.id} value={topic.id}>{topic.topic}</option>)}
                </select>
                <table>
                    <thead>
                        <tr>
                            <th className="sub_h_id">Submit ID</th>
                            <th className="sub_h_score">Score</th>
                            <th className="sub_h_fb">Feedback</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.tableData.map(item => (
                                <tr key={item.submitId}>
                                    <td className="sub_id">{item.submitId}</td>
                                    <td className="sub_score">{item.score}</td>
                                    <td className="sub_fb">{item.feedback}</td>
                                    <td className="sub_actions">
                                        <DeleteButton
                                            actionName={'Delete'}
                                            actionUrl={'http://localhost:8080/submissions/' + item.submitId}
                                            loadData={this.loadData}
                                        ></DeleteButton>
                                        <span></span>
                                        <button className="btn btn-success" value={item.submitId} onClick={this.toggleState}>Update</button>
                                    </td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}

class DeleteButton extends Component {

    constructor() {
        super();
        this.deleteItem = this.deleteItem.bind(this);
    }

    deleteItem() {
        axios.delete(this.props.actionUrl);
        this.props.loadData();
        window.location.reload();
    }

    render() {
        return (
            <div>
                <button className="btn btn-success" onClick={this.deleteItem}>{this.props.actionName}</button>
            </div>
        );
    }
}

class UpdateForm extends Component {

    constructor() {
        super();
        this.state = {
            loaded: false,
            submission: undefined,
            updateQuestion: '',
            updateScore: 0,
            updateFeedback: ''
        }
        this.updateCurrentScore = this.updateCurrentScore.bind(this);
        this.handleFeedbackChange = this.handleFeedbackChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    componentDidMount () {
        axios.get(this.props.updateURL, {
            responseType: 'json'
        }).then(response => {
            console.log(response);
            this.setState({
                loaded: true,
                submission: response.data,
                updateQuestion: response.data.topic.question,
                updateScore: String(response.data.score),
                updateFeedback: response.data.feedback
            });
        });
    }


    updateCurrentScore(e) {
        var val = e.target.value;
        this.setState({
            updateScore: val
        }, () => {
            console.log(this.state.updateScore);
            this.setState(this.state);
        });
    }

    handleFeedbackChange(e)
    {
        this.setState({
            updateFeedback: e.target.value
        });
    }

    handleSubmit(e)
    {
        e.preventDefault();
        fetch(this.props.updateURL, {
            method: 'put',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({
                "score": this.state.updateScore,
                "feedback": this.state.updateFeedback,
                "topic": {
                    "id": this.props.updateTopic
                }
            })
        });
    }

    render() {
        if (!this.state.loaded) {
            return (
                <div className="spinner-border" id="spinner" role="status">
                </div>
            );
        } else {
            return (
                <div>
                    <form onSubmit={this.handleSubmit}>
                        <h2 className="title">Update Survey for </h2>
                        <br></br>
                        <h3>{this.state.updateQuestion}</h3>
                        <FormControl component="fieldset">
                            <FormLabel component="legend">Choose a score</FormLabel>
                            <RadioGroup aria-label="position" name="position" row>
                                <FormControlLabel
                                    value="0"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '0'}
                                    control={<Radio color="primary" />}
                                    label="0"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="1"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '1'}
                                    control={<Radio color="primary" />}
                                    label="1"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="2"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '2'}
                                    control={<Radio color="primary" />}
                                    label="2"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="3"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '3'}
                                    control={<Radio color="primary" />}
                                    label="3"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="4"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '4'}
                                    control={<Radio color="primary" />}
                                    label="4"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="5"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '5'}
                                    control={<Radio color="primary" />}
                                    label="5"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="6"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '6'}
                                    control={<Radio color="primary" />}
                                    label="6"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="7"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '7'}
                                    control={<Radio color="primary" />}
                                    label="7"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="8"
                                    checked={this.state.updateScore === '8'}
                                    onClick={this.updateCurrentScore}
                                    control={<Radio color="primary" />}
                                    label="8"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="9"
                                    checked={this.state.updateScore === '9'}
                                    onClick={this.updateCurrentScore}
                                    control={<Radio color="primary" />}
                                    label="9"
                                    labelPlacement="bottom"
                                />
                                <FormControlLabel
                                    value="10"
                                    onClick={this.updateCurrentScore}
                                    checked={this.state.updateScore === '10'}
                                    control={<Radio color="primary" />}
                                    label="10"
                                    labelPlacement="bottom"
                                />
                            </RadioGroup>
                        </FormControl>
                        <div className="    form-group">
                            <label><strong>Enter your new feedback: </strong></label>
                            <br></br>
                            <textarea
                                className="form-control"
                                value={this.state.updateFeedback}
                                align="left"
                                rows="3"
                                onChange={this.handleFeedbackChange}>
                            </textarea>
                            <div className="form-group">
                                <button onClick={this.props.toggle} className="btn btn-primary">Cancel</button>
                            </div>
                            <div className="form-group">
                                <button type="submit" className="btn btn-primary">Submit Survey</button>
                            </div>
                        </div>
                    </form>
                </div>
            );
        }
    }
}

export default ViewSubmissions;