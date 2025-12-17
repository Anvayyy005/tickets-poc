package org.example.service;

import java.nio.file.Files;
import java.nio.file.Path;

public class FileWriterService {

    public void write(String fileName, String content) throws Exception {
        Files.writeString(Path.of(fileName), content);
    }
}
