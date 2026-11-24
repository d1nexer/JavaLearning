package ru.edu.pr08;

public class ClientCsvParser {

    private static final String DELIMITER = ";";
    private static final int EXPECTED_COLUMNS = 3;

    public ClientDraft parse(String line, int lineNumber) {
        if (line == null) {
            throw new IllegalArgumentException("Line " + lineNumber + ": null line");
        }
        String[] parts = line.split(DELIMITER, -1);
        if (parts.length != EXPECTED_COLUMNS) {
            throw new IllegalArgumentException(
                    "Line " + lineNumber + ": expected " + EXPECTED_COLUMNS + " columns, got " + parts.length
            );
        }
        String inn = parts[0].trim();
        String name = parts[1].trim();
        String email = parts[2].trim();
        return new ClientDraft(inn, name, email);
    }
}