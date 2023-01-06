package GUI.Models;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import BLL.CatMovieManager;
import BLL.Interfaces.ICatMovieManager;
import BLL.Interfaces.IMovieManager;
import BLL.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

public class MovieModel {

    IMovieManager movieManager;
    ICatMovieManager catMovieManager;
    List<CatMovie> catMovieList;
    private ObservableList<Movie> movieObservableList;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        catMovieManager = new CatMovieManager();
        catMovieList = new ArrayList<>();
        movieObservableList = FXCollections.observableArrayList();
        getAllMovies();
        getAllCatMovies();
    }

    /**
     * Removes a category from all movies in the list.
     * @param category The category to remove.
     */
    public void updateMovieCats(Category category) {
        movieObservableList.replaceAll(movie -> {
            List<Category> catsToRemove = new ArrayList<>();

            for (Category cat : movie.getCategories()) {
                if (cat.getId() == category.getId()) catsToRemove.add(cat);
            }

            for (Category cat : catsToRemove) {
                movie.removeCategory(cat);
            }

            return movie;
        });
    }

    private void getAllCatMovies() throws Exception {
        catMovieList.addAll(catMovieManager.getAllCatMovies());
    }
    private void getAllMovies() throws Exception {
        movieObservableList.addAll(movieManager.getAllMovies());
    }

    public List<CatMovie> getCatMovieList() {
        return catMovieList;
    }

    public ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }

    public Movie getMovieFromID(int id){
        for (Movie m:movieObservableList) {
            if (id == m.getId()){
                return m;
            }
        }
        return null;
    }

}
