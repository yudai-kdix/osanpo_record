package com.example.osanpo.Controllers;


import com.example.osanpo.Entities.Post;
import com.example.osanpo.Services.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public String getAllPosts(Model model) {
        List<Post> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "posts/list";
    }

    @GetMapping("/{id}")
    public String getPostById(@PathVariable Long id, Model model) {
        Post post = postService.findPostById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "posts/detail";
    }

    @GetMapping("/create")
    public String showCreateForm(Post post) {
        return "posts/create";
    }

    @PostMapping("/create")
    public String createPost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Post post = postService.findPostById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid post Id:" + id));
        model.addAttribute("post", post);
        return "posts/edit";
    }

    @PostMapping("/update/{id}")
    public String updatePost(@PathVariable Long id, @ModelAttribute Post post) {
        post.setId(id);
        postService.savePost(post);
        return "redirect:/posts";
    }

    @GetMapping("/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/posts";
    }
}