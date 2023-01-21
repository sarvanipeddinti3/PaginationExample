package com.example.PaginationExample.repository;

import com.example.PaginationExample.models.ImageFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageFileRepository extends JpaRepository<ImageFile,String> {
    List<ImageFile> findByFileName(String name);
}
