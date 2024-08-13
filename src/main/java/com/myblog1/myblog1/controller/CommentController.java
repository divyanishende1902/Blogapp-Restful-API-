package com.myblog1.myblog1.controller;

import com.myblog1.myblog1.payload.CommentDto;
import com.myblog1.myblog1.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //localhost:8080/posts/{postId}/comments
    @PostMapping("posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
                                                    @RequestBody CommentDto commentDto) {

        CommentDto dto = commentService.createComment(postId, commentDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);

    }

    @GetMapping("posts/{postId}/comments")
    public List<CommentDto> getAllCommentsByPostId(@PathVariable("postId") long postId) {

        List<CommentDto> dto = commentService.getCommentByPostId(postId);
        return dto;
    }

    @PutMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<CommentDto> updateComment(
            @PathVariable("postId") long postId,
            @PathVariable("Id") long Id,
            @RequestBody CommentDto commentDto) {
        CommentDto dto = commentService.updateComment(postId, Id, commentDto);

         return new ResponseEntity<>(dto, HttpStatus.OK);
    }

      @DeleteMapping("/posts/{postId}/comments/{Id}")
    public ResponseEntity<String> deleteComment(
              @PathVariable("postId") long postId,
              @PathVariable("Id") long commentId

      ){
        commentService.deleteComment(postId, commentId);

        return new ResponseEntity<>("Comment is Deleted!", HttpStatus.OK);

      }

}
