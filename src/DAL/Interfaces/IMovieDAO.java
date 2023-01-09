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
     * Inserts a newly created movie into the database and returns the movies' id.
     * @param movie the created movie.
     * @return movie with id.
     * @throws Exception if it fails to create a movie.
     */
    Movie createMovie(Movie movie) throws Exception;

    void deleteMovie(Movie movie) throws Exception;
}
