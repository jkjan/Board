package com.jun.board.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Post b SET b.title = :title, b.contents = :contents WHERE b.postIdx = :postIdx")
//    void updateBoard(Integer postIdx, String title, String contents);
//
//    @Transactional
//    @Modifying(clearAutomatically = true)
//    @Query("UPDATE Post b SET b.hitCnt = b.hitCnt + 1 WHERE b.postIdx = :postIdx")
//    void hitBoard(Integer postIdx);
}
