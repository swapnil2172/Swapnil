package com.blog.controller;

import com.blog.payload.PostDto;
import com.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    public PostController(PostService postService) {
        this.postService = postService;
    }

    private PostService postService;
    @PostMapping
    public ResponseEntity<?>savePost(@Valid @RequestBody PostDto dto, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto postDto = postService.savePost(dto);
        return new ResponseEntity<>(postDto,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id){
        postService.deletePost(id);
        return new ResponseEntity<>("post is deleted",HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name = "pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false)String sortBy,
            @RequestParam(name="sortDir",defaultValue = "asc",required = false)String sortDir){
        List<PostDto> dtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long postId,@RequestBody PostDto postDto){
        PostDto dto = postService.updatePost(postId, postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }


}
