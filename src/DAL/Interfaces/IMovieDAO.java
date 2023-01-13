package DAL.Interfaces;

import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public interface IMovieDAO {
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

    /**
     * returns a list of movie objects from the database that have not been opened in more than 17520 hours
     * @return a list of movies that have not been opened in more than 17520 hours
     * @throws Exception
     */
    List<Movie> getAllOldMovies() throws Exception;

    /**
     * gets a list from the business logic layer of movies that should be deleted
     * @param deleteAllOldMovies a list of movie objects to delete
     * @throws Exception
     */
    void deleteAllOldMovies(List<Movie> deleteAllOldMovies) throws Exception;

    /**
     * gets a boolean value and movie object so the database knows to update when the movie was last viewed
     * @param movie a movie object that the user has selected in the gui
     * @throws Exception
     */
    void updateLastViewed(Movie movie) throws Exception;
}