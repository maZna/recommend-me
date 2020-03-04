import React, { Component } from 'react';
import axios from 'axios'

class ViewTopics extends Component {

    constructor()
    {
        super();
        this.state = {
            tableData: [],
            message: ''
        };
    }

    componentDidMount () {
        axios.get('http://localhost:8080/topics', {
            responseType: 'json'
        }).then(response => {
            if (response.data.length !== 0)
            {
                this.setState({
                    tableData: response.data
                });
            }
            else
            {
                this.setState({
                    message: 'No topics to display'
                });
            }
        });
    }

    render() {
        if (this.state.tableData.length === 0)
        {
            return (
                <div>
                    <h2>{this.state.message}</h2>
                </div>
            );
        }
        return(
            <div>
                <h2 className="title">All survey topics</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Topic ID</th>
                            <th>NPM</th>
                            <th>Topic Name</th>
                            <th>Question</th>
                        </tr>
                    </thead>
                    <tbody>
                        {
                            this.state.tableData.map(item => (
                                <tr key={item.id}>
                                    <td>{item.id}</td>
                                    <td>{item.npm}</td>
                                    <td>{item.topic}</td>
                                    <td>{item.question}</td>
                                </tr>
                            ))
                        }
                    </tbody>
                </table>
            </div>
        );
    }
}

export default ViewTopics;