package com.myblog1.myblog1.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private Long id;
    @NotNull
    @Size(min =3, message = "post title should have atleast 3 characters")
    private String title;
    @NotNull
    @Size(min =10, message = "post description should have atleast 10 characters")
    private String description;
    @NotNull
    @NotEmpty
    private String content;




}
