package com.myblog1.myblog1.service;

import com.myblog1.myblog1.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
    List<CommentDto> getCommentByPostId(long postId);

    CommentDto updateComment(long postId, long Id, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
}
