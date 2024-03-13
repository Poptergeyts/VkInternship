package com.poptergeyts.vk.controller;

import com.poptergeyts.vk.jsonObject.JsonPost;
import com.poptergeyts.vk.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostsController {
    private PostsService postsService;

    @Autowired
    public void setPostsService(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/posts")
    ResponseEntity<List<JsonPost>> getAllPosts() {
        return ResponseEntity.ok(postsService.getAllPosts());
    }

    @GetMapping("/posts/{postId}")
    ResponseEntity<JsonPost> getPost(@PathVariable("postId") Long postId) {
        return ResponseEntity.ok(postsService.getPost(postId));
    }
}
