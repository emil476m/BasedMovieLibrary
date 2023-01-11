package BLL.Interfaces;

import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public interface IDeleteReminderManager {

    List<Movie> getAllMovies() throws Exception;

    void deleteMovie(Movie movie) throws Exception;

    void deleteAllMovies(ArrayList<Movie> deleteAllMoveies) throws Exception;
}
