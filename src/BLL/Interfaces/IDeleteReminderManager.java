package BLL.Interfaces;

import BE.Movie;

import java.util.ArrayList;
import java.util.List;

public interface IDeleteReminderManager {

    /**
     * gets a list of all movie objects that have not been opened in more than 2 years and has a personal rating of less than 6
     * @return a list of movie objects
     * @throws Exception
     */
    List<Movie> getAllOldMovies() throws Exception;

    /**
     * sends a movie object down to the database so the movie can be deleted
     * @param movie a movie object that the user selected in the gui
     * @throws Exception
     */
    void deleteMovie(Movie movie) throws Exception;

    /**
     * sends an ArrayList of movie objects down to the database so the movie objects can be deleted
     * @param deleteAllMoveies an ArrayList of movie objects
     * @throws Exception
     */
    void deleteAllMovies(ArrayList<Movie> deleteAllMoveies) throws Exception;
}
