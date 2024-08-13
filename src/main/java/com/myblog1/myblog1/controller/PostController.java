package com.myblog1.myblog1.controller;

import com.myblog1.myblog1.payload.PostDto;
import com.myblog1.myblog1.payload.PostResponse;
import com.myblog1.myblog1.service.PostService;
import com.myblog1.myblog1.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

    //Get all posts rest api
    @GetMapping
    public PostResponse getAllPost(@RequestParam (value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required= false ) int pageNo,
                                   @RequestParam(value="pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize ,
                                   @RequestParam(value="sortBy", defaultValue= AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                                   @RequestParam(value="sortDir", defaultValue=AppConstants.DEFAULT_SORT_DIR, required=false) String sortDir
    )
    {
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);


    }


        //response Entity is used to send response back.
        // below code is used to get data by id
        @GetMapping("/{id}")
        public ResponseEntity<PostDto> getPostById(@PathVariable(name="id") long id){
         return ResponseEntity.ok(postService.getPostById(id));
        }

       // below code is used to update data by id update post rest api

    @PreAuthorize("hasRole('ADMIN')")
       @PutMapping("/{id}")
       public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable(name="id")long id){
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
      // delete post rest Api

      @PreAuthorize("hasRole('ADMIN')")
      @DeleteMapping("/{id}")
      public ResponseEntity<String> deletePost(@PathVariable (name="id") long id){

        postService.deletePost(id);

        return new ResponseEntity<>("Post Entity deleted Successfully", HttpStatus.OK);

    }
    }
