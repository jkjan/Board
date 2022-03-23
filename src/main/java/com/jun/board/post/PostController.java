package com.jun.board.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
public class PostController {
    final private PostService postService;

    PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/board/all")
    public ModelAndView getAllPosts() throws Exception {
        ModelAndView mv = new ModelAndView("postList");
        List<Post> list = postService.getAllPosts();
        mv.addObject("list", list);
        log.info("Accessing Board List");
        return mv;
    }

    @RequestMapping("/board/openPostWrite.do")
    public String writePost() throws Exception {
        log.info("Writing a new post");
        return "postWrite";
    }

    @RequestMapping("/board/insertPost.do")
    public String insertPostWrite(Post post) throws Exception {
        postService.insertPost(post);
        log.info("Wrote a new post, redirecting to Board List");
        return "redirect:/board/all";
    }

    @RequestMapping("/board/openPostDetail.do")
    public ModelAndView getPostDetail(@RequestParam int postIdx) throws Exception {
        ModelAndView mv = new ModelAndView("postDetail");
        Optional<Post> board = postService.getPost(postIdx);
        mv.addObject("post", board.orElse(null));
        log.info("Reading a post");
        return mv;
    }

    @RequestMapping("/board/deletePost.do")
    public String deletePost(@RequestParam int postIdx) throws Exception {
        postService.deletePost(postIdx);
        log.info("Deleted a post, redirecting to Board List");
        return "redirect:/board/all";
    }

    @RequestMapping("/board/updatePost.do")
    public String updateBoard(Post post) throws Exception {
        postService.updatePost(post);
        log.info("Updated a post, redirecting to Board List");
        return "redirect:/board/all";
    }
}
