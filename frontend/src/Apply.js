import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const Apply = () => {
    const navigate = useNavigate();
    const [faculties, setFaculties] = useState([]);
    const [selectedFaculty, setSelectedFaculty] = useState('');

    // Стан для балів
    const [scores, setScores] = useState({
        math: '',
        english: '',
        physics: ''
    });

    // Завантаження списку факультетів при відкритті сторінки
    useEffect(() => {
        fetch('http://localhost:8080/faculties')
            .then(res => res.json())
            .then(data => setFaculties(data))
            .catch(err => console.error("Error loading faculties:", err));
    }, []);

    const handleSubmit = async (e) => {
        e.preventDefault();

        // Отримуємо поточного користувача з localStorage
        const userString = localStorage.getItem('user');
        if (!userString) {
            alert("Будь ласка, увійдіть у систему спочатку!");
            navigate('/login');
            return;
        }
        const user = JSON.parse(userString);

        // Формуємо правильний JSON для бекенду
        const payload = {
            userId: user.id,
            facultyId: selectedFaculty,
            mathScore: parseFloat(scores.math),
            englishScore: parseFloat(scores.english),
            physicsScore: parseFloat(scores.physics)
        };

        try {
            const response = await fetch('http://localhost:8080/applicants', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Якщо у вас є JWT токен, розкоментуйте рядок нижче:
                    // 'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(payload),
            });

            if (response.ok) {
                alert('Заявку успішно подано! Ваші бали збережено.');
                navigate('/'); // Повертаємось на головну
            } else {
                alert('Помилка при подачі заявки. Перевірте дані.');
            }
        } catch (error) {
            console.error('Помилка:', error);
            alert('Сервер не відповідає.');
        }
    };

    return (
        <div className="container mt-5" style={{ maxWidth: '600px' }}>
            <div className="card shadow p-4">
                <h2 className="text-center mb-4">Подача документів</h2>
                <form onSubmit={handleSubmit}>

                    {/* Вибір факультету */}
                    <div className="mb-3">
                        <label className="form-label fw-bold">Оберіть факультет:</label>
                        <select
                            className="form-select"
                            onChange={e => setSelectedFaculty(e.target.value)}
                            required
                        >
                            <option value="">-- Оберіть зі списку --</option>
                            {faculties.map(f => (
                                <option key={f.id} value={f.id}>
                                    {f.name} (Бюджет: {f.budgetSeats}, Контракт: {f.contractSeats})
                                </option>
                            ))}
                        </select>
                    </div>

                    <hr />

                    {/* Введення балів */}
                    <h5 className="mb-3 text-primary">Введіть бали ЗНО (100-200):</h5>

                    <div className="mb-3">
                        <label className="form-label">Математика</label>
                        <input
                            type="number" className="form-control"
                            min="100" max="200" step="0.1"
                            placeholder="Наприклад: 185.5"
                            value={scores.math}
                            onChange={e => setScores({...scores, math: e.target.value})}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Англійська мова</label>
                        <input
                            type="number" className="form-control"
                            min="100" max="200" step="0.1"
                            placeholder="Наприклад: 170"
                            value={scores.english}
                            onChange={e => setScores({...scores, english: e.target.value})}
                            required
                        />
                    </div>

                    <div className="mb-3">
                        <label className="form-label">Фізика</label>
                        <input
                            type="number" className="form-control"
                            min="100" max="200" step="0.1"
                            placeholder="Наприклад: 160"
                            value={scores.physics}
                            onChange={e => setScores({...scores, physics: e.target.value})}
                            required
                        />
                    </div>

                    <button type="submit" className="btn btn-success w-100 mt-3">
                        Подати заявку
                    </button>
                </form>
            </div>
        </div>
    );
};

export default Apply;