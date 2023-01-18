package DAL.Interfaces;

import BE.Category;
import java.util.List;

public interface ICategoryDAO {
    /**
     * Adds a new category to the database,
     * and returns the newly created category.
     * @param category The category to add.
     * @return The newly created category from the database.
     * @throws Exception If it fails to add the category.
     */
    Category addCategory(Category category) throws Exception;

    /**
     * Retrieves all categories in the database.
     * @return A list of all categories.
     * @throws Exception If it fails to retrieve the categories.
     */
    List<Category> getAllCategories() throws Exception;

    /**
     * Deletes a category from the database.
     * @param category The category to delete.
     * @throws Exception If it fails to delete the category.
     */
    void deleteCategory(Category category) throws Exception;
}
