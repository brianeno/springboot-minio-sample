package com.brianeno.upload.controller;

import com.brianeno.upload.entity.FileMetadataEntity;
import com.brianeno.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpHeaders.CONTENT_LENGTH;
import static org.springframework.http.HttpHeaders.CONTENT_RANGE;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    @Value("${upload.default-chunk-size}")
    public Integer defaultChunkSize;

    @PostMapping
    public ResponseEntity<UUID> saveObject(@RequestParam("file") MultipartFile file) {
        UUID fileUuid = uploadService.save(file);
        return ResponseEntity.ok(fileUuid);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<byte[]> fetchObject(
        @PathVariable UUID uuid) {

        UploadService.ContentWithMetadata contentWithMetadata = uploadService.fetchFile(uuid);
        return ResponseEntity.status(HttpStatus.OK)
            .header(CONTENT_TYPE, contentWithMetadata.metadata().getHttpContentType())
            .header(CONTENT_LENGTH, String.valueOf(contentWithMetadata.metadata().getSize()))
            .body(contentWithMetadata.chunk());
    }

    @GetMapping("/metadata")
    public ResponseEntity<List<FileMetadataEntity>> fetchMetadata() {

        List<FileMetadataEntity> metadata = uploadService.getFileMetadata();
        return ResponseEntity.status(HttpStatus.OK)
            .body(metadata);
    }
}
