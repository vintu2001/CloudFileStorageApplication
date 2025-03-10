package com.vineet.Projects.service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class GoogleCloudStorageService {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    public GoogleCloudStorageService() {
        this.storage = StorageOptions.getDefaultInstance().getService();
    }

    public String uploadFile(String filePath) throws Exception {
        Bucket bucket = storage.get(bucketName);
        String fileName = Paths.get(filePath).getFileName().toString();

        try (InputStream inputStream = Files.newInputStream(Paths.get(filePath))) {
            Blob blob = bucket.create(fileName, inputStream);
            return blob.getMediaLink(); // Returns the file URL
        }
    }
}
