package org.onlinebookstore.onlinebookstorebackend.controllers;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.IOException;
import org.onlinebookstore.onlinebookstorebackend.repository.BookRepository;

@RestController
public class FileUploadController {
    @Value("${upload.path}")
    private String uploadPath;

    @CrossOrigin
    @RequestMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
        }

        try {
            // Save the file with a temporary name
            String fileName = "temp.jpg";
            File dest = new File(uploadPath + fileName);
            file.transferTo(dest);
            return new ResponseEntity<>("File uploaded successfully: " + fileName, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}


