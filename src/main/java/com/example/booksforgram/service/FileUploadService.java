package com.example.booksforgram.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileUploadService {
    void upload(MultipartFile file) throws IOException;
}
