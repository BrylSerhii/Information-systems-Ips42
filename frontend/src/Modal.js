import React from 'react';

function Modal({ show, title, message, onClose, isSuccess }) {
    if (!show) return null;

    return (
        <div style={overlayStyle}>
            <div style={modalStyle}>
                <div style={{ marginBottom: "15px", fontSize: "3rem" }}>
                    {isSuccess ? "🎉" : "⚠️"}
                </div>
                <h2 style={{ color: isSuccess ? "#27ae60" : "#c0392b", margin: "0 0 10px 0" }}>
                    {title}
                </h2>
                <p style={{ fontSize: "1.1rem", color: "#555" }}>{message}</p>
                <button onClick={onClose} style={buttonStyle}>
                    OK
                </button>
            </div>
        </div>
    );
}

// CSS Styles
const overlayStyle = {
    position: 'fixed',
    top: 0,
    left: 0,
    right: 0,
    bottom: 0,
    backgroundColor: 'rgba(0, 0, 0, 0.6)', // Semi-transparent dark background
    backdropFilter: 'blur(3px)', // Blur effect
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
    zIndex: 1000
};

const modalStyle = {
    backgroundColor: 'white',
    padding: '30px',
    borderRadius: '12px',
    boxShadow: '0 5px 15px rgba(0,0,0,0.3)',
    textAlign: 'center',
    maxWidth: '400px',
    width: '90%',
    animation: 'fadeIn 0.3s ease-out'
};

const buttonStyle = {
    marginTop: '20px',
    padding: '10px 25px',
    backgroundColor: '#2c3e50',
    color: 'white',
    border: 'none',
    borderRadius: '5px',
    fontSize: '1rem',
    cursor: 'pointer',
    transition: 'background 0.2s'
};

export default Modal;