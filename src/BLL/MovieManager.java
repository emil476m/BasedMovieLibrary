package BLL;

import BE.Movie;
import BLL.Interfaces.IMovieManager;
import DAL.DB.MovieDAO_DB;
import DAL.Interfaces.IMovieDAO;

import java.util.List;

public class MovieManager implements IMovieManager {
    IMovieDAO movieDAO;
    public MovieManager(){
        movieDAO = new MovieDAO_DB();
    }

    /**
     * Return a list of Movie objects from the database.
     * @return A list of all Movies.
     * @throws Exception throws exception if it fails to return a list of Movie objects.
     */
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    /**
     * Instructs the movie DAO to delete a movie.
     * @param movie The movie to delete.
     * @throws Exception If it fails to delete the movie.
     */
    @Override
    public void deleteMovie(Movie movie) throws Exception {
        movieDAO.deleteMovie(movie);
    }
}
