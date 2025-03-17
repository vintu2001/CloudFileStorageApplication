package com.vineet.Projects.repository;

import com.vineet.Projects.model.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface FileMetadataRepository extends JpaRepository<FileMetadata, UUID> {
    Optional<FileMetadata> findByFileName(String fileName);
}
