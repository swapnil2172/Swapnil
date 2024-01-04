package com.blog.service.impl;

import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto savePost(PostDto dto) {
        Post post=new Post();
        post.setTitle(dto.getTitle());
        post.setDescription(dto.getDescription());
        post.setContent(dto.getContent());

        Post saved = postRepo.save(post);

        PostDto postDto=new PostDto();
        postDto.setId(saved.getId());
        postDto.setTitle(saved.getTitle());
        postDto.setDescription(saved.getDescription());
        postDto.setContent(saved.getContent());
        postDto.setMessage("post is created");

        return postDto;
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()->  new ResourceNotFoundException("post not found with id"+id)
        );
        postRepo.deleteById(id);
    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo,pageSize, sort);
        Page<Post> all = postRepo.findAll(pageable);
        List<Post> content = all.getContent();
        List<PostDto> dtos = content.stream().map(p -> maptoDto(p)).collect(Collectors.toList());
        return dtos;

    }

    @Override
    public PostDto updatePost(long postId, PostDto postDto) {
        Post post = postRepo.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post is not available with id" + postId)
        );

        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post saved = postRepo.save(post);
        PostDto dto = maptoDto(saved);
        return dto;

    }

    PostDto maptoDto(Post post){
        PostDto postDto=new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

}
