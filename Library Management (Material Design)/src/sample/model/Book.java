package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class Book {

    private SimpleStringProperty bookTitle;
    private SimpleStringProperty bookId;
    private SimpleStringProperty bookPublisher;
    private SimpleStringProperty bookAuthor;
    private SimpleBooleanProperty isAvail;


    public Book (String bookId, String bookTitle, String bookAuthor, String bookPublisher, boolean isAvail) {
        this.bookId = new SimpleStringProperty(bookId);
        this.bookAuthor = new SimpleStringProperty(bookAuthor);
        this.bookPublisher = new SimpleStringProperty(bookPublisher);
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.isAvail = new SimpleBooleanProperty(isAvail);
    }




    /** getter method --> */

    public String getBookId() {
        return bookId.get();
    }

    public String getBookTitle() {
        return bookTitle.get();
    }

    public String getBookPublisher() {
        return bookPublisher.get();
    }

    public String getBookAuthor() {
        return bookAuthor.get();
    }

    public boolean getIsAvail() {
        return isAvail.get();
    }




    /** setter method --> */

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher.set(bookPublisher);
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor.set(bookAuthor);
    }

    public void setIsAvail(boolean isAvail) {
        this.isAvail.set(isAvail);
    }




    /** String property --> */

    public SimpleStringProperty bookAuthorProperty() {
        return bookAuthor;
    }

    public SimpleStringProperty bookIdProperty() {
        return bookId;
    }

    public SimpleStringProperty bookPublisherProperty() {
        return bookPublisher;
    }

    public SimpleStringProperty bookTitleProperty() {
        return bookTitle;
    }

    public SimpleBooleanProperty isAvailProperty() {
        return isAvail;
    }
}
