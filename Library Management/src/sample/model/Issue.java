package sample.model;

public class Issue {

    private String bookId;
    private String memberId;

    public Issue(String bookId, String memberId) {
        this.bookId = bookId;
        this.memberId = memberId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }
}
