package com.blog.service;

import com.blog.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto dto);

    void deleteComment(long commentId);

    List<CommentDto> getcommentsbypostId(long postId);

    CommentDto updateComment(long commentId, CommentDto commentDto);

    List<CommentDto> getAllComments();
}
