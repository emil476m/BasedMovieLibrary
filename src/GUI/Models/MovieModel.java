package GUI.Models;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import BLL.CatMovieManager;
import BLL.Interfaces.ICatMovieManager;
import BLL.Interfaces.IMovieManager;
import BLL.MovieManager;
import GUI.Util.LevenshteinCalculator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class MovieModel {

    private IMovieManager movieManager;
    private ICatMovieManager catMovieManager;
    private List<CatMovie> catMovieList;
    private List<Movie> allMovies;
    private ObservableList<Movie> movieObservableList;
    private ObservableList<Movie> moviesToDelete;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();
        catMovieManager = new CatMovieManager();
        catMovieList = new ArrayList<>();
        allMovies = new ArrayList<>();
        movieObservableList = FXCollections.observableArrayList();
        moviesToDelete = FXCollections.observableArrayList();
        getAllMovies();
        getAllCatMovies();
        getAllMoviesToDelete();
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

    private void getAllMoviesToDelete() throws Exception {
        moviesToDelete.addAll(movieManager.getAllOldMovies());
    }

    private void getAllCatMovies() throws Exception {
        catMovieList.addAll(catMovieManager.getAllCatMovies());
    }
    private void getAllMovies() throws Exception {
        List<Movie> movies = movieManager.getAllMovies();
        movieObservableList.addAll(movies);
        allMovies.addAll(movies);
    }

    public List<CatMovie> getCatMovieList() {
        return catMovieList;
    }

    public ObservableList<Movie> getMovieObservableList() {
        return movieObservableList;
    }
    public ObservableList<Movie> getMoviesToDeleteObservableList() { return moviesToDelete; }

    /**
     * loops thru all movies in the observable list.
     * @param id movie id.
     * @return movie with the given id.
     */
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
    /*public ObservableList<Movie> getSearchResults(String query)
    {
        ObservableList<Movie> searchResults = FXCollections.observableArrayList();

        for (Movie m: movieObservableList)
        {
            boolean titleContains = m.getTitle().toLowerCase().contains(query);
            boolean categoriesContains = !m.getCategories().isEmpty() && m.getCategoryNames().toLowerCase().contains(query);
            boolean ratingContains = String.valueOf(m.getRating()).contains(query);
            boolean pRatingContains = String.valueOf(m.getPRating()).contains(query);

            boolean addMovie = titleContains || categoriesContains || ratingContains || pRatingContains;

            if (addMovie) searchResults.add(m);
        }

        return searchResults;
    }*/

    public void searchMovies (String query) {
        movieObservableList.clear();

        for (Movie m: allMovies)
        {
            boolean titleContains = m.getTitle().toLowerCase().contains(query);
            boolean categoriesContains = !m.getCategories().isEmpty() && m.getCategoryNames().toLowerCase().contains(query);
            boolean ratingContains = String.valueOf(m.getRating()).contains(query);
            boolean pRatingContains = String.valueOf(m.getPRating()).contains(query);

            boolean addMovie = titleContains || categoriesContains || ratingContains || pRatingContains;

            if (addMovie) movieObservableList.add(m);
        }
    }

    public void clearSearch() {
        movieObservableList.clear();
        movieObservableList.addAll(allMovies);
    }

    /**
     * adds the new movie to the observable list and sends the new movie object down to the database.
     * @param movie
     * @throws Exception
     */
    public void createMovie(Movie movie) throws Exception {
        if (movieExists(movie)) {
            throw new Exception("Movie title already exists");
        }
        else {
            //sends the movie down to the database and get a movie object back with an id generated by the database.
            Movie newMovie =  movieManager.createMovie(movie);
            //adds the relations between the movie and categories to the database.
            catMovieManager.createMovies(newMovie);
            //adds the movie to the observable list so the user can see it.
            movieObservableList.add(newMovie);
            allMovies.add(newMovie);
        }
    }

    /**
     * Uses the Levenshtein Distance to check if a movie already exists.
     * @param movie The movie to check for.
     * @return true if the movie already exists, otherwise false.
     */
    private boolean movieExists(Movie movie) {
        int minChanges = 3;

        for (Movie m : allMovies) {
            int levDistance = LevenshteinCalculator.distance(movie.getTitle(), m.getTitle());

            if (levDistance < minChanges) return true;
        }

        return false;
    }

    /**
     * edits the Prating on the movie
     * @param movie the edited movie
     * @param newRating the new prating
     * @throws Exception if it fails to edit rating
     */
    public void editPRating(Movie movie, double newRating) throws Exception {
        //sets the new rating
        movie.setPRating(newRating);
        //sends the movie down to be edited in the database.
        movieManager.editPRating(movie);
    }

    /**
     * Deletes and removes a movie from the movies to delete list,
     * @param movie The movie to delete.
     * @throws Exception If it fails to delete the movie.
     */
    public void deleteMovieToDelete(Movie movie) throws Exception {
        deleteMovie(movie);

        moviesToDelete.remove(movie);
    }

    /**
     * Instructs the movie manager to delete a movie,
     * and then removes the same movie from the list.
     * @param movie The movie to delete.
     * @throws Exception If it fails to delete the movie.
     */
    public void deleteMovie(Movie movie) throws Exception {
        movieManager.deleteMovie(movie);

        // Deletes the relations of the movie.
        catMovieManager.deleteWhereMovie(movie);

        movieObservableList.remove(movie);
        allMovies.remove(movie);
    }

    /**
     * Deletes all the movies that were set for deletion.
     * @throws Exception If it fails to delete the movies.
     */
    public void deleteAllMoviesToDelete() throws Exception {
        movieManager.deleteAllMovies(moviesToDelete);

        catMovieManager.deleteWhereOldMoives(moviesToDelete);

        movieObservableList.removeAll(moviesToDelete);
        allMovies.removeAll(moviesToDelete);
        moviesToDelete.clear();
    }

    public void updateLastViewed(Movie movie) throws Exception {
        movieManager.updateLastViewed(movie);
    }
}
