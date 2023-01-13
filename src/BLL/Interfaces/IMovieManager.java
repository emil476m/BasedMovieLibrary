package BLL.Interfaces;

import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public interface IMovieManager {
    /**
     * gets a list of all movie objects that have not been opened in more than 2 years and has a personal rating of less than 6
     * @return a list of movie objects
     * @throws Exception
     */
    List<Movie> getAllOldMovies() throws Exception;

    /**
     * sends an ArrayList of movie objects down to the database so the movie objects can be deleted
     * @param deleteAllMovies an ArrayList of movie objects
     * @throws Exception
     */
    void deleteAllMovies(List<Movie> deleteAllMovies) throws Exception;

    /**
     * Return a list of Movie objects from the database.
     * @return A list of all Movies.
     * @throws Exception throws exception if it fails to return a list of Movie objects.
     */
    List<Movie> getAllMovies() throws Exception;

    /**
     * Inserts a newly created movie into the database and returns the movies' id.
     * @param movie the created movie.
     * @return movie with id.
     * @throws Exception if it fails to create a movie.
     */
    Movie createMovie(Movie movie) throws Exception;

    void deleteMovie(Movie movie) throws Exception;

    /**
     * It edits the personal rating of the movies in the database, that matches the id of the Movie object.
     * @param movie last selected movie.
     * @throws Exception if it fails to edit the database.
     */
    void editPRating(Movie movie) throws Exception;

    void updateLastViewed(Movie movie) throws Exception;
}
