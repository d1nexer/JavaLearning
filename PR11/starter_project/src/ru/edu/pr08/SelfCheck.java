package ru.edu.pr08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SelfCheck {
    public static void main(String[] args) {
        boolean ok = false;
        Path tempFile = null;
        try {
            tempFile = Files.createTempFile("clients_test_", ".csv");
            Files.write(tempFile, List.of(
                    "inn;name;email",
                    "1234567890;Ivanov;ivan@example.com",
                    "123;TooLongClientNameExceedingLimit;bad-email",
                    "123456789012;Petrov;petr@domain.ru"
            ));

            ClientRepository repo = new ClientRepository();
            ClientImporter importer = new ClientImporter(repo);
            ImportResult res = importer.importFile(tempFile);

            boolean missingFileThrown = false;
            try {
                importer.importFile(Path.of("non_existent_file_12345.csv"));
            } catch (IOException expected) {
                missingFileThrown = true;
            }

            ok = (res.accepted() == 2)
                    && (res.errors().size() == 1)
                    && (res.errors().get(0).lineNumber() == 3)
                    && (repo.findByInn("1234567890") != null)
                    && missingFileThrown;
        } catch (Exception e) {
            System.out.println("FAIL exception " + e.getMessage());
        } finally {
            if (tempFile != null) {
                try {
                    Files.deleteIfExists(tempFile);
                } catch (IOException ignored) {
                }
            }
        }
        System.out.println(ok ? "PASS" : "FAIL  complete TODO methods");
    }
}