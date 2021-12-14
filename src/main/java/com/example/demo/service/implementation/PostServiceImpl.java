package com.example.demo.service.implementation;

import com.example.demo.domain.dto.input.Post;
import com.example.demo.feignclients.PostFeignClient;
import com.example.demo.service.api.PostService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {


    private final PostFeignClient postFeignClient;

    public PostServiceImpl(PostFeignClient postFeignClient) {
        this.postFeignClient = postFeignClient;
    }


    @Override
    public List<Post> getAllPosts() {
        return postFeignClient.getAllPosts();
    }

    @Override
    public Post getPostById(Long postId) {
        return postFeignClient.getPostById(postId);
    }

    @Override
    public List<Post> getAllPostsByUserId(Long userId) {
        return postFeignClient.getPostByUserId(userId);
    }

    @Override
    public Post createPost(Post post) {
        return postFeignClient.createPost(post);
    }

    @Override
    public void updatePost(Long postId, Post post) {
        postFeignClient.updatePost(post);
    }

    @Override
    public void deletePost(Long postId) {
        postFeignClient.deletePost(postId);
    }
}
