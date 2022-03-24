package com.jun.board.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    @Transactional
    @Modifying
    @Query("update Post a set a.title = :#{#post.title}, a.contents = :#{#post.contents} WHERE a.postIdx = :#{#post.postIdx}")
    public void updatePost(@Param("post") Post post);
}
