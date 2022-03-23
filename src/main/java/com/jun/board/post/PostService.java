package com.jun.board.post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<Post> getAllPosts() throws Exception;
    void insertPost(Post post);
    Optional<Post> getPost(int postIdx);
    void deletePost(int postIdx);
    void updatePost(Post post);
}
