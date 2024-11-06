// src/services/apiService.js
const BASE_URL = 'http://localhost:8080/';  // Base URL for the API

export const loginUser = async (username, password) => {
    try {
        const response = await fetch('http://localhost:8080/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ username, password }),
        });

        if (!response.ok) {
            throw new Error('Login failed');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error logging in:', error);
        throw error;
    }
};

export const registerUser = async (name, email, password, role) => {
    try {
        const response = await fetch(`${BASE_URL}/register`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ name, email, password, role }),
        });

        if (!response.ok) {
            throw new Error('Registration failed');
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error('Error registering:', error);
        throw error;
    }
};
