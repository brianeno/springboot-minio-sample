package com.brianeno.upload.exception;

public class StorageException extends RuntimeException {

    public StorageException(Exception ex) {
        super(ex);
    }
}
