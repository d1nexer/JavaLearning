package ru.edu.pr05;

import java.time.LocalDate;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = false;
        try { BusinessTrip trip = new BusinessTrip("T-01", "Иван Иванов", LocalDate.of(2026, 6, 1), LocalDate.of(2026, 6, 10));
            trip.submit();
            trip.approve();
            trip.complete(LocalDate.of(2026, 6, 11));
            ok = (trip.getStatus() == TripStatus.COMPLETED); } catch (Exception e) { System.out.println("FAIL exception " + e.getMessage()); }
        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}
