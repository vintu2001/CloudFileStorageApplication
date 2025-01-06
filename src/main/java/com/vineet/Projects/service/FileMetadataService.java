package com.vineet.Projects.service;

import com.vineet.Projects.model.FileMetadata;
import com.vineet.Projects.repository.FileMetadataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileMetadataService {

    @Autowired
    private FileMetadataRepository fileMetadataRepository;

    //saving file metadata
    public FileMetadata saveMetadata(String fileName, String fileType, Long fileSize){
        FileMetadata metadata = new FileMetadata();
        metadata.setFileName(fileName);
        metadata.setFileType(fileType);
        metadata.setFileSize(fileSize);
        return fileMetadataRepository.save(metadata);
    }

    // Retrieve all metadata
    public List<FileMetadata> getAllMetadata() {
        return fileMetadataRepository.findAll();
    }

    // Retrieve metadata by ID
    public FileMetadata getMetadataById(Long id) {
        return fileMetadataRepository.findById(id).orElse(null);
    }
}
