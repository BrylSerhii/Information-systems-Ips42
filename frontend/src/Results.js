import React, { useEffect, useState } from 'react';

// 1. RENAME function from 'App' to 'Results'
function Results() {
    const [applicants, setApplicants] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/applicants')
            .then(response => response.json())
            .then(data => setApplicants(data))
            .catch(error => console.error('Error fetching data:', error));
    }, []);

    const getStatusStyle = (status) => {
        if (status === 'ADMITTED') return { color: 'green', fontWeight: 'bold' };
        if (status === 'REJECTED') return { color: 'red', fontWeight: 'bold' };
        return { color: 'black' };
    };

    return (
        <div style={{ padding: "50px", fontFamily: "Arial, sans-serif", textAlign: "center" }}>
            {Results}
            <h2>🏆 Admission Results</h2>
            <p>Real-time status of all registered applicants.</p>

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

// 3. EXPORT as Results
export default Results;