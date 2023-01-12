package DAL.Interfaces;

import BE.CatMovie;
import BE.Category;
import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public interface ICatMovieDAO {

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

    void deleteWhereCat(Category category) throws Exception;

    void deleteWhereMovie(Movie movie) throws Exception;

    /**
     * gets a list from the business logic layer of movies that should have their link deleted
     * @param deleteOldMovies a list of movie objects
     * @throws Exception
     */
    void deleteWhereOldMovies(ArrayList<Movie> deleteOldMovies) throws Exception;
}
