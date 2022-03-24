package com.jun.board.post;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    final private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPosts() throws Exception {
        return postRepository.findAll();
    }

    @Override
    public void insertPost(Post post) {
        postRepository.save(post);
    }

    @Override
    @Transactional
    public Optional<Post> getPost(int postIdx) {
        Optional<Post> ret = postRepository.findById(postIdx);

        // 조회수 증가
        if (ret.isPresent()) {
            ret.get().setHitCnt(ret.get().getHitCnt() + 1);
            log.debug("Add 1 to hit cnt");
        }

        return ret;
    }

    @Override
    public void deletePost(int postIdx) {
        postRepository.deleteById(postIdx);
    }

    @Override
    public void updatePost(Post post) {
        postRepository.save(post);
    }
}

