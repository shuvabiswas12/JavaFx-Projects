package sample.model;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class BookInformation {

    private SimpleStringProperty bookTitle;
    private SimpleStringProperty bookId;
    private SimpleStringProperty bookAuthor;
    private SimpleStringProperty bookPublisher;
    private SimpleBooleanProperty val;

    public BookInformation (String bookTitle, String bookId, String bookAuthor, String bookPublisher, boolean val) {
        this.bookTitle = new SimpleStringProperty(bookTitle);
        this.bookId = new SimpleStringProperty(bookId);
        this.bookAuthor = new SimpleStringProperty(bookAuthor);
        this.bookPublisher = new SimpleStringProperty(bookPublisher);
        this.val = new SimpleBooleanProperty(val);
    }


    public String getBookTitle() {
        return bookTitle.get();
    }

    public SimpleStringProperty bookTitleProperty() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle.set(bookTitle);
    }

    public String getBookId() {
        return bookId.get();
    }

    public SimpleStringProperty bookIdProperty() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId.set(bookId);
    }

    public String getBookAuthor() {
        return bookAuthor.get();
    }

    public SimpleStringProperty bookAuthorProperty() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor.set(bookAuthor);
    }

    public String getBookPublisher() {
        return bookPublisher.get();
    }

    public SimpleStringProperty bookPublisherProperty() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher.set(bookPublisher);
    }

    public boolean isVal() {
        return val.get();
    }

    public SimpleBooleanProperty valProperty() {
        return val;
    }

    public void setVal(boolean val) {
        this.val.set(val);
    }
}
