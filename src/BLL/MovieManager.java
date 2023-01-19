package BLL;

import BE.Movie;
import BLL.Interfaces.IMovieManager;
import DAL.DB.MovieDAO_DB;
import DAL.Interfaces.IMovieDAO;

import java.util.List;

public class MovieManager implements IMovieManager {
    private IMovieDAO movieDAO;
    public MovieManager(){
        movieDAO = new MovieDAO_DB();
    }

    /**
     * Gets a list of all movie objects that have not been opened in more than 2 years
     * and has a personal rating of less than 6.
     * @return A list of old movies.
     * @throws Exception If it fails to retrieve the old movies.
     */
    @Override
    public List<Movie> getAllOldMovies() throws Exception {
        return movieDAO.getAllOldMovies();
    }

    /**
     * Instructs the movie DAO to delete a list of movies.
     * @param movies The movies to delete.
     * @throws Exception If it fails to delete the movies.
     */
    @Override
    public void deleteMovies(List<Movie> movies) throws Exception {
        movieDAO.deleteMovies(movies);
    }

    /**
     * Return a list of Movie objects from the database.
     * @return A list of all Movies.
     * @throws Exception If it fails to return a list of Movie objects.
     */
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    /**
     * Instructs the movie DAO to create a new movie,
     * and returns the movie with its new id.
     * @param movie The movie to create.
     * @return The newly created movie.
     * @throws Exception If it fails to create the movie.
     */
    public Movie createMovie(Movie movie) throws Exception{
        return movieDAO.createMovie(movie);
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

    /**
     * Instructs the movie DAO to update a movie's personal rating.
     * @param movie The movie to update.
     * @throws Exception If it fails to update the movie's personal rating.
     */
    public void editPRating(Movie movie) throws Exception{
        movieDAO.editPRating(movie);
    }

    /**
     * Updates the last time a movie has been viewed.
     * @param movie The movie to update.
     * @throws Exception If it fails to update the movie.
     */
    @Override
    public void updateLastViewed(Movie movie) throws Exception {
        movieDAO.updateLastViewed(movie);
    }
}
