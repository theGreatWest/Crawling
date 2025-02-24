package kr.book.search.model;

public class Book {
    private String title;
    private String authors;
    private String publisher;
    private String thumbnail;

    public Book() {
    }

    public Book(String title, String authors, String publisher, String thumbnail) {
        this.title = title;
        this.authors = authors;
        this.publisher = publisher;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", publisher='" + publisher + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}
