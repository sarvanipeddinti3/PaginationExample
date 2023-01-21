package com.example.PaginationExample.service;

import com.example.PaginationExample.models.Song;

import java.util.List;
import java.util.Set;

public interface SongsService {
    List<Song> getAllSongs(Integer pageNo, Integer pageSize);
    List<Song> getAllSongsByMovie(String movie);
}
