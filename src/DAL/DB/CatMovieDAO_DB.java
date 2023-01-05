package DAL.DB;

import BE.CatMovie;
import BE.Movie;
import DAL.Interfaces.ICatMovieDAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
}
