import './App.css';
import { BrowserRouter as Router, Routes, Route, Navigate, NavLink} from "react-router-dom";
import { Link } from "react-router-dom";
import RegistrationForm from './Components/RegistrationForm';
import LoginForm from './Components/LoginForm';
import React from 'react';
function App() {
  
  // check for token in local storage
  // if token is present, user is authenticated
  // if token is not present, user is not authenticated
  const isAuthenticated = !!localStorage.getItem("token");

  return (
    // <Router>
    //   <div className="App">
    //     <header className="App-header">
    //       <h1>Prescription Generation System</h1>
    //       <nav className="nav-links">
    //         <Link to="/register">Register</Link>
    //         <Link to="/login">Login</Link>
    //       </nav>
    //     </header>

    //     <main className="form-container">
    //       <Routes>
    //         <Route path="/" element={<Navigate to="/register" />} />
    //         <Route path="/register" element={<RegistrationForm />} />
    //         <Route path="/login" element={<LoginForm />} />
    //       </Routes>
    //     </main>
    //   </div>
    // </Router>
     <Router>
      <div className="App">
        <header className="App-header">
          <h1>Prescription Generation System</h1>
         <nav className="nav-links">
  <Link to="/register">Register</Link>
  <Link to="/login">Login</Link>
</nav>

        </header>

        <main className="form-container">
          <Routes>
            <Route path="/" element={<Navigate to="/register" />} />
            <Route path="/register" element={<RegistrationForm />} />
            <Route path="/login" element={<LoginForm />} />
          </Routes>
        </main>
      </div>
    </Router>
  );
}

export default App;
