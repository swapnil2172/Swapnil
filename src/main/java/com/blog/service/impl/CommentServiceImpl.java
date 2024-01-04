package com.blog.service.impl;

import com.blog.entity.Comment;
import com.blog.entity.Post;
import com.blog.exception.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(long postId, CommentDto dto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post is not found with id" + postId)
        );

        Comment comment=new Comment();
        comment.setName(dto.getName());
        comment.setBody(dto.getBody());
        comment.setEmail(dto.getEmail());

        comment.setPost(post);

        Comment save = commentRepository.save(comment);
        CommentDto commentDto = maptoDto(save);

        return commentDto;

    }

    @Override
    public void deleteComment(long commentId) {
        commentRepository.findById(commentId).orElseThrow(
                ()->new ResourceNotFoundException("comment is not found with id"+commentId)
        );

        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getcommentsbypostId(long postId) {
        List<Comment> comments = commentRepository.findBypostId(postId);
        List<CommentDto> dtos = comments.stream().map(c -> maptoDto(c)).collect(Collectors.toList());
        return dtos;
    }

    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("comment is not found with id" + commentId)
        );


        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment save = commentRepository.save(comment);
        CommentDto dtos = maptoDto(save);
        return dtos;
    }

    @Override
    public List<CommentDto> getAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentDto> dtos = comments.stream().map(c -> maptoDto(c)).collect(Collectors.toList());
        return dtos;
    }

    CommentDto maptoDto(Comment comment){
        CommentDto dtos=new CommentDto();
        dtos.setId(comment.getId());
        dtos.setName(comment.getName());
        dtos.setEmail(comment.getEmail());
        dtos.setBody(comment.getBody());
        return dtos;
    }

}
