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

    public ModelsHandler() throws Exception {
        categoryModel = new CategoryModel();
        movieModel = new MovieModel();
        initializeMovieCategories();
    }

    /**
     * finds the correct category name/type from at category id.
     */
    private void initializeMovieCategories(){
        //loops through all catMovies.
        for (CatMovie catMovie: movieModel.getCatMovieList()){
            //loops through all movies to find the movie with the given id.
            Movie movie = movieModel.getMovieFromID(catMovie.getMovieId());
            //loops through all categories to find the category with the given id.
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
}
