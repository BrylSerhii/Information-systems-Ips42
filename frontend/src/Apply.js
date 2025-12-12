import React, { useState } from 'react';
import Modal from './Modal'; // Import the new component

function Apply() {
    const [fullName, setFullName] = useState('');
    const [facultyId, setFacultyId] = useState('1');

    // Modal State
    const [showModal, setShowModal] = useState(false);
    const [modalContent, setModalContent] = useState({ title: '', message: '', isSuccess: true });

    const handleSubmit = async (e) => {
        e.preventDefault();
        const token = localStorage.getItem('token');

        if (!token) {
            setModalContent({
                title: 'Access Denied',
                message: 'You must log in before applying!',
                isSuccess: false
            });
            setShowModal(true);
            return;
        }

        try {
            const response = await fetch('http://localhost:8080/applicants/apply', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                },
                body: JSON.stringify({ fullName, facultyId }),
            });

            if (response.ok) {
                // Success! Show the Pop-up
                setModalContent({
                    title: 'Application Received!',
                    message: `Success! ${fullName} has been registered for the entrance exams.`,
                    isSuccess: true
                });
                setShowModal(true);
                setFullName(''); // Clear form
            } else {
                throw new Error('Server rejected request');
            }
        } catch (error) {
            setModalContent({
                title: 'Error',
                message: 'Something went wrong. Please try again.',
                isSuccess: false
            });
            setShowModal(true);
        }
    };

    const closeModal = () => {
        setShowModal(false);
        if (modalContent.isSuccess) {
            window.location.href = '/results'; // Redirect only on success
        }
    };

    return (
        <div style={{ padding: "40px", maxWidth: "500px", margin: "0 auto" }}>
            {/* The Pop-up Component */}
            <Modal
                show={showModal}
                title={modalContent.title}
                message={modalContent.message}
                isSuccess={modalContent.isSuccess}
                onClose={closeModal}
            />

            <h2 style={{ textAlign: "center", marginBottom: "30px" }}>📝 Apply for Admission</h2>

            <form onSubmit={handleSubmit} style={formStyle}>
                <div style={{ marginBottom: "20px" }}>
                    <label style={labelStyle}>Full Name</label>
                    <input
                        type="text"
                        value={fullName}
                        onChange={e => setFullName(e.target.value)}
                        required
                        style={inputStyle}
                        placeholder="e.g. John Smith"
                    />
                </div>
                <div style={{ marginBottom: "20px" }}>
                    <label style={labelStyle}>Select Faculty</label>
                    <select
                        value={facultyId}
                        onChange={e => setFacultyId(e.target.value)}
                        style={inputStyle}
                    >
                        <option value="1">Computer Science</option>
                        <option value="2">Law</option>
                    </select>
                </div>
                <button type="submit" style={submitButtonStyle}>Submit Application</button>
            </form>
        </div>
    );
}

// Styles for the Form
const formStyle = {
    backgroundColor: "#f9f9f9",
    padding: "30px",
    borderRadius: "10px",
    boxShadow: "0 2px 10px rgba(0,0,0,0.1)"
};

const labelStyle = {
    display: "block",
    marginBottom: "8px",
    fontWeight: "bold",
    color: "#34495e"
};

const inputStyle = {
    width: "100%",
    padding: "12px",
    borderRadius: "6px",
    border: "1px solid #ddd",
    fontSize: "1rem",
    boxSizing: "border-box" // Prevents padding from breaking layout
};

const submitButtonStyle = {
    width: "100%",
    padding: "12px",
    backgroundColor: "#27ae60",
    color: "white",
    border: "none",
    borderRadius: "6px",
    fontSize: "1.1rem",
    fontWeight: "bold",
    cursor: "pointer",
    marginTop: "10px"
};

export default Apply;