package com.brianeno.upload.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileMetadataEntity {

    @Id
    private String id;

    private long size;

    private String originalName;

    private String httpContentType;

    private Timestamp dateUploaded;
}
