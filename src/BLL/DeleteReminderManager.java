package BLL;

import BE.Movie;
import BLL.Interfaces.IDeleteReminderManager;
import DAL.DB.MovieDAO_DB;
import DAL.Interfaces.IMovieDAO;

import java.util.List;

public class DeleteReminderManager implements IDeleteReminderManager {

    IMovieDAO movieDAO;

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
    public void deleteAllMovies() throws Exception {

    }
}
