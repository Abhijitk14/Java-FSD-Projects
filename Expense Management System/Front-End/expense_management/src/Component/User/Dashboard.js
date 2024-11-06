import React, { useEffect, useState } from 'react';
import { getAllExpenses, deleteExpense } from '../../Service/expenseService'; // Update import path if needed
import Navbar from '../../Navbar';
import './Dashboard.css';

const Dashboard = () => {
    const [expenses, setExpenses] = useState([]);

    // Fetch expenses when the component mounts
    useEffect(() => {
        const fetchExpenses = async () => {
            try {
                const data = await getAllExpenses();
                console.log('Fetched expenses:', data); // Debugging: Check fetched data
                setExpenses(data);
            } catch (error) {
                console.error('Error fetching expenses:', error);
            }
        };

        fetchExpenses();
    }, []);

    // Handle delete expense
    const handleDelete = async (id) => {
        if (window.confirm('Are you sure you want to delete this expense?')) {
            try {
                await deleteExpense(id);
                setExpenses(expenses.filter(expense => expense.id !== id));
            } catch (error) {
                console.error('Error deleting expense:', error);
            }
        }
    };


    return (
        <div className="dashboard-container">
            <Navbar />
            <div className="dashboard-content">
                <h1>Your Expenses</h1>
                <table className="expenses-table">
                    <thead>
                        <tr>
                            <th>Expense id</th>
                            <th>User Name</th>
                            <th>Category</th>
                            <th>Amount</th>
                            <th>Date</th>
                            <th>Description</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {expenses.length > 0 ? (
                            expenses.map(expense => (
                                <tr key={expense.expenseId}>
                                    <td>{expense.id}</td>
                                    <td>{expense.user}</td>
                                    <td>{expense.category}</td>
                                    <td>${expense.amount}</td>
                                    <td>{new Date(expense.date).toLocaleDateString()}</td>
                                    <td>{expense.description}</td>
                                    <td>
                                        <button onClick={() => handleDelete(expense.id)}>Delete</button>
                                    </td>
                                </tr>
                            ))
                        ) : (
                            <tr>
                                <td colSpan="5">No expenses found.</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Dashboard;
