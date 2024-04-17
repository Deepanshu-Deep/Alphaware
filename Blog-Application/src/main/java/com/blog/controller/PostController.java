package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.Post;
import com.blog.serviceImpl.PostServiceImpl;

@RestController
@RequestMapping("/api")
public class PostController {
	
	@Autowired
	private PostServiceImpl postService;
	
	@PostMapping("post")
	public ResponseEntity<Post> createPostHandler(@RequestBody Post postDetails, @PathVariable Long categoryId){
		
		Post post1 =  postService.createPost(postDetails, categoryId);
		
		return new ResponseEntity<Post>(post1, HttpStatus.OK);
	}
	
	
	@GetMapping("post")
	public ResponseEntity<List<Post>> getAllPostsHandler(){
		
		return new ResponseEntity<List<Post>>(postService.getAllPosts(), HttpStatus.OK);
	}
	
	
	@PutMapping("post/{postId}")
	public ResponseEntity<Post> updatePostHandler(@PathVariable Long postId, @RequestBody Post postDetails) {
		
	    Post updatedPost = postService.updatePost(postId, postDetails);
	    
	    return new ResponseEntity<>(updatedPost, HttpStatus.OK);
	}

	
	
	@GetMapping("post/{postId}")
	public ResponseEntity<Post> getPostByIdHandler(@PathVariable Long postId){

		Post post = postService.getPostById(postId);
		
		return new ResponseEntity<Post>(post, HttpStatus.OK);
	}
	
	
	@DeleteMapping("post/{postId}")
	public ResponseEntity<String> deletePostHandler(@PathVariable Long postId) {
		
	    postService.deletePost(postId);
	    
	    return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
	}
	
	
	
	
	

}
