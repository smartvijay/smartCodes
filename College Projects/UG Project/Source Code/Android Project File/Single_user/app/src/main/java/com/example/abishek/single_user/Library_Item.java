package com.example.abishek.single_user;

/**
 * Created by Abishek on 3/2/2018.
 */

public class Library_Item {

    private String Booktitle;
    private int BookAccount;
    private String BookResource;
    private String BookIssueDate;
    private String BookDueDate;
    private String BookReturnDate;
    private String BookStatus;

    public Library_Item(String booktitle, int bookAccount, String bookResource, String bookIssueDate, String bookDueDate, String bookReturnDate, String bookStatus)
        {
            Booktitle = booktitle;
            BookAccount = bookAccount;
            BookResource = bookResource;
            BookIssueDate = bookIssueDate;
            BookDueDate = bookDueDate;
            BookReturnDate = bookReturnDate;
            BookStatus = bookStatus;

    }

    public String getBooktitle() {
        return Booktitle;
    }

    public int getBookAccount() {
        return BookAccount;
    }

    public String getBookResource() {
        return BookResource;
    }

    public String getBookIssueDate() {
        return BookIssueDate;
    }

    public String getBookDueDate() {
        return BookDueDate;
    }

    public String getBookReturnDate() {
        return BookReturnDate;
    }

    public String getBookStatus() {
        return BookStatus;
    }



}

