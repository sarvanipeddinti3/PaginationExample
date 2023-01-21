package com.example.PaginationExample.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "movieName")
    private String name;
    @Column(name = "poster")
    private String poster;
    @Column(name = "songs")
    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL)
    private Set<Song> songs= new HashSet<>();
    public Movie(String name){
        this.name=name;
    }
}
