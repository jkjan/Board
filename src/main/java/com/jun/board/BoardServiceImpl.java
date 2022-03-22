package com.jun.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImpl implements BoardService {
    final private BoardRepository boardRepository;

    @Autowired
    public BoardServiceImpl(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @Override
    public List<Board> selectBoardList() throws Exception {
        return boardRepository.findAll();
    }

    @Override
    public Board insertBoard(Board board) {
        return boardRepository.save(board);
    }

    @Override
    public Optional<Board> selectBoardDetail(int boardIdx) {
        Optional<Board> ret = boardRepository.findById(boardIdx);

        // 조회수 증가
        if (ret.isPresent()) {
            ret.get().setHitCnt(ret.get().getHitCnt() + 1);
            boardRepository.save(ret.get());
        }

        return ret;
    }

    @Override
    public void deleteBoard(int boardIdx) {
        boardRepository.deleteById(boardIdx);
    }

    @Override
    public void updateBoard(Board board) {
//        boardRepository.updateBoard(board.getBoardIdx(), board.getTitle(), board.getContents());
        boardRepository.save(board);
    }

    @Override
    public void deleteAll() {
        boardRepository.deleteAll();
    }
}

