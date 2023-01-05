package DAL.Interfaces;

import BE.Category;

public interface ICategoryDAO {
    Category addCategory(Category category) throws Exception;

    void deleteCategory(Category category) throws Exception;
}
