package com.jun.board;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@Slf4j
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
        log.trace("Trace");
        log.debug("Debug");
        log.info("Info");
        log.warn("Warn");
        log.error("Error");
        log.info("Accessing Board List");
        return mv;
    }

    @RequestMapping("/board/openBoardWrite.do")
    public String openBoardWrite() throws Exception {
        log.info("Writing a new post");
        return "boardWrite";
    }

    @RequestMapping("/board/insertBoard.do")
    public String insertBoardWrite(Board board) throws Exception {
        boardService.insertBoard(board);
        log.info("Wrote a new post, redirecting to Board List");
        return "redirect:/board/openBoardList.do";
    }

    @RequestMapping("/board/openBoardDetail.do")
    public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception {
        ModelAndView mv = new ModelAndView("boardDetail");
        Optional<Board> board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board", board.orElse(null));
        log.info("Reading a post");
        return mv;
    }

    @RequestMapping("/board/deleteBoard.do")
    public String deleteBoard(@RequestParam int boardIdx) throws Exception {
        boardService.deleteBoard(boardIdx);
        log.info("Deleted a post, redirecting to Board List");
        return "redirect:/board/openBoardList.do";
    }

    @RequestMapping("/board/updateBoard.do")
    public String updateBoard(Board board) throws Exception {
        boardService.updateBoard(board);
        log.info("Updated a post, redirecting to Board List");
        return "redirect:/board/openBoardList.do";
    }
}
