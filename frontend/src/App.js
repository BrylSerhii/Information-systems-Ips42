import React, { useEffect, useState } from 'react';

function App() {
    const [applicants, setApplicants] = useState([]);

    useEffect(() => {
        // Fetch data from the Java Backend
        fetch('http://localhost:8080/applicants')
            .then(response => response.json())
            .then(data => setApplicants(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    // Helper function to color the text based on status
    const getStatusStyle = (status) => {
        if (status === 'ADMITTED') return { color: 'green', fontWeight: 'bold' };
        if (status === 'REJECTED') return { color: 'red', fontWeight: 'bold' };
        return { color: 'black' };
    };

    return (
        <div style={{ padding: "50px", fontFamily: "Arial, sans-serif", textAlign: "center" }}>
            <h1>🎓 Admissions Committee Results</h1>
            <p>Real-time data from Spring Boot Backend</p>

            <table border="1" cellPadding="10" style={{ margin: "0 auto", borderCollapse: "collapse", width: "80%" }}>
                <thead style={{ backgroundColor: "#f4f4f4" }}>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Faculty</th>
                    <th>Total Score</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                {applicants.map((app) => (
                    <tr key={app.id}>
                        <td>{app.id}</td>
                        <td>{app.fullName}</td>
                        {/* Check if faculty exists before reading name to prevent errors */}
                        <td>{app.faculty ? app.faculty.name : 'Unknown'}</td>
                        <td>{app.totalScore.toFixed(1)}</td>
                        <td style={getStatusStyle(app.status)}>
                            {app.status}
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
}

export default App;