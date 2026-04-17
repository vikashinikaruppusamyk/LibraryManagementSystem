package com.library;

import com.library.model.Book;
import com.library.model.BorrowRecord;
import com.library.model.Patron;
import com.library.repository.BookRepository;
import com.library.repository.PatronRepository;

public class Main {
    public static void main(String[] args) {

        BookRepository bookRepo = new BookRepository();
        PatronRepository patronRepo = new PatronRepository();

        Book b1 = new Book("The Alchemist", "Paulo Coelho", "ISBN001", 1988);
        bookRepo.addBook(b1);

        Patron p1 = new Patron("P001", "Vikashini", "vika@email.com");
        patronRepo.addPatron(p1);

        BorrowRecord record = new BorrowRecord("ISBN001", "The Alchemist");
        p1.addBorrowRecord(record);

        System.out.println("\n--- Patron Info ---");
        System.out.println(p1);

        System.out.println("\n--- Borrow History ---");
        for (BorrowRecord r : p1.getBorrowHistory()) {
            System.out.println(r);
        }
    }
}