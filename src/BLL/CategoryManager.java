package BLL;

import BE.Category;
import BLL.Interfaces.ICategoryManager;
import DAL.DB.CategoryDAO_DB;
import DAL.Interfaces.ICategoryDAO;

public class CategoryManager implements ICategoryManager {
    ICategoryDAO categoryDAO = new CategoryDAO_DB();

    /**
     * Instructs the DAO to add a new category,
     * and returns the newly created category.
     * @param category The category to add.
     * @return The newly created category.
     * @throws Exception If it fails to add the category.
     */
    @Override
    public Category addCategory(Category category) throws Exception {
        return categoryDAO.addCategory(category);
    }

    /**
     * Instructs the DAO to delete a category.
     * @param category The category to delete.
     * @throws Exception If it fails to delete the category.
     */
    @Override
    public void deleteCategory(Category category) throws Exception {
        categoryDAO.deleteCategory(category);
    }
}
