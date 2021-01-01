import React from 'react';
import './Styling/navigation.css'
import { NavLink } from 'react-router-dom';
 
const Navigation = () => {
    return (    
       <div>
        <ul>
          <li><NavLink to="/"><button className="btn btn-light">View Submissions</button></NavLink></li>
          <li><NavLink to="/viewTopics"><button className="btn btn-light">View Topics</button></NavLink></li>
          <li><NavLink to="/createSurvey"><button className="btn btn-light">Create Survey</button></NavLink></li>
          <li><NavLink to="/fillSurvey"><button className="btn btn-light">Fill Survey</button></NavLink></li>
          </ul>
       </div>
    );
}
 
export default Navigation;