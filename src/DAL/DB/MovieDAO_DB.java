package DAL.DB;

import BE.Movie;
import DAL.Interfaces.IMovieDAO;
import DAL.Util.LocalFileHandler;

import java.nio.file.Path;
import java.sql.*;
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
                // Map database row to object
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

    /**
     * Inserts a newly created movie into the database and returns the movie with its new id.
     * @param movie The movie to create.
     * @return The created movie with its new id.
     * @throws Exception if it fails to create the movie.
     */
    @Override
    public Movie createMovie(Movie movie) throws Exception {
        String sql = "INSERT INTO Movie (MovieName, Rating, MoviePath) VALUES (?,?,?);";

        try(Connection connection = databaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            Path relativePath = LocalFileHandler.createLocalFile(movie.getFilePath());

            String title = movie.getTitle();
            double rating = movie.getRating();
            String path = String.valueOf(relativePath);

            statement.setString(1, title);
            statement.setDouble(2, rating);
            statement.setString(3, path);

            statement.executeUpdate();

            Movie generatedMovie = null;

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                generatedMovie = new Movie(id, rating, movie.getCategories(), path, title);
            }

            return generatedMovie;

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create movie", e);
        }
    }

    /**
     * Deletes a movie from the database.
     * @param movie The movie to delete.
     * @throws Exception If it fails to delete the movie.
     */
    @Override
    public void deleteMovie(Movie movie) throws Exception {
        String sql = "DELETE FROM Movie WHERE Id = ?;";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movie.getId());

            statement.executeUpdate();

            LocalFileHandler.deleteLocalFile(movie.getFilePath());
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to delete movie");
        }
    }

    /**
     * Edits the personal rating of a movie.
     * @param movie The movie with its new personal rating.
     * @throws Exception if it fails to edit the personal rating.
     */
    @Override
    public void editPRating(Movie movie) throws Exception {
        String sql = "UPDATE Movie SET PersonalRating = ? WHERE Id = ?;";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, movie.getPRating());
            statement.setInt(2, movie.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to update personal rating", e);
        }
    }

    /**
     * Returns a list of movie objects from the database that have not been opened in more than 17520 hours.
     * @return A list of movies that have not been opened in more than 17520 hours
     * @throws Exception If it fails to retrieve the movies.
     */
    public List<Movie> getAllOldMovies() throws Exception {
        ArrayList<Movie> allOldMovies = new ArrayList<>();

        try(Connection connection = databaseConnector.getConnection();
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

                allOldMovies.add(movie);
            }

            return allOldMovies;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve old movies", e);
        }
    }

    /**
     * Deletes all given movies.
     * @param movies The movies to delete.
     * @throws Exception If it fails to delete the movies.
     */
    @Override
    public void deleteMovies(List<Movie> movies) throws Exception {
        String sql = "DELETE FROM Movie WHERE id= ?";

        try(Connection connection = databaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Movie m: movies) {
                statement.setInt(1,m.getId());

                statement.executeUpdate();

                LocalFileHandler.deleteLocalFile(m.getFilePath());
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Failed to delete all old movies", ex);
        }
    }

    /**
     * Updates the last time a movie has been viewed.
     * @param movie The movie to update.
     * @throws Exception If it fails to update the movie.
     */
    @Override
    public void updateLastViewed(Movie movie) throws Exception {
        String sql = "UPDATE Movie SET LastView = GETDATE() WHERE Id = ?;";

        try(Connection connection = databaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movie.getId());

            statement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            throw new Exception("Could not update lastView");
        }
    }
}