package DAL.DB;

import BE.Category;
import DAL.Interfaces.ICategoryDAO;

import java.sql.*;

public class CategoryDAO_DB implements ICategoryDAO {
    private DatabaseConnector dbConnector;

    public CategoryDAO_DB() {
        dbConnector = new DatabaseConnector();
    }

    /**
     * Adds a new category to the database,
     * and returns the newly created category.
     * @param category The category to add.
     * @return The newly created category from the database.
     * @throws Exception If it fails to add the category.
     */
    @Override
    public Category addCategory(Category category) throws Exception {
        String sql = "INSERT INTO Category (CategoryName) VALUES (?)";
        Category newCategory = null;

        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());

            statement.executeUpdate();

            ResultSet resultSet = statement.getGeneratedKeys();

            if (resultSet.next()) {
                newCategory = new Category(resultSet.getInt(1), category.getName());
            }

            return newCategory;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create category", e);
        }
    }

    /**
     * Deletes a category from the database.
     * @param category The category to delete.
     * @throws Exception If it fails to delete the category.
     */
    @Override
    public void deleteCategory(Category category) throws Exception {
        String sql = "DELETE FROM Category WHERE Id = ?";

        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, category.getId());

            statement.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to remove category", e);
        }
    }
}
