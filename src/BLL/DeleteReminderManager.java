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

    @Override
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllOldMovies();
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception
    {
        movieDAO.deleteMovie(movie);
    }

    @Override
    public void deleteAllMovies(ArrayList<Movie> deleteAllMovies) throws Exception {
        movieDAO.deleteAllOldMovies(deleteAllMovies);
    }
}
