package com.library.repository;

import com.library.model.Book;
import java.util.List;

public interface BookRepositoryInterface {
    void addBook(Book book);
    void removeBook(String isbn);
    void updateBook(String isbn, String newTitle, String newAuthor);
    Book findByIsbn(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);
    List<Book> getAllBooks();
}