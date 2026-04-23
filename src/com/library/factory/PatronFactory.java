package com.library.factory;

import com.library.model.Patron;

public class PatronFactory {

    private static int counter = 1;

    public static Patron createPatron(String name, String email) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Patron name cannot be empty");
        }
        String patronId = "P" + String.format("%03d", counter++);
        return new Patron(patronId, name, email);
    }
}