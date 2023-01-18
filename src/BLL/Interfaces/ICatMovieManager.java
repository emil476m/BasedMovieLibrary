package BLL.Interfaces;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import java.util.List;

public interface ICatMovieManager {
    /**
     * Return a list of CatMovie objects from the database.
     * @return A list of all CatMovies.
     * @throws Exception If it fails to return a list of CatMovie objects.
     */
    List<CatMovie> getAllCatMovies() throws Exception;

    /**
     * Creates a movie's category relations.
     * @param movie The movie to create the relations of.
     * @throws Exception if it fails to create the relations.
     */
    void createMovies(Movie movie) throws Exception;

    /**
     * Instructs the cat movie DAO to delete
     * the relations of a category.
     * @param category The category to delete relations of.
     * @throws Exception If it fails to delete the relations.
     */
    void deleteWhereCat(Category category) throws Exception;

    /**
     * Instructs the cat movie DAO to delete
     * the relations of a movie.
     * @param movie The movie to delete relations of.
     * @throws Exception If it fails to delete the relations.
     */
    void deleteWhereMovie(Movie movie) throws Exception;

    /**
     * Instructs the movie DAO to delete the relations of a list of movies.
     * @param movies The movies to delete the relations of.
     * @throws Exception If it fails to delete the relations.
     */
    void deleteMoviesRelations(List<Movie> movies) throws Exception;
}