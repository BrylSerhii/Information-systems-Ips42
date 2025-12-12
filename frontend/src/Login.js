import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();
        const response = await fetch('http://localhost:8080/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
        });

        if (response.ok) {
            const data = await response.json();
            localStorage.setItem('token', data.token); // Save token
            alert('Login successful!');
            navigate('/apply'); // Go to application page
        } else {
            alert('Login failed');
        }
    };

    const handleRegister = async () => {
        await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password }),
        });
        alert('Registered! Now please log in.');
    };

    return (
        <div style={{ padding: "40px", maxWidth: "400px", margin: "auto" }}>
            <h2>🔑 Login / Register</h2>
            <input
                type="text" placeholder="Username"
                value={username} onChange={e => setUsername(e.target.value)}
                style={{ width: "100%", padding: "10px", margin: "10px 0" }}
            />
            <input
                type="password" placeholder="Password"
                value={password} onChange={e => setPassword(e.target.value)}
                style={{ width: "100%", padding: "10px", margin: "10px 0" }}
            />
            <button onClick={handleLogin} style={{ padding: "10px", marginRight: "10px" }}>Log In</button>
            <button onClick={handleRegister} style={{ padding: "10px" }}>Register New User</button>
        </div>
    );
}

export default Login;