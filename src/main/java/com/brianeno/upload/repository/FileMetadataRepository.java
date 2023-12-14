package com.brianeno.upload.repository;

import com.brianeno.upload.entity.FileMetadataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadataEntity, String> {
}
