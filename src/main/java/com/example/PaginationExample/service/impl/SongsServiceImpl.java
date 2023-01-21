package com.example.PaginationExample.service.impl;

import com.example.PaginationExample.controllers.SongsController;
import com.example.PaginationExample.models.Song;
import com.example.PaginationExample.repository.SongRepository;
import com.example.PaginationExample.service.SongsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SongsServiceImpl implements SongsService {
    private static final Logger logger = LogManager.getLogger(SongsServiceImpl.class);
    @Autowired
    private SongRepository songRepository;
    @Override
    public List<Song> getAllSongs(Integer pageNo, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Song> songPage = songRepository.findAll(pageable);
        if(songPage.hasContent()){
            logger.debug("current page songs" + songPage.getContent());
            return songPage.getContent();
        }
        logger.debug("No songs are present from {pageNo}. Returning empty list",pageNo);
        return new ArrayList<>();
    }

    @Override
    public List<Song> getAllSongsByMovie(String movie) {
        return null;
    }
}
