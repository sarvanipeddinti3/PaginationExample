package com.example.PaginationExample.service.impl;

import com.example.PaginationExample.controllers.SongsController;
import com.example.PaginationExample.models.ImageFile;
import com.example.PaginationExample.repository.ImageFileRepository;
import com.example.PaginationExample.service.ImageFileService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageFileServiceImpl implements ImageFileService {
    private static final Logger logger = LogManager.getLogger(ImageFileServiceImpl.class);
    @Autowired
    private ImageFileRepository imageFileRepository;

    @Override
    public ImageFile storeFile(MultipartFile file) {
        logger.debug("Attempting to store file "+ file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if (fileName.contains("..")) {
                logger.error("Sorry! Filename contains invalid path sequence " + fileName);
                throw new RuntimeException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ImageFile dbFile = new ImageFile(fileName, file.getContentType(), file.getBytes());

            return imageFileRepository.save(dbFile);
        } catch (IOException ex) {
            logger.error("Could not store file " + fileName + "due to "+ ex.getMessage());
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public ImageFile getFile(String fileName) {
        logger.debug("Attempting to get file with name" + fileName);
        return imageFileRepository.findByFileName(fileName).stream().findFirst()
                .orElseThrow(() -> {
                    logger.error("File not found with name " + fileName);
                    return new RuntimeException("File not found with name " + fileName);
                });
    }
}
