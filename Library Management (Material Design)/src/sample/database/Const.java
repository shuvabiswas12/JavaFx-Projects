package sample.database;

public class Const {

    /** table name --> */

    // Book Table
    public static final String BOOK_TABLE = "";
    public static final String ID = "";
    public static final String TITLE = "";
    public static final String AUTHOR = "";
    public static final String PUBLISHER = "";
    public static final String IS_AVAIL = "";


    // Member Table
    public static final String MEMBER_TABLE = "";
    public static final String NAME = "";
    public static final String MOBILE = "";
    public static final String EMAIL = "";


    // Issues Table
    public static final String ISSUE_TABLE = "";
    public static final String BOOK_ID = "";
    public static final String MEMBER_ID = "";
    public static final String ISSUES_TIME = "";
    public static final String RENEW_COUNT = "";




    /** database query --> */

    // create book table
    public static final String CREATE_BOOK_TABLE = " CREATE TABLE " + BOOK_TABLE +
            "( "
            + ID + " VARCHAR (200) PRIMARY KEY, \n"
            + TITLE + " VARCHAR (200), \n"
            + AUTHOR + " VARCHAR (200), \n"
            + PUBLISHER + " VARCHAR (100), \n"
            + IS_AVAIL + " boolean default true \n"
            +" )" ;

    // create member table
    public static final String CREATE_MEMBER_TABLE = "CREATE TABLE " + MEMBER_TABLE +
            "( "
            + ID + " VARCHAR (200) PRIMARY KEY, \n"
            + NAME + " VARCHAR (200), \n"
            + MOBILE + " VARCHAR (100), \n"
            + EMAIL + " VARCHAR (100), \n"
            + " )" ;

    // create issues table
    public static final String CREATE_ISSUES_TABLE = "CREATE TABLE " + ISSUE_TABLE +
            "( "
            + BOOK_ID + " VARCHAR (200) PRIMARY KEY, \n"
            + MEMBER_ID + " VARCHAR (200), \n"
            + ISSUES_TIME + " VARCHAR (200) timestamp default CURRENT_TIMESTAMP, \n"
            + RENEW_COUNT + " VARCHAR (200) integer default 0, \n"
            + " FOREIGN KEY ( " + BOOK_ID + " ) REFERENCES " + BOOK_TABLE + " ( " + ID + " ), \n"
            + " FOREIGN KEY ( " + MEMBER_ID + " ) REFERENCES " + MEMBER_TABLE + " ( " + MEMBER_ID + " ), \n"
            + " )" ;





}
