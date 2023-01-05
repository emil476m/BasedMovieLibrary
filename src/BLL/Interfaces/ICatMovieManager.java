package BLL.Interfaces;

import BE.CatMovie;

import java.util.List;

public interface ICatMovieManager {
    /**
     * Return a list of CatMovie objects from the database.
     * @return A list of all CatMovies.
     * @throws Exception throws exception if it fails to return a list of CatMovie objects.
     */
    List<CatMovie> getAllCatMovies() throws Exception;
}