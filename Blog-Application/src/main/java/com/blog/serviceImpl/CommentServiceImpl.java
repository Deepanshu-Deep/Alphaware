package com.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.CommentException;
import com.blog.exception.PostException;
import com.blog.model.Comment;
import com.blog.model.Post;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;


@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Override
	public Comment createComment(Long postId, String content) {
		
		Optional<Post> post = postRepository.findById(postId);
		
		if(post.isPresent()) {
			
			Comment comment = new Comment();
			
			comment.setPost(post.get());
			comment.setContent(content);
			comment.setCreatedAt(new Date());
			
			return commentRepository.save(comment);
		}
		throw new PostException("Given post Id not found : " + postId);
	}


	@Override
	public void deleteComment(Long commentId) {
		
		Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentException("Comment not found with id: " + commentId));
      
        commentRepository.delete(comment);
		
	}
	
	@Override
	public List<Comment> getCommentsByPostId(Long postId){
		
		return commentRepository.findByPostId(postId);
		
	}



	
	
}
