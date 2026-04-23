package com.library.factory;

import com.library.model.Book;

public class BookFactory {

    public static Book createBook(String title, String author,
                                  String isbn, int publicationYear) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be empty");
        }
        if (isbn == null || isbn.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        return new Book(title, author, isbn, publicationYear);
    }
}