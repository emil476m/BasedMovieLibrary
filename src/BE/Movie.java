package BE;

import java.util.ArrayList;
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
    public Movie(int id, double rating, String filePath, String title) {
        this.id = id;
        this.rating = rating;
        this.filePath = filePath;
        this.title = title;
        categories = new ArrayList<>();
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
        String allCatNames = "";
        System.out.println("test");
        System.out.println(categories.get(0).getName());
        for (Category c : categories){

           allCatNames += c.getName() +", ";
        }
        return allCatNames;
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
