package com.example.PaginationExample.controllers;

import com.example.PaginationExample.models.ImageFile;
import com.example.PaginationExample.models.Movie;
import com.example.PaginationExample.service.ImageFileService;
import com.example.PaginationExample.service.MoviesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MoviesController {
    private static final Logger logger = LogManager.getLogger(MoviesController.class);
    @Autowired
    private ImageFileService fileStorageService;
    @Autowired
    private MoviesService moviesService;

    @PostMapping(value= "/movies",
            consumes = { MediaType.APPLICATION_JSON_VALUE,MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> uploadMovie(@RequestPart("movie") Movie movie,
                                         @RequestPart("file") MultipartFile file) {
        logger.debug("Attempting to upload movie");
        ImageFile fileName = fileStorageService.storeFile(file);
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();
        logger.debug("Uploaded image, download URI"+ fileDownloadUri);
        logger.debug("Uploading movie" + movie.getName());
        //Getting uploaded poster url and setting it to movie
        if(movie.getPoster()==null)
            movie.setPoster(fileDownloadUri);
        Movie movieResponse = moviesService.uploadMovie(movie);
        logger.debug("Received Response"+ movieResponse);
        return new ResponseEntity<>(movieResponse,HttpStatus.CREATED);
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<?> getMovieDetails(@PathVariable("id") Long Id){
        Movie movie = moviesService.getMovieDetails(Id);
        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        ImageFile databaseFile = fileStorageService.getFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }


}
