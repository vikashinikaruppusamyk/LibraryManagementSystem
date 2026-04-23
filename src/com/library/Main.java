package com.library;

import com.library.factory.BookFactory;
import com.library.factory.PatronFactory;
import com.library.model.Book;
import com.library.model.Patron;
import com.library.observer.PatronNotificationObserver;
import com.library.repository.BookRepository;
import com.library.repository.PatronRepository;
import com.library.service.InventoryService;
import com.library.service.LendingService;

public class Main {
    public static void main(String[] args) {

        BookRepository bookRepo = new BookRepository();
        PatronRepository patronRepo = new PatronRepository();
        LendingService lendingService = new LendingService(bookRepo, patronRepo);
        InventoryService inventoryService = new InventoryService(bookRepo);

        // Using Factory instead of new Book() directly
        Book b1 = BookFactory.createBook("The Alchemist", "Paulo Coelho", "ISBN001", 1988);
        Book b2 = BookFactory.createBook("Atomic Habits", "James Clear", "ISBN002", 2018);
        bookRepo.addBook(b1);
        bookRepo.addBook(b2);

        // Using Factory instead of new Patron() directly
        Patron p1 = PatronFactory.createPatron("Vikashini", "vika@email.com");
        Patron p2 = PatronFactory.createPatron("Ananya", "ananya@email.com");
        patronRepo.addPatron(p1);
        patronRepo.addPatron(p2);

        // Ananya subscribes to The Alchemist (currently available, p1 will borrow it)
        PatronNotificationObserver ananyaObserver = new PatronNotificationObserver(p2.getName());
        b1.addObserver(ananyaObserver);

        System.out.println("\n--- Inventory Before Checkout ---");
        inventoryService.printInventorySummary();

        System.out.println("\n--- Vikashini checks out The Alchemist ---");
        lendingService.checkoutBook("ISBN001", p1.getPatronId());

        System.out.println("\n--- Inventory After Checkout ---");
        inventoryService.printInventorySummary();

        System.out.println("\n--- Try Borrowing Same Book Again ---");
        lendingService.checkoutBook("ISBN001", p1.getPatronId());

        System.out.println("\n--- Vikashini returns The Alchemist ---");
        lendingService.returnBook("ISBN001", p1.getPatronId());

        System.out.println("\n--- Inventory After Return ---");
        inventoryService.printInventorySummary();

        System.out.println("\n--- Vikashini's Borrow History ---");
        p1.getBorrowHistory().forEach(System.out::println);

        System.out.println("\n--- Auto-generated Patron IDs ---");
        System.out.println(p1.getPatronId() + " → " + p1.getName());
        System.out.println(p2.getPatronId() + " → " + p2.getName());
    }
}