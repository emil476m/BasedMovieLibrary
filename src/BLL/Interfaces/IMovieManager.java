package BLL.Interfaces;

import BE.Movie;
import java.util.List;

public interface IMovieManager {
    /**
     * Gets a list of all movie objects that have not been opened in more than 2 years
     * and has a personal rating of less than 6.
     * @return A list of old movies.
     * @throws Exception If it fails to retrieve the old movies.
     */
    List<Movie> getAllOldMovies() throws Exception;

    /**
     * Instructs the movie DAO to delete a list of movies.
     * @param movies The movies to delete.
     * @throws Exception If it fails to delete the movies.
     */
    void deleteMovies(List<Movie> movies) throws Exception;

    /**
     * Return a list of Movie objects from the database.
     * @return A list of all Movies.
     * @throws Exception If it fails to return a list of Movie objects.
     */
    List<Movie> getAllMovies() throws Exception;

    /**
     * Instructs the movie DAO to create a new movie,
     * and returns the movie with its new id.
     * @param movie The movie to create.
     * @return The newly created movie.
     * @throws Exception If it fails to create the movie.
     */
    Movie createMovie(Movie movie) throws Exception;

    /**
     * Instructs the movie DAO to delete a movie.
     * @param movie The movie to delete.
     * @throws Exception If it fails to delete the movie.
     */
    void deleteMovie(Movie movie) throws Exception;

    /**
     * Instructs the movie DAO to update a movie's personal rating.
     * @param movie The movie to update.
     * @throws Exception If it fails to update the movie's personal rating.
     */
    void editPRating(Movie movie) throws Exception;

    /**
     * Updates the last time a movie has been viewed.
     * @param movie The movie to update.
     * @throws Exception If it fails to update the movie.
     */
    void updateLastViewed(Movie movie) throws Exception;
}
