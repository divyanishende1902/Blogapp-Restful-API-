package com.myblog1.myblog1.repository;

import com.myblog1.myblog1.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments , Long> {
    //whenever u want to search a record in the table baaed on the column name of ur choice just goto the repository
    // layer and what paramter to the supply.
    List<Comments> findByPostId(long postId);
}
