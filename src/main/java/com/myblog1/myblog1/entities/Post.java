package com.myblog1.myblog1.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="posts",uniqueConstraints = {@UniqueConstraint(columnNames={"title"})})
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="title", nullable=false)
    private String title;
    @Column(name="description", nullable=false)
    private String description;
    @Lob
    @Column(name="content", nullable=false)
    private String content;//255 character

    // we write post in mapped bcoz u seen in the comments class this comments is mapped to the post varible is present in another class
    @OneToMany(mappedBy = "post", cascade= CascadeType.ALL, orphanRemoval = true)//cascade meaning any changes i parents class would be impact on child
    Set<Comments> comments = new HashSet<>();
}
