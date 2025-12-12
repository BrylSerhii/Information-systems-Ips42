import React, { useEffect, useState } from 'react';

function Faculties() {
    const [faculties, setFaculties] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/faculties')
            .then(res => res.json())
            .then(data => setFaculties(data));
    }, []);

    return (
        <div style={{ padding: "40px" }}>
            <h2>📚 Available Faculties</h2>
            <p>Current budget places (Quotas) for this year.</p>

            <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(200px, 1fr))", gap: "20px", marginTop: "20px" }}>
                {faculties.map(fac => (
                    <div key={fac.id} style={{ backgroundColor: "#3498db", color: "white", padding: "20px", borderRadius: "10px" }}>
                        <h3>{fac.name}</h3>
                        <hr style={{ borderColor: "rgba(255,255,255,0.3)" }}/>
                        <p style={{ fontSize: "1.5rem", fontWeight: "bold" }}>Quota: {fac.budgetQuota}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default Faculties;