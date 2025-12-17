import React, { useState, useEffect } from 'react';

const Results = () => {
    const [applicants, setApplicants] = useState([]);

    useEffect(() => {
        // Завантажуємо список усіх заявок
        fetch('http://localhost:8080/applicants')
            .then(res => res.json())
            .then(data => {
                console.log("Отримані дані:", data); // Для налагодження
                setApplicants(data);
            })
            .catch(err => console.error("Помилка завантаження:", err));
    }, []);

    // Функція для вибору кольору статусу
    const getStatusColor = (status) => {
        if (status === 'ADMITTED') return 'success'; // Зелений
        if (status === 'REJECTED') return 'danger';  // Червоний
        return 'warning'; // Жовтий (APPLIED)
    };

    const getStatusText = (status) => {
        if (status === 'ADMITTED') return 'Зараховано';
        if (status === 'REJECTED') return 'Відхилено';
        return 'На розгляді';
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4">Результати вступу</h2>

            {applicants.length === 0 ? (
                <div className="alert alert-info text-center">
                    Список абітурієнтів поки що порожній.
                </div>
            ) : (
                <div className="table-responsive">
                    <table className="table table-striped table-hover shadow-sm">
                        <thead className="table-dark">
                        <tr>
                            <th>ID</th>
                            <th>Ім'я студента</th>
                            <th>Факультет</th>
                            <th>Загальний бал</th>
                            <th>Статус</th>
                        </tr>
                        </thead>
                        <tbody>
                        {applicants.map(app => (
                            <tr key={app.id}>
                                <td>{app.id}</td>

                                {/* Безпечний вивід імені */}
                                <td>
                                    {app.user ? app.user.username : "Невідомо"}
                                </td>

                                {/* Безпечний вивід факультету */}
                                <td>
                                    {app.faculty ? app.faculty.name : "Не вказано"}
                                </td>

                                {/* 👇 ГОЛОВНЕ ВИПРАВЛЕННЯ 👇 */}
                                {/* Перевіряємо, чи існує totalScore перед викликом toFixed */}
                                <td className="fw-bold">
                                    {app.totalScore !== undefined && app.totalScore !== null
                                        ? app.totalScore.toFixed(1)
                                        : "0.0"}
                                </td>

                                <td>
                                        <span className={`badge bg-${getStatusColor(app.status)}`}>
                                            {getStatusText(app.status)}
                                        </span>
                                </td>
                            </tr>
                        ))}
                        </tbody>
                    </table>
                </div>
            )}
        </div>
    );
};

export default Results;