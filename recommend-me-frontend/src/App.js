import React from 'react';
import './App.css';
import { BrowserRouter, Switch, Route } from 'react-router-dom';
import ViewSubmissions from './components/ViewSubmissions';
import ViewTopics from './components/ViewTopics';
import CreateSurvey from './components/CreateSurvey';
import FillSurvey from './components/FillSurvey';
import Navigation from './components/Navigation';

function App() {
  return (
    <div className="App">
      <BrowserRouter>
      <header className="App-header">
        <Navigation></Navigation>
      </header>
      <div>
        <Switch>
          <Route path="/" component={ViewSubmissions} exact/>
          <Route path="/viewTopics" component={ViewTopics} exact />
          <Route path="/createSurvey" component={CreateSurvey} exact/>
          <Route path="/fillSurvey" component={FillSurvey} exact/>
        </Switch>
      </div>
    </BrowserRouter>
    </div>
  );
}

export default App;
