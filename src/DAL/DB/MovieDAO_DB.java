package DAL.DB;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import DAL.Interfaces.ICatMovieDAO;
import DAL.Interfaces.IMovieDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MovieDAO_DB implements IMovieDAO {

    private DatabaseConnector databaseConnector;

    public MovieDAO_DB(){
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Return a list of Movie objects from the database.
     * @return A list of all Movies.
     * @throws Exception throws exception if it fails to return a list of Movie objects.
     */
    public List<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();

        try(Connection connection = databaseConnector.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM Movie;";

            ResultSet rs = statement.executeQuery(sql);

            // Loop through rows from database result set
            while(rs.next()){
                //map database row to object
                int movieId = rs.getInt("Id");
                int movieRating = rs.getInt("Rating");
                String filePath = rs.getString("MoviePath");
                String title = rs.getString("MovieName");



                Movie movie = new Movie(movieId, movieRating, filePath, title);

                allMovies.add(movie);
            }

            return allMovies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve movies", e);
        }
    }
}
