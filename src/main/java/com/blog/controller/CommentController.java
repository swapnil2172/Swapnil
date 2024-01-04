package com.blog.controller;

import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/{postId}")
    public ResponseEntity<CommentDto> createComment(@PathVariable long postId, @RequestBody CommentDto dto){
        CommentDto dtos = commentService.createComment(postId, dto);
        return new ResponseEntity<>(dtos, HttpStatus.CREATED);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable long commentId){
        commentService.deleteComment(commentId);
        return new ResponseEntity<>("comment is deleted with id"+commentId,HttpStatus.OK);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<CommentDto>> getcommentsbypostId(@PathVariable long postId){
        List<CommentDto> dtos = commentService.getcommentsbypostId(postId);
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable long commentId, @RequestBody CommentDto commentDto){
        CommentDto dto = commentService.updateComment(commentId, commentDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> getAllComments(){
        List<CommentDto> dtos = commentService.getAllComments();
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
}
