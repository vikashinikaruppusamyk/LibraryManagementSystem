package com.library.observer;

public interface BookObserver {
    void onBookAvailable(String isbn, String bookTitle);
}