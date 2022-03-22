package com.jun.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer> {
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Board b SET b.title = :title, b.contents = :contents WHERE b.boardIdx = :boardIdx")
    void updateBoard(Integer boardIdx, String title, String contents);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Board b SET b.hitCnt = b.hitCnt + 1 WHERE b.boardIdx = :boardIdx")
    void hitBoard(Integer boardIdx);
}
