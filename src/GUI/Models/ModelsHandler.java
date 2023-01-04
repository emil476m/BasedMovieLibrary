package GUI.Models;

/**
 * Holds all the models used by the program,
 * so that controllers only have to know of this class.
 */
public class ModelsHandler {
    private CategoryModel categoryModel;
    private MovieModel movieModel;

    public ModelsHandler() {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public MovieModel getMovieModel() { return movieModel; }
}
