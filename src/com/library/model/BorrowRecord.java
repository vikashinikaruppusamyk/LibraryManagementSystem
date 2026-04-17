package com.library.model;

import java.time.LocalDate;

public class BorrowRecord {

    private String isbn;
    private String bookTitle;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public BorrowRecord(String isbn, String bookTitle) {
        this.isbn = isbn;
        this.bookTitle = bookTitle;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
    }

    public String getIsbn() { return isbn; }
    public String getBookTitle() { return bookTitle; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getReturnDate() { return returnDate; }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public boolean isReturned() {
        return returnDate != null;
    }

    @Override
    public String toString() {
        return bookTitle + " (ISBN: " + isbn + ") | Borrowed: " + borrowDate +
                " | Returned: " + (isReturned() ? returnDate : "Not yet");
    }
}