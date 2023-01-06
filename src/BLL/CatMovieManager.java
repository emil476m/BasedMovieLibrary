package BLL;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
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
     * Instructs the cat movie DAO to delete
     * the relations of a category.
     * @param category The category to delete relations of.
     * @throws Exception If it fails to delete the relations.
     */
    @Override
    public void deleteWhereCat(Category category) throws Exception {
        catMovieDAO.deleteWhereCat(category);
    }
}
