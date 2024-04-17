package com.blog.service;

import java.util.List;

import com.blog.model.Comment;

public interface CommentService {

    Comment createComment(Long postId, String content);
    
    void deleteComment(Long commentId);
    
    public List<Comment> getCommentsByPostId(Long postId);
	
}
