import React, { Component } from 'react';
import axios from 'axios'
import './Styling/viewsubmissions.css'

class ViewSubmissions extends Component {

    constructor() {
        super();
        this.state = {
            tableData: [],
            topics: [],
            selected: 0
        };
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(e) {
        this.setState({
            selected: e.target.value
        });
        axios.get('http://localhost:8080/submissions/t/' + e.target.value, {
            responseType: 'json'
        })
            .then(response => {
                this.setState({
                    tableData: response.data,
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
                    topics: response.data,
                    selected: response.data[0].id
                });
                axios.get('http://localhost:8080/submissions/t/' + this.state.selected, {
                    responseType: 'json'
                }).then(response => {
                    this.setState({
                        tableData: response.data
                    });
                })
            }
        });

    }

    render() {
        if (this.state.tableData.length === 0) {
            return (
                <div>
                <h2 className="title">No submissions to display</h2>
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
                            <th>Submit ID</th>
                            <th>Score</th>
                            <th>Feedback</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.tableData.map(item => (
                                <tr key={item.submitId}>
                                    <td>{item.submitId}</td>
                                    <td>{item.score}</td>
                                    <td>{item.feedback}</td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default ViewSubmissions;