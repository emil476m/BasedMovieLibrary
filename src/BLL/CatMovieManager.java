package BLL;

import BE.CatMovie;
import BE.Movie;
import BE.Category;
import BLL.Interfaces.ICatMovieManager;
import DAL.DB.CatMovieDAO_DB;
import DAL.Interfaces.ICatMovieDAO;

import java.util.List;

public class CatMovieManager implements ICatMovieManager {
    ICatMovieDAO catMovieDAO;

    public CatMovieManager(){
        catMovieDAO = new CatMovieDAO_DB();
    }

    /**
     * Return a list of CatMovie objects from the database.
     * @return A list of all CatMovies.
     * @throws Exception throws exception if it fails to return a list of CatMovie objects.
     */
    public List<CatMovie> getAllCatMovies() throws Exception{
        return catMovieDAO.getAllCatMovies();
    }

    /**
     * Inserts a newly created movie relations into the database.
     * @param movie the created movie.
     * @throws Exception if it fails to create a movie.
     */
    public void createMovies(Movie movie) throws Exception{
        catMovieDAO.createMovieRelations(movie);
    }

    /**
     * Instructs the cat movie DAO to delete
     * the relations of a category.
     * @param category The category to delete relations of.
     * @throws Exception If it fails to delete the relations.
     */
    @Override
    public void deleteWhereCat(Category category) throws Exception {
        catMovieDAO.deleteWhereCat(category);
    }

    /**
     * Instructs the cat movie DAO to delete
     * the relations of a movie.
     * @param movie The movie to delete relations of.
     * @throws Exception If it fails to delete the relations.
     */
    @Override
    public void deleteWhereMovie(Movie movie) throws Exception {
        catMovieDAO.deleteWhereMovie(movie);
    }

    /**
     * sends an ArrayList to the database to delete the category link the movies in the list has
     * @param deleteOldMovies an ArrayList of movie objects
     * @throws Exception
     */
    @Override
    public void deleteWhereOldMoives(List<Movie> deleteOldMovies) throws Exception {
        catMovieDAO.deleteMoviesRelations(deleteOldMovies);
    }
}
