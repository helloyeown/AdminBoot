package org.zerock.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.zerock.board.dto.BoardDTO;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    // 수정 폼
    @GetMapping("modify/{bno}")
    public String modifyForm(@PathVariable int bno, Model model){
        BoardDTO boardDTO = boardService.read(bno);
        model.addAttribute("board", boardDTO);
   
        return "/board/modify";

    }

    // 수정
    @PostMapping("modify/{bno}")
    public String modify(@PathVariable int bno, BoardDTO boardDTO, PageRequestDTO pageRequestDTO){
        boardService.modify(boardDTO);

        return "redirect:/board/read/" + bno;

    }

    
    // 목록
    @GetMapping("list")
    public void getList(Model model, PageRequestDTO dto){
        log.info("list...............");

        PageResponseDTO<BoardDTO> list = boardService.getList(dto);

        model.addAttribute("list", list);
        model.addAttribute("request", dto);
    }

    // 등록
    @PostMapping("regist")
    public String register(BoardDTO boardDTO){
        
        boardService.insert(boardDTO);

        return "redirect:/board/list";
    }

    // 등록 폼
    @GetMapping("regist")
    public void registForm(){
        log.info("GET registForm.................");
    }


    // 조회
    @GetMapping("read/{bno}")
    public String readPage(@PathVariable int bno, Model model, PageRequestDTO pageRequestDTO){
        
        log.info("GET read................");

        BoardDTO dto = boardService.read(bno);
        model.addAttribute("board", dto);
        //model.addAttribute("requestDTO", requestDTO);

        return "/board/read";

    }


    // 삭제
    @PostMapping("delete/{bno}")
    public String deleteBoard(@PathVariable int bno){
        log.info("POST | DELETE");

        boardService.delete(bno);

        return "redirect:/board/list";

    }

}
