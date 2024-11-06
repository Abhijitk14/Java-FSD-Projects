import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import axiosInstance from '../../Service/axiosInstance'; // Using axiosInstance for API
import './Register.css'; // Ensure this file exists and is properly linked

const Register = () => {
    const [name, setName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [role, setRole] = useState('USER'); // Default role
    const [successMessage, setSuccessMessage] = useState(''); // State for success message
    const [errorMessage, setErrorMessage] = useState(''); // State for error message
    const navigate = useNavigate();

    const handleRegister = async (e) => {
        e.preventDefault();
        try {
            const response = await axiosInstance.post('/register', { name, email, password, role });
            console.log('Registration successful:', response.data);
            setSuccessMessage('Registration successful! Redirecting to login page...');
            setTimeout(() => {
                navigate('/login');
            }, 2000); // Redirect after 2 seconds to show success message
        } catch (error) {
            console.error('Registration error:', error);
            setErrorMessage('Registration error. Please try again.');
        }
    };

    return (
        <div className="container">
            <div className="main">
                <h2>Register</h2><hr />
                {successMessage && <p className="success-message">{successMessage}</p>}
                {errorMessage && <p className="error-message">{errorMessage}</p>}
                <form onSubmit={handleRegister} className="form">
                    <div className="formGroup">
                        <label>Name:</label>
                        <input
                            type="text"
                            value={name}
                            onChange={(e) => setName(e.target.value)}
                            required
                            className="input"
                            placeholder='Enter Name'
                        />
                    </div>
                    <div className="formGroup">
                        <label>Email:</label>
                        <input
                            type="email"
                            value={email}
                            onChange={(e) => setEmail(e.target.value)}
                            required
                            className="input"
                            placeholder='Enter Email'
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
                    <div className="formGroup">
                        <label>Role:</label>
                        <select
                            value={role}
                            onChange={(e) => setRole(e.target.value)}
                            className="input"
                        >
                            <option value="USER">USER</option>
                            <option value="ADMIN">ADMIN</option>
                        </select>
                    </div>
                    <button type="submit" className="button">Register</button>
                </form>
                <p>
                    Already have an account?{' '}
                    <Link to="/login" className="link">Login</Link>
                </p>
            </div>
        </div>
    );
};

export default Register;
