package com.poptergeyts.vk.controller;

import com.poptergeyts.vk.jsonObject.JsonPost;
import com.poptergeyts.vk.service.AlbumsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AlbumsController {
    private AlbumsService albumsService;

    @Autowired
    public void setPostsService(AlbumsService albumsService) {
        this.albumsService = albumsService;
    }

    @GetMapping("/posts")
    ResponseEntity<List<JsonPost>> getAllPosts() {
        return ResponseEntity.ok(albumsService.getAllAlbums());
    }

    @GetMapping("/posts/{albumId}")
    ResponseEntity<JsonPost> getPost(@PathVariable("albumId") Long postId) {
        return ResponseEntity.ok(albumsService.getAlbum(postId));
    }
}
