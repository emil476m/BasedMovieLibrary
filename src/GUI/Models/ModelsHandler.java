package GUI.Models;

import BE.CatMovie;
import BE.Category;
import BE.Movie;

/**
 * Holds all the models used by the program,
 * so that controllers only have to know of this class.
 */
public class ModelsHandler {
    private CategoryModel categoryModel;
    private MovieModel movieModel;
    private DeleteReminderModel deleteReminderModel;

    public ModelsHandler() throws Exception {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
        deleteReminderModel = new DeleteReminderModel();
        initializeMovieCategories();
    }

    /**
     * finds the correct category name/type from at category id.
     */
    private void initializeMovieCategories(){
        for (CatMovie catMovie: movieModel.getCatMovieList()){
            Movie movie = movieModel.getMovieFromID(catMovie.getMovieId());
            Category cat = categoryModel.getCategoryFromID(catMovie.getCategoryId());

            if ((movie != null) && (cat != null)){
                int index = movieModel.getMovieObservableList().indexOf(movie);
                movieModel.getMovieObservableList().get(index).addCategory(cat);
            }
        }
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public MovieModel getMovieModel() { return movieModel; }

   public DeleteReminderModel getDeleteReminderModel() { return deleteReminderModel; }
}
