package BE;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Movie {
private int id;
private double rating;
private double pRating;
private List<Category> categories;
private String filePath;
private String title;
private Date lastViewed;

    public Movie(int id, double rating, List<Category> categories, String filePath, String title) {
        this.id = id;
        this.rating = rating;
        this.categories = categories;
        this.filePath = filePath;
        this.title = title;
    }

    public Movie(double rating, List<Category> categories, String filePath, String title) {
        this.rating = rating;
        this.categories = categories;
        this.filePath = filePath;
        this.title = title;
    }

    public Movie(int id, double rating, String filePath, String title, double pRating, Date lastViewed) {
        this.id = id;
        this.rating = rating;
        this.filePath = filePath;
        this.title = title;
        categories = new ArrayList<>();
        this.pRating = pRating;
        this.lastViewed = lastViewed;
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

    public double getPRating() {
        return pRating;
    }

    public void setPRating(double pRating) {
        this.pRating = pRating;
    }

    public List<Category> getCategories() {
        return categories;
    }
    public String getCategoryNames(){
        if (!categories.isEmpty()) {
            String allCatNames = "";
            for (int i = 0; i < categories.size() - 1; i++) {
                allCatNames += categories.get(i).getName() + ", ";
            }
            allCatNames += categories.get(categories.size() - 1);
            return allCatNames;
        }
        return null;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
    }

    public void removeCategory(Category category) { this.categories.remove(category); }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Date getLastViewed() {
        return lastViewed;
    }

    public void setLastViewed(Date lastViewed) {
        this.lastViewed = lastViewed;
    }
}
