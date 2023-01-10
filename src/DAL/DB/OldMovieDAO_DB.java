package DAL.DB;

import BE.Movie;
import DAL.Interfaces.IOldMovieDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OldMovieDAO_DB implements IOldMovieDAO {

   private DatabaseConnector connector;

   public OldMovieDAO_DB() { connector = new DatabaseConnector(); }

    @Override
    public List<Movie> getAllMovies() throws Exception {
        ArrayList<Movie> allMovies = new ArrayList<>();

        try(Connection connection = connector.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT *\n" +
                    "FROM Movie\n" +
                    "Where DATEDIFF(HOUR, LastView, GETDATE()) > 17520\n" +
                    "And PersonalRating < 6;";

            ResultSet rs = statement.executeQuery(sql);

            // Loop through rows from database result set
            while(rs.next()){
                //map database row to object
                int movieId = rs.getInt("Id");
                double movieRating = rs.getDouble("Rating");
                double moviePRating = rs.getDouble("PersonalRating");
                String filePath = rs.getString("MoviePath");
                String title = rs.getString("MovieName");
                Date date = rs.getDate("LastView");

                Movie movie = new Movie(movieId, movieRating, filePath, title, moviePRating, date);

                allMovies.add(movie);
            }

            return allMovies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve movies", e);
        }
    }

    @Override
    public void deleteMovie(Movie movie) throws Exception {
        String sql = "DELETE FROM Movie WHERE Id = ?";

        try (Connection connection = connector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movie.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to delete movie");
        }
    }

    @Override
    public void deleteAllMovies() throws Exception {

    }
}
