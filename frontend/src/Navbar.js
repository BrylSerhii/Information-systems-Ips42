import React from 'react';
import { Link } from 'react-router-dom';

function Navbar() {
    return (
        <nav style={{ backgroundColor: "#2c3e50", padding: "15px", display: "flex", gap: "20px" }}>
            <span style={{ color: "white", fontWeight: "bold", marginRight: "auto" }}>🎓 AC System</span>
            <Link to="/" style={linkStyle}>Home</Link>
            <Link to="/faculties" style={linkStyle}>Faculties</Link>
            <Link to="/results" style={linkStyle}>Results</Link>
            <Link to="/login" style={linkStyle}>Login</Link>
            <Link to="/apply" style={linkStyle}>Apply</Link>
        </nav>
    );
}

const linkStyle = { color: "white", textDecoration: "none", fontSize: "1.1rem" };

export default Navbar;