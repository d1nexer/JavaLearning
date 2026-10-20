package ru.edu.pr01;

import java.io.BufferedReader;
import java.io.FileReader;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = true;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/sample.csv"));
            reader.readLine();                          
            String line;
            int lineNumber = 1;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(";");
                int    quantity  = Integer.parseInt(parts[0].trim());
                double unitPrice = Double.parseDouble(parts[1].trim());
                double discount  = Double.parseDouble(parts[2].trim());

                boolean valid = OrderCalculator.isValid(quantity, unitPrice, discount);

                System.out.print("line " + lineNumber + ": " + line
                        + "  -  valid=" + valid);

                if (valid) {
                    double total = OrderCalculator.calculateTotal(quantity, unitPrice, discount, 10.0);
                    System.out.println(", total(НДС 10%)=" + total);
                } else {
                    System.out.println("  (невалидная строка)");
                }
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("FAIL exception " + e.getMessage());
            ok = false;
        }

        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}