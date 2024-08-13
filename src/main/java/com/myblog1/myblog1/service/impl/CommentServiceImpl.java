package com.myblog1.myblog1.service.impl;

import com.myblog1.myblog1.entities.Comments;
import com.myblog1.myblog1.entities.Post;
import com.myblog1.myblog1.exception.ResourceNotFoundException;
import com.myblog1.myblog1.payload.CommentDto;
import com.myblog1.myblog1.repository.CommentRepository;
import com.myblog1.myblog1.repository.PostRepository;
import com.myblog1.myblog1.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
    }
// in below code we passed postId through postman id to check and commentdto is nothing but a json obj
    //to putit into as per id passed.
    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("post", "id", postId)
        );
        // here we need to trasfer json obj into entity class (comments) thats why we did mapping with
        //comments aftr passing data we told to springboot to whatever data we r trasfring set for post
        // so we setPost. then we called commentRepository to save then we return to maptodto.
        Comments comment = maptoComments(commentDto);
        comment.setPost(post);
        Comments newComment = commentRepository.save(comment);
        return  maptoDto(newComment);

    }

    @Override
    public List<CommentDto> getCommentByPostId(long postId) {
        List<Comments> comments = commentRepository.findByPostId(postId);
       return  comments.stream().map(comment -> maptoDto(comment)).collect(Collectors.toList());

    }

    @Override
    public CommentDto updateComment(long postId, long Id, CommentDto commentDto) {//in commentdto data is present

        //retrieve postbyid
        Post post = postRepository.findById(postId).orElseThrow(() -> new
                ResourceNotFoundException("post", "id", postId));

        //retrieve commentByIdc
        Comments comments = commentRepository.findById(Id).orElseThrow(() -> new
                ResourceNotFoundException("comment", "Id", Id));

        comments.setPost(post);

        comments.setName(commentDto.getName());
        comments.setEmail(commentDto.getEmail());
        comments.setBody(commentDto.getBody());

        Comments updatedComment = commentRepository.save(comments);

        return maptoDto(updatedComment);

    }

    @Override
    public void deleteComment(long postId, long commentId) {
        postRepository.findById(postId).orElseThrow(()->
                new ResourceNotFoundException("post","id",postId));
        Comments comments = commentRepository.findById(commentId).orElseThrow(() -> new
                ResourceNotFoundException("comment", "Id", commentId));


        commentRepository.deleteById(commentId);
    }

    //below code go dto to entity
    Comments maptoComments(CommentDto commentDto){
        Comments comment = mapper.map(commentDto, Comments.class);
//    //    Comments comment = new Comments();
//  //  comment.setName(commentDto.getName());
//  //  comment.setEmail(commentDto.getEmail());
//    //comment.setBody(commentDto.getBody());
    return comment;
    }

    //below code go from entity to  dto
    CommentDto maptoDto(Comments comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);

//    //    CommentDto commentDto = new CommentDto();
//      //  commentDto.setId(comment.getId());
//        //commentDto.setName(comment.getName());
//        //commentDto.setEmail(comment.getEmail());
//        //commentDto.setBody(comment.getBody());
        return commentDto;
    }
}
