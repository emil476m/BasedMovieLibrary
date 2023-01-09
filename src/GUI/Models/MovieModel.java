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
        movieObservableList.clear();
    }


    public void editPRating(Movie movie, double newRating) throws Exception {
        movie.setPRating(newRating);
        movieManager.editPRating(movie);
    }


}
