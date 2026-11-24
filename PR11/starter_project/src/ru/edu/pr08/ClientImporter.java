package ru.edu.pr08;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class ClientImporter {

    private final ClientCsvParser parser = new ClientCsvParser();
    private final ClientValidator validator = new ClientValidator();
    private final ClientRepository repository;

    public ClientImporter(ClientRepository r) {
        this.repository = Objects.requireNonNull(r, "Repository must not be null");
    }

    public ImportResult importFile(Path path) throws IOException {
        Objects.requireNonNull(path, "Path must not be null");
        ImportResult result = new ImportResult();

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;
            boolean isHeader = true;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (isHeader) {
                    isHeader = false;
                    continue;
                }

                try {
                    ClientDraft draft = parser.parse(line, lineNumber);
                    List<String> validationErrors = validator.validate(draft);
                    if (!validationErrors.isEmpty()) {
                        result.addError(new ImportError(lineNumber, String.join("; ", validationErrors)));
                        continue;
                    }

                    Client client = new Client(draft.inn(), draft.name(), draft.email());
                    repository.save(client);
                    result.addAccepted();
                } catch (IllegalArgumentException e) {
                    result.addError(new ImportError(lineNumber, e.getMessage()));
                }
            }
        }

        return result;
    }
}