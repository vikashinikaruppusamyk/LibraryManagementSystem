package com.library.service;

import com.library.model.Book;
import com.library.model.BorrowRecord;
import com.library.model.Patron;
import com.library.repository.BookRepository;
import com.library.repository.PatronRepository;

import java.time.LocalDate;

public class LendingService {

    private BookRepository bookRepository;
    private PatronRepository patronRepository;

    public LendingService(BookRepository bookRepository, PatronRepository patronRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public void checkoutBook(String isbn, String patronId) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found: " + isbn);
            return;
        }
        if (!book.isAvailable()) {
            System.out.println("Book is already borrowed: " + book.getTitle());
            return;
        }

        Patron patron = patronRepository.findById(patronId);
        if (patron == null) {
            System.out.println("Patron not found: " + patronId);
            return;
        }

        book.setAvailable(false);
        BorrowRecord record = new BorrowRecord(isbn, book.getTitle());
        patron.addBorrowRecord(record);
        System.out.println("Checked out: " + book.getTitle() + " → " + patron.getName());
    }

    public void returnBook(String isbn, String patronId) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            System.out.println("Book not found: " + isbn);
            return;
        }

        Patron patron = patronRepository.findById(patronId);
        if (patron == null) {
            System.out.println("Patron not found: " + patronId);
            return;
        }

        BorrowRecord activeRecord = null;
        for (BorrowRecord record : patron.getBorrowHistory()) {
            if (record.getIsbn().equals(isbn) && !record.isReturned()) {
                activeRecord = record;
                break;
            }
        }

        if (activeRecord == null) {
            System.out.println("No active borrow found for this book and patron.");
            return;
        }

        activeRecord.setReturnDate(LocalDate.now());
        book.setAvailable(true);
        System.out.println("Returned: " + book.getTitle() + " ← " + patron.getName());
    }
}