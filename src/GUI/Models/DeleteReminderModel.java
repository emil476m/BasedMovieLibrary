package GUI.Models;

import BE.Movie;
import BLL.DeleteReminderManager;
import BLL.Interfaces.ICatMovieManager;
import BLL.Interfaces.IDeleteReminderManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class DeleteReminderModel {
    IDeleteReminderManager deleteReminderManager;
    ICatMovieManager catMovieManager;

    private ObservableList<Movie> moviesToDelete;

    public DeleteReminderModel() throws Exception {
        deleteReminderManager = new DeleteReminderManager();
        moviesToDelete = FXCollections.observableArrayList();
        addMoviesToMoviesToDelete();
    }


    /**
     * gets a ObservableList of movie objects
     * @return a ObservableList of movie objects
     */
    public ObservableList<Movie> getMoviesToDelete()
    {
        return moviesToDelete;
    }

    /**
     * makes a call to the deleteReminderManager to get all movies
     * @throws Exception
     */
    public void addMoviesToMoviesToDelete() throws Exception {
        moviesToDelete.addAll(deleteReminderManager.getAllOldMovies());
    }

    /**
     * sends a movie object to the deleteReminderManager and catMovieManager before removing the movie from the moviesToDelete ObservableList
     * @param movie the movie object that the user wants to delete
     * @throws Exception
     */
    public void deleteMovie(Movie movie) throws Exception
    {
        deleteReminderManager.deleteMovie(movie);

        catMovieManager.deleteWhereMovie(movie);

        moviesToDelete.remove(movie);
    }

    /**
     * sends the deleteAllMovies list to the deleteReminderManager and catMovieManager before clearing and updating the ObservableList moviesToDelete
     * @throws Exception
     */
    public void deleteAllmovies() throws Exception {
        ArrayList<Movie> deleteAllMovies = (ArrayList<Movie>) moviesToDelete;
        deleteReminderManager.deleteAllMovies(deleteAllMovies);
        catMovieManager.deleteWhereOldMoives(deleteAllMovies);

        moviesToDelete.clear();
        getMoviesToDelete();
    }

    /**
     * Clears the ObservableList moviesToDelete
     */
    public void ClearListOnClose()
    {
        moviesToDelete.clear();
    }

    /**
     * Checks if the moviesToDelete ObservableList's size is bigger than 0 so the computer knows that the list is not empty
     * @return returns true if moviesToDelete has a size bigger than 0 else it returns false
     */
    public boolean isntEmpty()
    {
        getMoviesToDelete();
        if(moviesToDelete.size() > 0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
