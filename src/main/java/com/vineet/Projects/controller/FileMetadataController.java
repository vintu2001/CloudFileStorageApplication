package com.vineet.Projects.controller;

import com.vineet.Projects.model.FileMetadata;
import com.vineet.Projects.service.FileMetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/files")
public class FileMetadataController {
    @Autowired
    FileMetadataService fileMetadataService;

    //Endpoint to save filemetadata

    @PostMapping
    public ResponseEntity<FileMetadata> saveMetadata(
            @RequestParam String fileName,
            @RequestParam String fileType,
            @RequestParam Long fileSize){
        FileMetadata savedMetadata = fileMetadataService.saveMetadata(fileName,fileType,fileSize);

        return ResponseEntity.ok(savedMetadata);
    }

    // Endpoint to retrieve all data

    @GetMapping
    public ResponseEntity<List<FileMetadata>> getAllMetadata(){
        List<FileMetadata> metadataList = fileMetadataService.getAllMetadata();
        return ResponseEntity.ok(metadataList);
    }

    // Endpoint to retrieve data for a specific id

    @GetMapping("/{id}")
    public ResponseEntity<FileMetadata> getMetadataById(@PathVariable Long id) {
        FileMetadata metadata = fileMetadataService.getMetadataById(id);

        if (metadata != null) {
            return ResponseEntity.ok(metadata);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
