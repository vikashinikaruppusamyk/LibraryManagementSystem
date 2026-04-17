package com.library.repository;

import com.library.model.Patron;
import java.util.ArrayList;
import java.util.List;

public class PatronRepository {

    private List<Patron> patrons = new ArrayList<>();

    public void addPatron(Patron patron) {
        patrons.add(patron);
        System.out.println("Patron added: " + patron.getName());
    }

    public void updatePatron(String patronId, String newName, String newEmail) {
        for (Patron patron : patrons) {
            if (patron.getPatronId().equals(patronId)) {
                patron.setName(newName);
                patron.setEmail(newEmail);
                System.out.println("Patron updated: " + patronId);
                return;
            }
        }
        System.out.println("Patron not found: " + patronId);
    }

    public Patron findById(String patronId) {
        for (Patron patron : patrons) {
            if (patron.getPatronId().equals(patronId)) return patron;
        }
        return null;
    }

    public List<Patron> getAllPatrons() {
        return patrons;
    }
}