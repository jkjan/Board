package com.jun.board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    List<Board> selectBoardList() throws Exception;
    Board insertBoard(Board board);
    Optional<Board> selectBoardDetail(int boardIdx);
    void deleteBoard(int boardIdx);
    void updateBoard(Board board);
    void deleteAll();
}
