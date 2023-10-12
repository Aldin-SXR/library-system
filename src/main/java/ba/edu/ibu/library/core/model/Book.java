package ba.edu.ibu.library.core.model;

public class Book {
    private int id;
    private String title;
    private String genre;
    private int yearOfPublishing;

    public Book(int id, String title, String genre, int yearOfPublishing) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.yearOfPublishing = yearOfPublishing;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYearOfPublishing() {
        return yearOfPublishing;
    }

    public void setYearOfPublishing(int yearOfPublishing) {
        this.yearOfPublishing = yearOfPublishing;
    }
}
