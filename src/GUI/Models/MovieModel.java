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

    private ObservableList<Category> categoryObservableList;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        catMovieManager = new CatMovieManager();
        catMovieList = new ArrayList<>();
        movieObservableList = FXCollections.observableArrayList();
        categoryObservableList = FXCollections.observableArrayList();
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


    /**
     * searches through the movieObservableList to find movies that contain the search query and adds them to a different observableList
     * @param query
     * @return the list of movies that contain the query
     */
    public ObservableList<Movie> getSearchResults(String query)
    {
        ObservableList<Movie> searchResults = FXCollections.observableArrayList();
        for (Movie m: movieObservableList)
        {
            boolean titleContains = m.getTitle().toLowerCase().contains(query);
            boolean categoriesContains = m.getCategoryNames().toLowerCase().contains(query);
            boolean ratingContains = String.valueOf(m.getRating()).contains(query);
            boolean pRatingContains = String.valueOf(m.getPRating()).contains(query);

            boolean addMovie = titleContains || categoriesContains || ratingContains || pRatingContains;

            if (addMovie) searchResults.add(m);
        }

        return searchResults;
    }
    public ObservableList<Category> getCategoryObservableList() {
        return categoryObservableList;
    }

    public void addCatToCreateMovieView(Category category){
        categoryObservableList.add(category);
    }

    public void  removeCatFromCreateMovie(Category category){
        categoryObservableList.remove(category);
    }

    public void createMovie(Movie movie) throws Exception {
        Movie newMovie =  movieManager.createMovie(movie);
        catMovieManager.createMovies(newMovie);
        movieObservableList.add(newMovie);
    }

    /**
     * Instructs the movie manager to delete a movie,
     * and then removes the same movie from the list.
     * @param movie The movie to delete.
     * @throws Exception If it fails to delete the movie.
     */
    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);

        movieObservableList.remove(movie);
    }
}
