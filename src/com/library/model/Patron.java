package com.library.model;

import java.util.ArrayList;
import java.util.List;

public class Patron {

    private String patronId;
    private String name;
    private String email;
    private List<BorrowRecord> borrowHistory;

    public Patron(String patronId, String name, String email) {
        this.patronId = patronId;
        this.name = name;
        this.email = email;
        this.borrowHistory = new ArrayList<>();
    }

    public String getPatronId() { return patronId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public List<BorrowRecord> getBorrowHistory() { return borrowHistory; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }

    public void addBorrowRecord(BorrowRecord record) {
        borrowHistory.add(record);
    }

    public List<BorrowRecord> getActiveborrows() {
        List<BorrowRecord> active = new ArrayList<>();
        for (BorrowRecord record : borrowHistory) {
            if (!record.isReturned()) {
                active.add(record);
            }
        }
        return active;
    }

    @Override
    public String toString() {
        return "[" + patronId + "] " + name + " | Email: " + email +
                " | Active borrows: " + getActiveborrows().size();
    }
}