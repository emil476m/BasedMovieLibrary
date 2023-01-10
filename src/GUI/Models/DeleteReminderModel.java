package GUI.Models;

import BE.Movie;
import BLL.Interfaces.IDeleteReminderManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DeleteReminderModel {
    IDeleteReminderManager deleteReminderManager;

    private ObservableList<Movie> moviesToDelete;

    public DeleteReminderModel() throws Exception {
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
    }
}
