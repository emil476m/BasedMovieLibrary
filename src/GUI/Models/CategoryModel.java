package GUI.Models;

import BE.Category;
import BLL.CategoryManager;
import BLL.Interfaces.ICategoryManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class CategoryModel {
    private ICategoryManager categoryManager;
    private ObservableList<Category> categories;

    public CategoryModel() throws Exception {
        categoryManager = new CategoryManager();
        categories = FXCollections.observableArrayList();
        getAllCategories();
    }

    public ObservableList<Category> getCategories() {
        return categories;
    }

    /**
     * Passes a new category to the category manager,
     * and adds the newly created category to the list.
     * @param category The category to add.
     * @throws Exception If it fails to add the category.
     */
    public void addCategory(Category category) throws Exception {
        Category newCategory = categoryManager.addCategory(category);

        if (newCategory != null) categories.add(newCategory);
    }

    /**
     * Retrieves all categories and adds them to the list.
     * @throws Exception If it fails to retrieve the categories.
     */
    private void getAllCategories() throws Exception {
        List<Category> allCategories = categoryManager.getAllCategories();

        categories.addAll(allCategories);
    }
}
