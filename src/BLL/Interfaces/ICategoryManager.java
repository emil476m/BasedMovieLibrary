package BLL.Interfaces;

import BE.Category;

import java.util.List;

public interface ICategoryManager {
    Category addCategory(Category category) throws Exception;

    List<Category> getAllCategories() throws Exception;
}
