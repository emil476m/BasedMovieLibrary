package DAL.Interfaces;

import BE.Movie;
import java.util.List;

public interface IMovieDAO {
    /**
     * Return a list of Movie objects from the database.
     * @return A list of all Movies.
     * @throws Exception throws exception if it fails to return a list of Movie objects.
     */
    List<Movie> getAllMovies() throws Exception;

    /**
     * Inserts a newly created movie into the database and returns the movie with its new id.
     * @param movie The movie to create.
     * @return The created movie with its new id.
     * @throws Exception if it fails to create the movie.
     */
    Movie createMovie(Movie movie) throws Exception;

    /**
     * Deletes a movie from the database.
     * @param movie The movie to delete.
     * @throws Exception If it fails to delete the movie.
     */
    void deleteMovie(Movie movie) throws Exception;

    /**
     * Edits the personal rating of a movie.
     * @param movie The movie with its new personal rating.
     * @throws Exception if it fails to edit the personal rating.
     */
    void editPRating(Movie movie) throws Exception;

    /**
     * Returns a list of movie objects from the database that have not been opened in more than 17520 hours.
     * @return A list of movies that have not been opened in more than 17520 hours
     * @throws Exception If it fails to retrieve the movies.
     */
    List<Movie> getAllOldMovies() throws Exception;

    /**
     * Deletes all given movies.
     * @param movies The movies to delete.
     * @throws Exception If it fails to delete the movies.
     */
    void deleteMovies(List<Movie> movies) throws Exception;

    /**
     * Updates the last time a movie has been viewed.
     * @param movie The movie to update.
     * @throws Exception If it fails to update the movie.
     */
    void updateLastViewed(Movie movie) throws Exception;
}