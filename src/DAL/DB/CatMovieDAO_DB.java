package DAL.DB;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import DAL.Interfaces.ICatMovieDAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CatMovieDAO_DB implements ICatMovieDAO {
    private DatabaseConnector databaseConnector;

    public CatMovieDAO_DB(){
        databaseConnector = new DatabaseConnector();
    }

    /**
     * Return a list of CatMovie objects from the database.
     * @return A list of all CatMovies.
     * @throws Exception throws exception if it fails to return a list of CatMovie objects.
     */
    public List<CatMovie> getAllCatMovies() throws Exception {
        ArrayList<CatMovie> allCatMovies = new ArrayList<>();

        try(Connection connection = databaseConnector.getConnection();
            Statement statement = connection.createStatement()) {
            String sql = "SELECT * FROM CatMovie;";

            ResultSet rs = statement.executeQuery(sql);

            // Loop through rows from database result set
            while(rs.next()){
                //map database row to object
                int categoryId = rs.getInt("CategoryId");
                int movieId = rs.getInt("MovieId");

                CatMovie catMovie = new CatMovie(categoryId, movieId);

                allCatMovies.add(catMovie);
            }

            return allCatMovies;
        } catch (SQLException e) {
            e.printStackTrace();
            /*
            Implicit coupling? We're doing this because we don't
            think there are any scenarios where this method would be called,
            without the user trying to retrieve a movie.
            */
            throw new Exception("Failed to retrieve categories of movie", e);
        }
    }

    /**
     * Creates a movie's category relations.
     * @param movie the created movie.
     * @throws Exception if it fails to create the movie's relations.
     */
    public void createMovieRelations(Movie movie) throws Exception{
        String sql = "INSERT INTO CatMovie (CategoryId, MovieId) VALUES (?,?);";

        try(Connection connection = databaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)){

            for (Category category : movie.getCategories()) {
                statement.setInt(1, category.getId());
                statement.setInt(2, movie.getId());

                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            /*
            Implicit coupling? We're doing this because we don't
            think there are any scenarios where this method would be called,
            without the user trying to create a movie.
            */
            throw new Exception("Failed to create movie", e);
        }
    }

    /**
     * Deletes all the relations of a category.
     * @param category The category to delete the relations of.
     * @throws Exception If it fails to delete the relations.
     */
    @Override
    public void deleteWhereCat(Category category) throws Exception {
        String sql = "DELETE FROM CatMovie WHERE CategoryId = ?;";

        try (Connection connection = databaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, category.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            /*
            Implicit coupling? We're doing this because we don't
            think there are any scenarios where this method would be called,
            without the user trying to delete a category.
            */
            throw new Exception("Failed to delete category", e);
        }
    }

    /**
     * Deletes all the relations of a movie.
     * @param movie The movie to delete the relations of.
     * @throws Exception If it fails to delete the relations.
     */
    @Override
    public void deleteWhereMovie(Movie movie) throws Exception {
        String sql = "DELETE FROM CatMovie WHERE MovieId = ?;";

        try (Connection connection = databaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movie.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            /*
            Implicit coupling? We're doing this because we don't
            think there are any scenarios where this method would be called,
            without the user trying to delete a movie.
            */
            throw new Exception("Failed to delete movie");
        }
    }

    /**
     * Deletes all the category relations of given movies.
     * @param movies The movies to delete the relations of.
     * @throws Exception If it fails to delete the relations.
     */
    @Override
    public void deleteMoviesRelations(List<Movie> movies) throws Exception {
        String sql = "DELETE FROM CatMovie WHERE MovieId= ?";

        try(Connection connection = databaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            for (Movie m: movies) {
                statement.setInt(1, m.getId());

                statement.executeUpdate();
            }
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            /*
            Implicit coupling? We're doing this because we don't
            think there are any scenarios where this method would be called,
            without the user trying to delete a list of movies.
            */
            throw new Exception("Failed to delete movies", ex);
        }
    }
}
