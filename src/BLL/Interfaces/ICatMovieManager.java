package BLL.Interfaces;

import BE.CatMovie;
import BE.Movie;

import java.util.List;

public interface ICatMovieManager {
    /**
     * Return a list of CatMovie objects from the database.
     * @return A list of all CatMovies.
     * @throws Exception throws exception if it fails to return a list of CatMovie objects.
     */
    List<CatMovie> getAllCatMovies() throws Exception;

    /**
     * Inserts a newly created movie relations into the database.
     * @param movie the created movie.
     * @throws Exception if it fails to create a movie.
     */
    void createMovies(Movie movie) throws Exception;

}