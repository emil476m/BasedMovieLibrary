package BLL.Interfaces;

import BE.Category;

public interface ICategoryManager {
    Category addCategory(Category category) throws Exception;

    void deleteCategory(Category category) throws Exception;
}
