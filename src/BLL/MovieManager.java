package BLL;

import BE.Movie;
import BLL.Interfaces.IMovieManager;
import DAL.DB.MovieDAO_DB;
import DAL.Interfaces.IMovieDAO;

import java.util.List;

public class MovieManager implements IMovieManager {
    IMovieDAO movieDAO;
    public MovieManager(){
        movieDAO = new MovieDAO_DB();
    }

    /**
     * Return a list of Movie objects from the database.
     * @return A list of all Movies.
     * @throws Exception throws exception if it fails to return a list of Movie objects.
     */
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    /**
     * Inserts a newly created movie into the database and returns the movies' id.
     * @param movie the created movie.
     * @return movie with id.
     * @throws Exception if it fails to create a movie.
     */
    public Movie createMovie(Movie movie) throws Exception{
        return movieDAO.createMovie(movie);
    }

    /**
     * It edits the personal rating of the movies in the database, that matches the id of the Movie object.
     * @param movie last selected movie.
     * @throws Exception if it fails to edit the database.
     */
    public void editPRating(Movie movie) throws Exception{
        movieDAO.editPRating(movie);
    }
}
