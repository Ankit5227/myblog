package com.myblog.blogapp.controller;


import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
      return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);

    }
    @GetMapping
    public List<PostDto> getAllPost(@RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {

        return postService.getAllPosts(pageNo, pageSize);

    }
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long id) {

        return ResponseEntity.ok(postService.getPostById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long id) {
        PostDto dto = postService.updatePost(postDto, id);
        return new ResponseEntity<>(dto, HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long id){
        postService.deletePost(id);
        return new ResponseEntity<>("Post entity deleted Successfully", HttpStatus.OK);
    }



}
