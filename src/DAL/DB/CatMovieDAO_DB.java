package DAL.DB;

import BE.CatMovie;
import BE.Category;
import BE.Movie;
import DAL.Interfaces.ICatMovieDAO;
import DAL.Util.LocalFileHandler;

import java.nio.file.Path;
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
            throw new Exception("Failed to retrieve catMovies", e);
        }
    }

    /**
     * Inserts a newly created movie relations into the database.
     * @param movie the created movie.
     * @throws Exception if it fails to create a movie.
     */
    public void createMovies(Movie movie) throws Exception{
        String sql = "INSERT INTO CatMovie (CategoryId, MovieId) VALUES (?,?) ;";
        String sqlTest = "";
        for (Category category: movie.getCategories()){
            sqlTest += "INSERT INTO CatMovie VALUES(" + category.getId() +", " + movie.getId() + ");";
        }
        try(Connection connection = databaseConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sqlTest)){



            //String title = movie.getTitle();
            //int movieId = movie.getId();;


            //statement.setString(1, title);
            //statement.setDouble(2, movieId);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create movie", e);
        }
    }
}
