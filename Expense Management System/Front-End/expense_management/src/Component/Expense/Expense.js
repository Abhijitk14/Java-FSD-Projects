import React, { useState } from 'react';
import Navbar from '../../Navbar';
import './Expense.css'; // Ensure you have styles defined in this file
import { addExpense } from '../../Service/addExpenseService'; // Import your API function
import 'bootstrap/dist/css/bootstrap.min.css'; // Import Bootstrap CSS

const Expense = () => {
    const [userId, setUserId] = useState('');
    const [categoryId, setCategoryId] = useState('');
    const [amount, setAmount] = useState('');
    const [date, setDate] = useState('');
    const [description, setDescription] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState(''); // New success message state

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Prepare the data to be sent to the server
            const expenseData = {
                user: { userId },
                category: { categoryId },
                amount,
                date,
                description
            };
            
            // Call the API to add the expense
            await addExpense(expenseData);
            
            // Reset the form fields
            setUserId('');
            setCategoryId('');
            setAmount('');
            setDate('');
            setDescription('');
            setErrorMessage('');
            setSuccessMessage('Expense added successfully!'); // Set success message
        } catch (error) {
            setErrorMessage('Failed to add expense. Please try again.');
            setSuccessMessage(''); // Clear success message if there's an error
            console.error('Error adding expense:', error);
        }
    };

    return (
        <div className="expense-container">
            <Navbar />
            <div className="expense-content">
                <h1>Add Expense</h1>
                {errorMessage && (
                    <div className="alert alert-danger" role="alert">
                        {errorMessage}
                    </div>
                )}
                {successMessage && (
                    <div className="alert alert-success" role="alert">
                        {successMessage}
                    </div>
                )} {/* Success alert */}
                <form onSubmit={handleSubmit} className="expense-form">
                    <div className="form-group">
                        <label htmlFor="userId">User ID:</label>
                        <input
                            type="number"
                            id="userId"
                            value={userId}
                            onChange={(e) => setUserId(e.target.value)}
                            required
                            placeholder="Enter User ID"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="categoryId">Category ID:</label>
                        <input
                            type="number"
                            id="categoryId"
                            value={categoryId}
                            onChange={(e) => setCategoryId(e.target.value)}
                            required
                            placeholder="Enter Category ID"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="amount">Amount:</label>
                        <input
                            type="number"
                            id="amount"
                            value={amount}
                            onChange={(e) => setAmount(e.target.value)}
                            required
                            placeholder="Enter Amount"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="date">Date:</label>
                        <input
                            type="date"
                            id="date"
                            value={date}
                            onChange={(e) => setDate(e.target.value)}
                            required
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="description">Description:</label>
                        <textarea
                            id="description"
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                            required
                            placeholder="Enter Description"
                        />
                    </div>
                    <button type="submit" className="btn btn-primary submit-button">Add Expense</button>
                </form>
            </div>
        </div>
    );
};

export default Expense;
