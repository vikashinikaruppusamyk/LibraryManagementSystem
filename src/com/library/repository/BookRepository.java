package com.library.repository;

import com.library.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ArrayList;
import java.util.List;

public class BookRepository implements BookRepositoryInterface {

    private static final Logger logger = LoggerFactory.getLogger(BookRepository.class);
    private List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        logger.info("Book added: {}", book.getTitle());
    }

    public void removeBook(String isbn) {
        books.removeIf(book -> book.getIsbn().equals(isbn));
        logger.info("Book removed with ISBN: {}", isbn);
    }

    public void updateBook(String isbn, String newTitle, String newAuthor) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                logger.info("Book updated: {}", isbn);
                return;
            }
        }
        logger.warn("Book not found for update: {}", isbn);
    }

    public Book findByIsbn(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) return book;
        }
        logger.warn("Book not found: {}", isbn);
        return null;
    }

    public List<Book> findByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> getAllBooks() {
        return books;
    }
}