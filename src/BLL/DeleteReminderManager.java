package BLL;

import BE.Movie;
import BLL.Interfaces.IDeleteReminderManager;
import DAL.DB.MovieDAO_DB;
import DAL.DB.OldMovieDAO_DB;
import DAL.Interfaces.IMovieDAO;
import DAL.Interfaces.IOldMovieDAO;
import javafx.collections.ObservableList;

import java.util.List;

public class DeleteReminderManager implements IDeleteReminderManager {

    IOldMovieDAO movieDAO;

    public DeleteReminderManager() { movieDAO = new OldMovieDAO_DB(); }

    @Override
    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception
    {
        movieDAO.deleteMovie(movie);
    }

    @Override
    public void deleteAllMovies() throws Exception {

    }
}
