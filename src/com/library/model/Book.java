package com.library.model;
import com.library.observer.BookObserver;
import java.util.ArrayList;
import java.util.List;

public class Book {

    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private boolean isAvailable;

    public Book(String title, String author, String isbn, int publicationYear) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return isAvailable; }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
        if (available) {
            notifyObservers();
        }
    }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }

    @Override
    public String toString() {
        return "[" + isbn + "] " + title + " by " + author + " (" + publicationYear + ") - " + (isAvailable ? "Available" : "Borrowed");
    }

    private List<BookObserver> observers = new ArrayList<>();

    public void addObserver(BookObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(BookObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (BookObserver observer : observers) {
            observer.onBookAvailable(this.isbn, this.title);
        }
    }
}