import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const navigate = useNavigate();

    const handleLogin = async (e) => {
        e.preventDefault();

        // 1. Спробуємо увійти (Login)
        try {
            const response = await fetch('http://localhost:8080/auth/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            if (response.ok) {
                // Сервер повернув JSON з користувачем (включаючи ID)
                const userData = await response.json();

                // Зберігаємо в пам'ять браузера
                localStorage.setItem('user', JSON.stringify(userData));
                console.log("Збережено користувача:", userData); // Для перевірки в консолі

                alert("Вхід успішний!");
                navigate('/'); // Перехід на головну
            } else {
                // Якщо вхід не вдався, спробуємо зареєструватися (для зручності тестування)
                await tryRegister();
            }
        } catch (error) {
            console.error("Помилка:", error);
            alert("Помилка з'єднання з сервером");
        }
    };

    const tryRegister = async () => {
        if(!window.confirm("Користувача не знайдено. Створити нового?")) return;

        const response = await fetch('http://localhost:8080/auth/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ username, password, role: "USER" })
        });

        if (response.ok) {
            const userData = await response.json();
            localStorage.setItem('user', JSON.stringify(userData));
            alert("Реєстрація успішна! Ви увійшли.");
            navigate('/');
        } else {
            alert("Помилка: Можливо, таке ім'я вже зайняте.");
        }
    };

    return (
        <div className="container mt-5" style={{maxWidth: '400px'}}>
            <div className="card p-4 shadow">
                <h2 className="text-center">Вхід</h2>
                <form onSubmit={handleLogin}>
                    <div className="mb-3">
                        <label>Логін</label>
                        <input type="text" className="form-control" required
                               value={username} onChange={e => setUsername(e.target.value)} />
                    </div>
                    <div className="mb-3">
                        <label>Пароль</label>
                        <input type="password" className="form-control" required
                               value={password} onChange={e => setPassword(e.target.value)} />
                    </div>
                    <button type="submit" className="btn btn-primary w-100">Увійти / Реєстрація</button>
                </form>
            </div>
        </div>
    );
};

export default Login;