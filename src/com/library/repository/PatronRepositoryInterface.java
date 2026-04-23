package com.library.repository;

import com.library.model.Patron;
import java.util.List;

public interface PatronRepositoryInterface {
    void addPatron(Patron patron);
    void updatePatron(String patronId, String newName, String newEmail);
    Patron findById(String patronId);
    List<Patron> getAllPatrons();
}