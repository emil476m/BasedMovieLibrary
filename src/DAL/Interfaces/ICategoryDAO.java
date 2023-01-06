package DAL.Interfaces;

import BE.Category;

import java.util.List;

public interface ICategoryDAO {
    Category addCategory(Category category) throws Exception;

    List<Category> getAllCategories() throws Exception;

    void deleteCategory(Category category) throws Exception;
}
