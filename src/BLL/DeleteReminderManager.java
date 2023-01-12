package BLL;

import BE.Movie;
import BLL.Interfaces.ICatMovieManager;
import BLL.Interfaces.IDeleteReminderManager;
import DAL.DB.MovieDAO_DB;
import DAL.Interfaces.IMovieDAO;
import GUI.Models.DeleteReminderModel;

import java.util.ArrayList;
import java.util.List;

public class DeleteReminderManager implements IDeleteReminderManager {

    IMovieDAO movieDAO;
    ICatMovieManager catMovieManager;

    DeleteReminderModel deleteReminderModel;

    public DeleteReminderManager() { movieDAO = new MovieDAO_DB(); }

    /**
     * gets a list of movie objects from the database
     * @return a list of movie objects
     * @throws Exception
     */
    @Override
    public List<Movie> getAllOldMovies() throws Exception {
        return movieDAO.getAllOldMovies();
    }

    /**
     * sends a movie object to the database to delete it
     * @param movie a movie object that the user selected in the gui
     * @throws Exception
     */
    @Override
    public void deleteMovie(Movie movie) throws Exception
    {
        movieDAO.deleteMovie(movie);
    }

    /**
     * sends an ArrayList to the database to delete the movie objects that are contained in it
     * @param deleteAllMovies an ArrayList of movie objects
     * @throws Exception
     */
    @Override
    public void deleteAllMovies(ArrayList<Movie> deleteAllMovies) throws Exception {
        movieDAO.deleteAllOldMovies(deleteAllMovies);
    }
}
