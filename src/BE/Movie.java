package BE;

import java.util.List;

public class Movie {
private int id;
private double rating;
private double pRating;
private List<Category> categories;
private String filePath;
private String title;

    public Movie(int id, double rating, List<Category> categories, String filePath, String title) {
        this.id = id;
        this.rating = rating;
        this.categories = categories;
        this.filePath = filePath;
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getpRating() {
        return pRating;
    }

    public void setpRating(double pRating) {
        this.pRating = pRating;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
