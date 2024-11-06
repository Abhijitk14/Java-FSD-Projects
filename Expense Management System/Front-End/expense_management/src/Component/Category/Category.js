import React, { useEffect, useState } from 'react';
import Navbar from '../../Navbar';
import './Category.css';
import { getAllCategories, addCategory, deleteCategory, updateCategory } from '../../Service/categoryService'; // Assuming you have these APIs

const Category = () => {
    const [categories, setCategories] = useState([]);
    const [showForm, setShowForm] = useState(false);
    const [formTitle, setFormTitle] = useState('Add Category');
    const [newCategoryName, setNewCategoryName] = useState('');
    const [newCategoryDescription, setNewCategoryDescription] = useState('');
    const [editingCategoryId, setEditingCategoryId] = useState(null);

    // Fetch categories on component mount
    useEffect(() => {
        const fetchCategories = async () => {
            try {
                const response = await getAllCategories();
                setCategories(response);
            } catch (error) {
                console.error('Error fetching categories:', error);
            }
        };

        fetchCategories();
    }, []);

    // Handle form submission for adding/updating a category
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (editingCategoryId) {
                // Update category
                await updateCategory(editingCategoryId, {
                    name: newCategoryName,
                    description: newCategoryDescription
                });
            } else {
                // Add new category
                await addCategory({
                    name: newCategoryName,
                    description: newCategoryDescription
                });
            }

            // Refresh category list
            const updatedCategories = await getAllCategories();
            setCategories(updatedCategories);
            resetForm();
        } catch (error) {
            console.error('Error saving category:', error);
        }
    };

    // Handle delete category
    const handleDelete = async (categoryId) => {
        if (window.confirm('Are you sure you want to delete this category?')) {
            try {
                await deleteCategory(categoryId);
                // Refresh category list
                const updatedCategories = await getAllCategories();
                setCategories(updatedCategories);
            } catch (error) {
                console.error('Error deleting category:', error);
            }
        }
    };

    // Handle editing a category (populate form with category data)
    const handleEdit = (category) => {
        setFormTitle('Update Category');
        setNewCategoryName(category.name);
        setNewCategoryDescription(category.description);
        setEditingCategoryId(category.categoryId);
        setShowForm(true);
    };

    // Reset form and hide
    const resetForm = () => {
        setFormTitle('Add Category');
        setNewCategoryName('');
        setNewCategoryDescription('');
        setEditingCategoryId(null);
        setShowForm(false);
    };

    return (
        <div className="category-container">
            <Navbar />
            <div className="category-content">
                <button className="btn btn-primary" onClick={() => setShowForm(!showForm)}>
                    {showForm ? 'Close Form' : 'Add Category'}
                </button>

                {/* Conditionally render the category form */}
                {showForm && (
                    <div className="category-form">
                        <h2>{formTitle}</h2>
                        <form onSubmit={handleSubmit}>
                            <div className="form-group">
                                <label htmlFor="categoryName">Name:</label>
                                <input
                                    type="text"
                                    id="categoryName"
                                    value={newCategoryName}
                                    onChange={(e) => setNewCategoryName(e.target.value)}
                                    placeholder="Enter category name"
                                    required
                                />
                            </div>
                            <div className="form-group">
                                <label htmlFor="categoryDescription">Description:</label>
                                <input
                                    type="text"
                                    id="categoryDescription"
                                    value={newCategoryDescription}
                                    onChange={(e) => setNewCategoryDescription(e.target.value)}
                                    placeholder="Enter category description"
                                    required
                                />
                            </div>
                            <button type="submit" className="submit-btn">
                                {editingCategoryId ? 'Update' : 'Add'}
                            </button>
                        </form>
                    </div>
                )}

                <table className="category-table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        {categories.map((category) => (
                            <tr key={category.categoryId}>
                                <td>{category.categoryId}</td>
                                <td>{category.name}</td>
                                <td>{category.description}</td>
                                <td>
                                    <button className="button edit-button" onClick={() => handleEdit(category)}>
                                        Update
                                    </button>
                                    <button className="button delete-button" onClick={() => handleDelete(category.categoryId)}>
                                        Delete
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </div>
        </div>
    );
};

export default Category;
