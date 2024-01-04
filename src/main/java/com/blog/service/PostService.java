package com.blog.service;

import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {

    PostDto savePost(PostDto dto);

    void deletePost(long id);

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDto updatePost(long postId, PostDto postDto);
}
