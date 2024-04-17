package com.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.model.Comment;
import com.blog.serviceImpl.CommentServiceImpl;

@RestController
@RequestMapping("/api")
public class CommentController {

	
	@Autowired
	private CommentServiceImpl commentService;
	
	@PostMapping("comments/create")
	public ResponseEntity<Comment> createCommentHandler(@RequestParam Long postId, @RequestParam String content){
		
		Comment comment =  commentService.createComment(postId, content);
		
		return new ResponseEntity<Comment>(comment, HttpStatus.OK);
	}
	
	
	@GetMapping("comments/{postId}")
	public ResponseEntity<List<Comment>> getAllCommentsHandler(@PathVariable Long postId){
		
//		List<Comment> comment =  commentService.getCommentsByPostId(postId);
		
		return new ResponseEntity<List<Comment>>(commentService.getCommentsByPostId(postId), HttpStatus.OK);
	}
	
	
	@DeleteMapping("comment/{commentId}")
	public ResponseEntity<String> deleteCommentHandler(@PathVariable Long commentId) {
		
	    commentService.deleteComment(commentId);
	    
	    return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
	}

	
}
