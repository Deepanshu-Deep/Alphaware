package com.blog.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.model.Post;


public interface PostService {
	
    Post createPost(Post post, Long categoryId);
    Post updatePost(Long postId, Post postDetails);
    void deletePost(Long postId);
    Post getPostById(Long postId);
    List<Post> getAllPosts();
   
	
}
