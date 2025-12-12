import React from 'react';

function Home() {
    return (
        <div style={{ padding: "40px", textAlign: "center" }}>
            <h1 style={{ fontSize: "3rem", color: "#2c3e50" }}>🏫 Admissions Committee System</h1>
            <p style={{ fontSize: "1.2rem", color: "#7f8c8d" }}>
                Automated Applicant Registration & Ranking System
            </p>

            <div style={{ display: "flex", justifyContent: "center", gap: "20px", marginTop: "40px" }}>
                <div style={cardStyle}>
                    <h3>🚀 Automated Ranking</h3>
                    <p>Algorithms automatically calculate scores and rank students.</p>
                </div>
                <div style={cardStyle}>
                    <h3>📊 Transparent Results</h3>
                    <p>Real-time admission status tracking for all applicants.</p>
                </div>
                <div style={cardStyle}>
                    <h3>🔒 Secure Data</h3>
                    <p>Reliable PostgreSQL storage with Spring Boot security.</p>
                </div>
            </div>
        </div>
    );
}

const cardStyle = {
    border: "1px solid #ddd",
    borderRadius: "8px",
    padding: "20px",
    width: "250px",
    boxShadow: "0 4px 8px rgba(0,0,0,0.1)"
};

export default Home;