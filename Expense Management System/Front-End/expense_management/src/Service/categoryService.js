import axiosInstance from './axiosInstance';

// Get all categories
export const getAllCategories = async () => {
    try {
        const response = await axiosInstance.get('/user/categories'); // Adjust the URL if needed
        return response.data;
    } catch (error) {
        console.error('Error fetching categories:', error);
        throw error;
    }
};

// Add a new category
export const addCategory = async (categoryData) => {
    try {
        await axiosInstance.post('/user/categories', categoryData); // Adjust the URL if needed
    } catch (error) {
        console.error('Error adding category:', error);
        throw error;
    }
};

// Delete a category
export const deleteCategory = async (categoryId) => {
    try {
        await axiosInstance.delete(`/user/categories/${categoryId}`); // Adjust the URL if needed
    } catch (error) {
        console.error('Error deleting category:', error);
        throw error;
    }
};

// Update a category
export const updateCategory = async (categoryId, categoryData) => {
    try {
        await axiosInstance.put(`/user/categories/${categoryId}`, categoryData); // Adjust the URL if needed
    } catch (error) {
        console.error('Error updating category:', error);
        throw error;
    }
};


