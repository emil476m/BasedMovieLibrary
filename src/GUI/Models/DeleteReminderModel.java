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


    public ObservableList<Movie> getMoviesToDelete()
    {
        return moviesToDelete;
    }

    public void addMoviesToMoviesToDelete() throws Exception {
        moviesToDelete.addAll(deleteReminderManager.getAllMovies());
    }

    public void deleteMovie(Movie movie) throws Exception
    {
        deleteReminderManager.deleteMovie(movie);

        catMovieManager.deleteWhereMovie(movie);

        moviesToDelete.remove(movie);
    }

    public void deleteAllmovies() throws Exception {
        ArrayList<Movie> deleteAllMovies = (ArrayList<Movie>) moviesToDelete;
        deleteReminderManager.deleteAllMovies(deleteAllMovies);
        catMovieManager.deleteWhereOldMoives(deleteAllMovies);

        moviesToDelete.clear();
        getMoviesToDelete();
    }

    public void ClearListOnClose()
    {
        moviesToDelete.clear();
    }

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
