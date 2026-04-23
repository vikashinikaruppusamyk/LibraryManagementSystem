package com.library;

import com.library.model.Book;
import com.library.model.Patron;
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

        Book b1 = new Book("The Alchemist", "Paulo Coelho", "ISBN001", 1988);
        Book b2 = new Book("Atomic Habits", "James Clear", "ISBN002", 2018);
        bookRepo.addBook(b1);
        bookRepo.addBook(b2);

        Patron p1 = new Patron("P001", "Vikashini", "vika@email.com");
        patronRepo.addPatron(p1);

        System.out.println("\n--- Inventory Before Checkout ---");
        inventoryService.printInventorySummary();

        System.out.println("\n--- Checkout ---");
        lendingService.checkoutBook("ISBN001", "P001");

        System.out.println("\n--- Inventory After Checkout ---");
        inventoryService.printInventorySummary();

        System.out.println("\n--- Try Borrowing Same Book Again ---");
        lendingService.checkoutBook("ISBN001", "P001");

        System.out.println("\n--- Return ---");
        lendingService.returnBook("ISBN001", "P001");

        System.out.println("\n--- Inventory After Return ---");
        inventoryService.printInventorySummary();

        System.out.println("\n--- Patron Borrow History ---");
        p1.getBorrowHistory().forEach(System.out::println);
    }
}