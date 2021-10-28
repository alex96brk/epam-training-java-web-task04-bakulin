package by.epamtc.bakulin.model;

import java.io.Serializable;

public class Book implements Serializable, Comparable<Book> {
    private Long bookId;
    private String bookName;
    private String bookAuthor;
    private String bookGenre;

    public Book(String bookName, String bookAuthor, String bookGenre) {
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
    }

    public Book() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookGenre() {
        return bookGenre;
    }

    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookGenre='" + bookGenre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        boolean result = false;
        if (this == o) {
            result = true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            result = false;
        }
        Book book = (Book) o;
        return (bookName == book.bookName || (bookName != null && bookName.equals(book.getBookName()))) &&
                (bookAuthor == book.bookAuthor || (bookAuthor != null && bookAuthor.equals(book.getBookAuthor()))) &&
                (bookGenre == book.bookGenre || (bookGenre != null && bookGenre.equals(book.getBookGenre())));
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (bookName == null ? 0 : bookName.hashCode());
        result = 31 * result + (bookAuthor == null ? 0 : bookAuthor.hashCode());
        result = 31 * result + (bookGenre == null ? 0 : bookGenre.hashCode());
        return result;
    }

    @Override
    public int compareTo(Book o) {
        int result = this.bookName.compareTo(o.getBookName());
        if (result == 0) {
            result = this.bookGenre.compareTo(o.getBookGenre());
        }
        return result;
    }
}
