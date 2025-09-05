import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import "../App.css"; // optional if you want separate styles

const Navbar = ({ isAuthenticated, setIsAuthenticated }) => {

  const navigate = useNavigate();

// Check login status on load

  // Handle logout
  const handleLogout = () => {
    localStorage.removeItem("token");
    setIsAuthenticated(false);
    navigate("/login"); // redirect to login page
  };

  return (
    <nav className="nav-links">
      {!isAuthenticated ? (
        <>
<div className="nav-unauthenticated">
  <div className="nav-content">
    <div className="nav-left">
      <h1>Prescription Generation System</h1>
    </div>
  </div>
</div>
        </>
      ) : (
        <>
       <div className="nav-authenticated">
  <div className="links-left">
    <Link to="/prescriptions">Home</Link>
    <Link to="/prescription/new">Add-Prescription</Link>
  </div>
  <button onClick={handleLogout} className="logout-btn">Logout</button>
</div>


        </>
      )}
    </nav>
  );
};

export default Navbar;
