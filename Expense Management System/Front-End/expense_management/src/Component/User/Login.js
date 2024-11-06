import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axiosInstance from '../../Service/axiosInstance'; // Assuming you're using axiosInstance for API requests

const Login = () => {
    const [username, setUsername] = useState(''); // Change from email to username
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const navigate = useNavigate(); // Hook from react-router-dom to navigate programmatically

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const response = await axiosInstance.post('/authenticate', { username, password }); // Change here to send username
            if (response.status === 200 && response.data.token) {
                localStorage.setItem('token', response.data.token); // Store the token in localStorage
                navigate('/user/dashboard'); // Redirect to Dashboard
            } else {
                setErrorMessage('Invalid username or password. Please try again.');
            }
        } catch (error) {
            console.error('Login error:', error);
            setErrorMessage('Invalid username or password. Please try again.');
        }
    };

    return (
        <div className="container">
            <div className="main">
                <h2>Login</h2><hr />
                {errorMessage && <p className="error-message">{errorMessage}</p>}
                <form onSubmit={handleLogin} className="form">
                    <div className="formGroup">
                        <label>Username:</label>
                        <input
                            type="text"
                            value={username}
                            onChange={(e) => setUsername(e.target.value)}
                            required
                            className="input"
                            placeholder='Enter Username'
                        />
                    </div>
                    <div className="formGroup">
                        <label>Password:</label>
                        <input
                            type="password"
                            value={password}
                            onChange={(e) => setPassword(e.target.value)}
                            required
                            className="input"
                            placeholder='Enter Password'
                        />
                    </div>
                    <button type="submit" className="button">Login</button>
                </form>
                <p>
                    Don't have an account?{' '}
                    <Link to="/register" className="link">Create an account</Link>
                </p>
            </div>
        </div>
    );
};

export default Login;
