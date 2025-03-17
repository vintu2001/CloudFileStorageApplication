package com.vineet.Projects.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "file_metadata")
public class FileMetadata {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String fileType;

    @Column(nullable = false)
    private long fileSize;

    @Column(nullable = false)
    private LocalDateTime uploadTime;

    private String storageLocation;

    public FileMetadata(String fileName, String fileType, long fileSize, String storageLocation){
        this.fileName = fileName;
        this.fileType = fileType;
        this.fileSize = fileSize;
        this.storageLocation = storageLocation;
        this.uploadTime = LocalDateTime.now();
    }

    public FileMetadata() {}
}
