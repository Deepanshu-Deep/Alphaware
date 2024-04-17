package com.blog.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blog.exception.PostException;
import com.blog.model.Category;
import com.blog.model.Post;
import com.blog.repository.PostRepository;
import com.blog.service.CategoryService;
import com.blog.service.PostService;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostRepository postRepository;
	
	
	 @Autowired
	 private CategoryService categoryService;
	
	
	@Override
	public Post createPost(Post post, Long categoryId) {
		
		Category category = categoryService.getCategoryById(categoryId);
		post.setCategory(category);
		post.setDate(new Date());
		return postRepository.save(post);
	}

	@Override
	public Post updatePost(Long postId, Post postDetails) {

		Optional<Post> postOptional = postRepository.findById(postId);

	    if(postOptional.isPresent()) {
	        Post existingPost = postOptional.get();
	        existingPost.setTitle(postDetails.getTitle());
	        existingPost.setContent(postDetails.getContent());
	        existingPost.setDate(new Date());
	        
	        return postRepository.save(existingPost);
	        
	    } else {
	    	
	        throw new PostException("Given post id not found: " + postId);
	    }

	}

	@Override
	public void deletePost(Long postId) {
		
		Post post = postRepository.findById(postId)
	            .orElseThrow(() -> new PostException(" Given post id not found: " + postId));
		
		postRepository.delete(post);
		
	}
	
	@Override
	public Post getPostById(Long postId) {
		
		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new PostException("Given post id not found: " + postId));
		
		return post;
	}

	@Override
	public List<Post> getAllPosts() {
		
		return postRepository.findAll();
	}



	
	

}
