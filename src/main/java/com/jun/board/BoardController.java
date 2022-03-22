package com.jun.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class BoardController {
    final private BoardService boardService;

    BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @RequestMapping("/board/openBoardList.do")
    public ModelAndView openBoardList() throws Exception {
        ModelAndView mv = new ModelAndView("boardList");
        List<Board> list = boardService.selectBoardList();
        mv.addObject("list", list);
        return mv;
    }

    @RequestMapping("/board/openBoardWrite.do")
    public String openBoardWrite() throws Exception {
        return "boardWrite";
    }

    @RequestMapping("/board/insertBoard.do")
    public String insertBoardWrite(Board board) throws Exception {
        boardService.insertBoard(board);
        return "redirect:/board/openBoardList.do";
    }

    @RequestMapping("/board/openBoardDetail.do")
    public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
        ModelAndView mv = new ModelAndView("boardDetail");
        Optional<Board> board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board.orElse(null));
        return mv;
    }

    @RequestMapping("/board/deleteBoard.do")
    public String deleteBoard(@RequestParam int boardIdx) throws Exception {
        boardService.deleteBoard(boardIdx);
        return "redirect:/board/openBoardList.do";
    }

    @RequestMapping("/board/updateBoard.do")
    public String updateBoard(Board board) throws Exception {
        boardService.updateBoard(board);
        return "redirect:/board/openBoardList.do";
    }
}
