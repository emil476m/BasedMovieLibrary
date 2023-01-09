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
            if(m.getTitle().toLowerCase().contains(query))
            {
                searchResults.add(m);
            }
           if (m.getCategoryNames().toLowerCase().contains(query))
            {
                searchResults.add(m);
            }
           if(String.valueOf(m.getRating()).contains(query))
           {
               searchResults.add(m);
           }
            if(String.valueOf(m.getPRating()).contains(query))
            {
                searchResults.add(m);
            }

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

}
