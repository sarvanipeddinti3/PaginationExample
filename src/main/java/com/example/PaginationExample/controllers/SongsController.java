package com.example.PaginationExample.controllers;

import com.example.PaginationExample.models.Song;
import com.example.PaginationExample.service.SongsService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.Set;

@RestController
public class SongsController {
    private static final Logger logger = LogManager.getLogger(SongsController.class);
    @Autowired
    private SongsService songsService;


    @GetMapping("/songs")
    private ResponseEntity<?> getSongs(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize)
    {
        logger.debug("Requesting songs from {pageNo}",pageNo);
        List<Song> songs =  songsService.getAllSongs(pageNo, pageSize);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    @GetMapping("/{movie}/songs")
    private ResponseEntity<?> getSongsByMovie(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "5") Integer pageSize,
            @PathVariable String movie)
    {
        List<Song> songs =  songsService.getAllSongsByMovie(movie);
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }
}
