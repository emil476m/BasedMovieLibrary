package BLL.Interfaces;

import BE.Category;
import java.util.List;

public interface ICategoryManager {
    /**
     * Instructs the DAO to add a new category,
     * and returns the newly created category.
     * @param category The category to add.
     * @return The newly created category.
     * @throws Exception If it fails to add the category.
     */
    Category addCategory(Category category) throws Exception;

    /**
     * Instructs the DAO to retrieve all categories.
     * @return A list of all categories.
     * @throws Exception If it fails to retrieve the categories.
     */
    List<Category> getAllCategories() throws Exception;

    /**
     * Instructs the DAO to delete a category.
     * @param category The category to delete.
     * @throws Exception If it fails to delete the category.
     */
    void deleteCategory(Category category) throws Exception;
}
