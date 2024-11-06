import axiosInstance from './axiosInstance'; // Import your configured axios instance

// Fetch all expenses
export const getAllExpenses = async () => {
    try {
        const response = await axiosInstance.get('/user/expenses');
        return response.data.map(expense => ({
            id: expense.expenseId,
            user:expense.user.name,
            category: expense.category.name,
            amount: expense.amount,
            date: expense.date,
            description: expense.description
        }));
    } catch (error) {
        console.error('Error fetching expenses:', error);
        throw error;
    }
};


// Delete an expense
export const deleteExpense = async (id) => {
    try {
        await axiosInstance.delete(`/user/expenses/${id}`);
    } catch (error) {
        console.error('Error deleting expense:', error);
        throw error;
    }
};
