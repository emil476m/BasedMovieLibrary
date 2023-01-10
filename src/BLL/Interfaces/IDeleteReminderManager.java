package BLL.Interfaces;

import BE.Movie;

import java.util.List;

public interface IDeleteReminderManager {

    List<Movie> getAllMovies() throws Exception;

    void deleteMovie(Movie movie) throws Exception;

    void deleteAllMovies() throws Exception;
}
