package com.library.observer;

public class PatronNotificationObserver implements BookObserver {

    private String patronName;

    public PatronNotificationObserver(String patronName) {
        this.patronName = patronName;
    }

    @Override
    public void onBookAvailable(String isbn, String bookTitle) {
        System.out.println("Hey " + patronName + "! '" + bookTitle +
                "' (ISBN: " + isbn + ") is now available for borrowing.");
    }
}