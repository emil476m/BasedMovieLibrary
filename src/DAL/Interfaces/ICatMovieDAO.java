package DAL.Interfaces;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import java.util.List;

public interface ICatMovieDAO {
    /**
     * Return a list of CatMovie objects from the database.
     * @return A list of all CatMovies.
     * @throws Exception If it fails to return a list of CatMovie objects.
     */
    List<CatMovie> getAllCatMovies() throws Exception;

    /**
     * Creates a movie's category relations.
     * @param movie the created movie.
     * @throws Exception if it fails to create the movie's relations.
     */
    void createMovieRelations(Movie movie) throws Exception;

    /**
     * Deletes all the relations of a category.
     * @param category The category to delete the relations of.
     * @throws Exception If it fails to delete the relations.
     */
    void deleteWhereCat(Category category) throws Exception;

    /**
     * Deletes all the relations of a movie.
     * @param movie The movie to delete the relations of.
     * @throws Exception If it fails to delete the relations.
     */
    void deleteWhereMovie(Movie movie) throws Exception;

    /**
     * Deletes all the category relations of given movies.
     * @param movies The movies to delete the relations of.
     * @throws Exception If it fails to delete the relations.
     */
    void deleteMoviesRelations(List<Movie> movies) throws Exception;
}
