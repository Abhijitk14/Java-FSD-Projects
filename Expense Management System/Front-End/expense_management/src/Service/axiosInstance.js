// src/Service/axiosInstance.js

import axios from 'axios';

// Create the axios instance
const axiosInstance = axios.create({
    baseURL: 'http://localhost:8080/', // Base URL for the backend API
    timeout: 10000, // Timeout setting
    headers: {
        'Content-Type': 'application/json',
    },
});

// Add a request interceptor to include JWT token in headers only for protected routes
axiosInstance.interceptors.request.use(
    (config) => {
        const token = localStorage.getItem('token'); // Retrieve the token from localStorage
        // Skip adding Authorization header for login or register endpoints
        if (token && !config.url.includes('/authenticate') && !config.url.includes('/register')) {
            config.headers.Authorization = `Bearer ${token}`; // Add the token to the Authorization header
        }
        return config;
    },
    (error) => {
        return Promise.reject(error); // Handle errors in the request
    }
);

// Add a response interceptor to handle errors globally
axiosInstance.interceptors.response.use(
    (response) => response, // Return the response data directly if the request was successful
    (error) => {
        // Handle errors (e.g., token expiration, unauthorized access)
        if (error.response && error.response.status === 401) {
            console.log('Unauthorized, logging out...');
            localStorage.removeItem('token');
            window.location.href = '/login'; // Redirect to the login page
        }
        return Promise.reject(error);
    }
);

export default axiosInstance;
