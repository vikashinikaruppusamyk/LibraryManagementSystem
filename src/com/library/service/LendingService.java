package com.library.service;

import com.library.model.Book;
import com.library.model.BorrowRecord;
import com.library.model.Patron;
import com.library.repository.BookRepositoryInterface;
import com.library.repository.PatronRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;

public class LendingService {

    private static final Logger logger = LoggerFactory.getLogger(LendingService.class);
    private BookRepositoryInterface bookRepository;
    private PatronRepositoryInterface patronRepository;

    public LendingService(BookRepositoryInterface bookRepository,
                          PatronRepositoryInterface patronRepository) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
    }

    public void checkoutBook(String isbn, String patronId) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            logger.error("Checkout failed - book not found: {}", isbn);
            return;
        }
        if (!book.isAvailable()) {
            logger.warn("Checkout failed - book already borrowed: {}", book.getTitle());
            return;
        }
        Patron patron = patronRepository.findById(patronId);
        if (patron == null) {
            logger.error("Checkout failed - patron not found: {}", patronId);
            return;
        }
        book.setAvailable(false);
        BorrowRecord record = new BorrowRecord(isbn, book.getTitle());
        patron.addBorrowRecord(record);
        logger.info("Checked out: {} → {}", book.getTitle(), patron.getName());
    }

    public void returnBook(String isbn, String patronId) {
        Book book = bookRepository.findByIsbn(isbn);
        if (book == null) {
            logger.error("Return failed - book not found: {}", isbn);
            return;
        }
        Patron patron = patronRepository.findById(patronId);
        if (patron == null) {
            logger.error("Return failed - patron not found: {}", patronId);
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
            logger.warn("Return failed - no active borrow found for ISBN: {}", isbn);
            return;
        }
        activeRecord.setReturnDate(LocalDate.now());
        book.setAvailable(true);
        logger.info("Returned: {} ← {}", book.getTitle(), patron.getName());
    }
}