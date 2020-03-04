import React from 'react';
import './Styling/navigation.css'
import { NavLink } from 'react-router-dom';
 
const Navigation = () => {
    return (    
       <div>
        <ul>
          <li><button className="btn btn-light"><NavLink to="/">View Submissions</NavLink></button></li>
          <li><button className="btn btn-light"><NavLink to="/viewTopics">View Topics</NavLink></button></li>
          <li><button className="btn btn-light"><NavLink to="/createSurvey">Create Survey</NavLink></button></li>
          <li><button className="btn btn-light"><NavLink to="/fillSurvey">Fill Survey</NavLink></button></li>
          </ul>
       </div>
    );
}
 
export default Navigation;