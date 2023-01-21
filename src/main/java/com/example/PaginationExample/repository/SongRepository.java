package com.example.PaginationExample.repository;

import com.example.PaginationExample.models.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song,Long> {

}
