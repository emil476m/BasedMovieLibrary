package BE;

public class Movie {
private int id;
private double rating;
private double pRating;
private String category;
private String filePath;
private String title;

    public Movie(int id, double rating, String category, String filePath, String title) {
        this.id = id;
        this.rating = rating;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
