package com.example.PaginationExample.service;

import com.example.PaginationExample.models.ImageFile;
import org.springframework.web.multipart.MultipartFile;

public interface ImageFileService {
    ImageFile storeFile(MultipartFile file);

    ImageFile getFile(String fileName);
}
