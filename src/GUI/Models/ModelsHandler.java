package GUI.Models;

/**
 * Holds all the models used by the program,
 * so that controllers only have to know of this class.
 */
public class ModelsHandler {
    private static CategoryModel categoryModel = new CategoryModel();

    public static CategoryModel getCategoryModel() {
        return categoryModel;
    }
}
