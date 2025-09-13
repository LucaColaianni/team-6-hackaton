package it.idcert.wallet.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

public class FileService {
    private final Path storageLocation = Paths.get("uploads");

    public FileService() throws IOException {
        Files.createDirectories(storageLocation);
    }

    public String saveFile(MultipartFile file) throws IOException, IOException {
        String id = UUID.randomUUID().toString();
        Path target = storageLocation.resolve(id + "-" + file.getOriginalFilename());
        Files.copy(file.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING);
        return id;
    }

    public Resource getFile(String id) throws IOException {
        try (Stream<Path> files = Files.list(storageLocation)) {
            Optional<Path> match = files
                    .filter(path -> path.getFileName().toString().startsWith(id + "-"))
                    .findFirst();

            if (match.isEmpty()) {
                throw new FileNotFoundException("File con id " + id + " non trovato");
            }

            return new FileSystemResource(match.get());
        }
    }
}
