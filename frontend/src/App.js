import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';

import Navbar from './Navbar';
import Home from './Home';
import Faculties from './Faculties';
import Results from './Results';
import Login from './Login';
import Apply from './Apply';

function App() {
    return (
        <Router>
            <Navbar />
            <div className="content">
                <Routes>
                    <Route path="/" element={<Home />} />
                    <Route path="/faculties" element={<Faculties />} />
                    <Route path="/results" element={<Results />} />
                    <Route path="/login" element={<Login />} />
                    <Route path="/apply" element={<Apply />} />
                </Routes>
            </div>
        </Router>
    );
}

export default App;