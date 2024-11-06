import axiosInstance from './axiosInstance'; // Import your configured axios instance

// Add a new expense
export const addExpense = async (expenseData) => {
    try {
        await axiosInstance.post('/user/expenses', expenseData);
    } catch (error) {
        console.error('Error adding expense:', error);
        throw error; // Re-throw the error to be handled in the component
    }
};
