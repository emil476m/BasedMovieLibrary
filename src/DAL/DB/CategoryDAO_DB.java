package DAL.DB;

import BE.Category;
import DAL.Interfaces.ICategoryDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO_DB implements ICategoryDAO {
    private DatabaseConnector dbConnector;

    // Column indexes in the Category table.
    private int idIndex = 1;
    private int categoryNameIndex = 2;

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
                newCategory = new Category(resultSet.getInt(idIndex), category.getName());
            }

            return newCategory;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to create category", e);
        }
    }

    /**
     * Retrieves all categories in the database.
     * @return A list of all categories.
     * @throws Exception If it fails to retrieve the categories.
     */
    @Override
    public List<Category> getAllCategories() throws Exception {
        String sql = "SELECT * FROM Category";

        try (Connection connection = dbConnector.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            List<Category> categories = new ArrayList<>();

            while (resultSet.next()) {
                int id = resultSet.getInt(idIndex);
                String categoryName = resultSet.getString(categoryNameIndex);

                categories.add(new Category(id, categoryName));
            }

            return categories;
        }
        catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("Failed to retrieve categories", e);
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
