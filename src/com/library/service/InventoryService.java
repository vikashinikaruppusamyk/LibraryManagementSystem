package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private BookRepository bookRepository;

    public InventoryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAvailableBooks() {
        List<Book> available = new ArrayList<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (book.isAvailable()) {
                available.add(book);
            }
        }
        return available;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowed = new ArrayList<>();
        for (Book book : bookRepository.getAllBooks()) {
            if (!book.isAvailable()) {
                borrowed.add(book);
            }
        }
        return borrowed;
    }

    public void printInventorySummary() {
        System.out.println("Total books: " + bookRepository.getAllBooks().size());
        System.out.println("Available: " + getAvailableBooks().size());
        System.out.println("Borrowed: " + getBorrowedBooks().size());
    }
}