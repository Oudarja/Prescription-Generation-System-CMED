import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate} from "react-router-dom";
import RegistrationForm from './Components/RegistrationForm';
import LoginForm from './Components/LoginForm';
import React, { useEffect, useState } from 'react';
import Navbar from './Components/Navbar';
import PrescriptionList from './Components/PrescriptionList';
import PrescriptionForm from './Components/PrescriptionForm';
import PrescriptionPerDay from './Components/PrescriptionPerDay';
import Consume from './Components/Consume';
function App() {
  
  // check for token in local storage
  // if token is present, user is authenticated
  // if token is not present, user is not authenticated
    // const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [isAuthenticated, setIsAuthenticated] = useState(null);

  // Check token on load
  useEffect(() => {
    const token = localStorage.getItem("token");
    setIsAuthenticated(!!token);
  }, []);

if (isAuthenticated === null) return null; // wait until we know
  return (
    
     <Router>
      <div className="App">
        <header className="App-header">
          {/* Pass isAuthenticated and setIsAuthenticated to Navbar so it can react to login/logout */}
           <Navbar isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated}/> 
        </header>

        <main>
          <Routes>
            <Route path="/" element={<Navigate to="/register" />} />
            <Route path="/register" element={<div className="form-container"><RegistrationForm /></div>} />
            {/* trigger a state update that Navbar can listen to.
            Lift the authentication state up to a parent (e.g., App.js) and pass it down to Navbar. */}
            <Route path="/login" element={<div className="form-container"><LoginForm isAuthenticated={isAuthenticated} setIsAuthenticated={setIsAuthenticated}/></div>} />
            {/* Protected Route */}
            <Route path="/prescriptions" element={ isAuthenticated ? ( <PrescriptionList /> ) : ( <Navigate to="/login" /> ) } />
            <Route path="/prescription/new/:id?" element={ isAuthenticated ? ( <PrescriptionForm /> ) : ( <Navigate to="/login" /> ) } />
            <Route path="/prescription/count/day" element={ isAuthenticated ? ( <PrescriptionPerDay /> ) : ( <Navigate to="/login" /> ) } />
            <Route path="/prescription/consume" element={ isAuthenticated ? ( <Consume /> ) : ( <Navigate to="/login" /> ) } />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
