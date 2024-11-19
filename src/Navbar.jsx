import React from 'react';
import './Navbar.css';

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-left">
        {/* Placeholder for logo */}
        <span className="navbar-logo">Logo</span>
      </div>
      <div className="navbar-center">
        <h1>Plagiarism Detector</h1>
      </div>
      <div className="navbar-right">
        <a href="/login" className="navbar-link">Login</a>
        <a href="/register" className="navbar-link">Register</a>
      </div>
    </nav>
  );
};

export default Navbar;






