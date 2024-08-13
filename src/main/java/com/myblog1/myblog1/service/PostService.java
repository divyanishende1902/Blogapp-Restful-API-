package com.myblog1.myblog1.service;

import com.myblog1.myblog1.payload.PostDto;
import com.myblog1.myblog1.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy,String  sortDir);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);

     void deletePost(long id);
}

