package GUI.Models;

/**
 * Holds all the models used by the program,
 * so that controllers only have to instantiate this class.
 */
public class ModelsHandler {
    private CategoryModel categoryModel;

    public ModelsHandler() {
        categoryModel = new CategoryModel();
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }
}
