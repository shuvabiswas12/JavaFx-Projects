package sample.database;

import sample.model.BookInformation;
import sample.model.Issue;
import sample.model.Membar;


public class Const {

    public static final String BOOK_TABLE = "BOOK";
    public static final String ID = "id";
    public static final String TITLE = "title";
    public static final String AUTHOR = "author";
    public static final String PUBLISHER = "publisher";
    public static final String IS_AVAIL = "isAvail";



    public static final String MEMBAR_TABLE = "MEMBAR";
    public static final String NAME = "name";
    public static final String MOBILE = "mobile";
    public static final String EMAIL = "email";

    public static final String ISSUE_TABLE = "ISSUE";
    public static final String BOOK_ID = "bookId";
    public static final String MEMBAR_ID = "membarId";
    public static final String ISSUES_TIME = "issuesTime";
    public static final String RENEW_COUNT = "renew_count";



    public static final String CREATE_BOOK_TABLE = "CREATE TABLE "+BOOK_TABLE
            +" ( "
            + "   id varchar(200) primary key, \n"
            + "   title varchar(200), \n "
            + "   author varchar(200), \n "
            + "   publisher varchar(100), \n "
            + "   isAvail boolean default true \n "
            + " ) ";


    public static final String CREATE_MEMBAR_TABLE = "CREATE TABLE "+MEMBAR_TABLE
            +" ( "
            + "   id varchar(200) primary key, \n"
            + "   name varchar(200), \n "
            + "   mobile varchar(20), \n "
            + "   email varchar(100) \n "
            + " ) ";

    public static final String CREATE_ISSUE_TABLE = "CREATE TABLE "+ISSUE_TABLE
            +" ( "
            + "   bookId varchar(200) primary key, \n"
            + "   membarId varchar(200), \n "
            + "   issuesTime timestamp default CURRENT_TIMESTAMP, \n "
            + "   renew_count integer default 0, \n "
            + "   FOREIGN KEY (bookId) REFERENCES BOOK(id), \n "
            + "   FOREIGN KEY (membarId) REFERENCES MEMBAR(id) \n "
            + " ) ";


    public static String insertBook(BookInformation bookInformation) {
        String query = null;


        query = "INSERT INTO BOOK VALUES ( "
                +"'"+bookInformation.getBookId()+"', "
                +"'"+bookInformation.getBookTitle()+"', "
                +"'"+bookInformation.getBookAuthor()+"', "
                +"'"+bookInformation.getBookPublisher()+"', "
                +true+" ) ";

        return query;
    }



    public static String insertMembar(Membar membar) {
        String query = null;

        query = "INSERT INTO MEMBAR VALUES ( "
                +"'"+membar.getId()+"', "
                +"'"+membar.getName()+"', "
                +"'"+membar.getMobile()+"', "
                +"'"+membar.getEmail()+"' ) ";

        return query;
    }




    public static String insertIssue(Issue issue) {
        String query = null;

        query = "INSERT INTO ISSUE ("+BOOK_ID+", "+MEMBAR_ID +") VALUES ( "
                +"'"+issue.getBookId()+"', "
                +"'"+issue.getMemberId()+"' ) ";

        return query;
    }


    public static String updateBook(String id) {
        String query = null;

        query = "UPDATE "+ BOOK_TABLE +" SET "+IS_AVAIL+" = false WHERE "+ID+" = '"+id+"'";

        return query;

    }



}
