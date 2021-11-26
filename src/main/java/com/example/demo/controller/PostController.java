package com.example.demo.controller;

import com.example.demo.domain.dto.input.Post;
import com.example.demo.service.api.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {


    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAllPosts() { return postService.getAllPosts(); }

    @GetMapping("/{postId}")
    public Post getPostById(@PathVariable Long postId) { return postService.getPostById(postId); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post createPost(Post post) { return postService.createPost(post); }

    @PutMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePost(@PathVariable Long postId, Post post) { postService.updatePost(postId, post); }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void deletePost(@PathVariable Long postId) { postService.deletePost(postId); }

}
