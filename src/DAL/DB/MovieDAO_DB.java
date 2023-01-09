package DAL.DB;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import DAL.Interfaces.ICatMovieDAO;
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

    /**
     * Inserts a newly created movie into the database and returns the movies' id.
     * @param movie the created movie.
     * @return movie with id.
     * @throws Exception if it fails to create a movie.
     */
    @Override
    public Movie createMovie(Movie movie) throws Exception {

        String sql = "INSERT INTO Movie (MovieName, Rating, MoviePath) VALUES (?,?,?) ;";

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
     * It edits the personal rating of the movies in the database, that matches the id of the Movie object.
     * @param movie last selected movie.
     * @throws Exception if it fails to edit the database.
     */
    @Override
    public void editPRating(Movie movie) throws Exception {
        String sql = "UPDATE Movie SET PersonalRating = ? WHERE Id = ?;";
        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setDouble(1, movie.getPRating());
            statement.setInt(2, movie.getId());

            //Run the specified SQL Statement
            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to update Personal Rating", e);
        }
    }
}