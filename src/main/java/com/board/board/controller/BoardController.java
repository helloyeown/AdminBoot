package com.board.board.controller;

import com.board.board.dto.BoardDTO;
import com.board.board.dto.BoardListDTO;
import com.board.board.dto.BoardRegisterDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.service.BoardService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.board.dto.PageRequestDTO;

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
    public String modifyForm(@PathVariable Integer bno, Model model, PageRequestDTO pageRequestDTO){
        
        log.info("GET MODIFY...............");
        log.info(pageRequestDTO);

        BoardDTO boardDTO = boardService.read(bno);
        model.addAttribute("board", boardDTO);
   
        return "/board/modify";

    }

    // 수정
    @PostMapping("modify/{bno}")
    public String modify(@PathVariable int bno, BoardDTO boardDTO, PageRequestDTO pageRequestDTO, RedirectAttributes rttr){

        boardService.modify(boardDTO);
        rttr.addFlashAttribute("message", boardDTO.getBno() + "번 게시물이 수정되었습니다.");

        return "redirect:/board/read/" + bno;

    }

    
    // 목록
    @GetMapping("list")
    public void getList(Model model, PageRequestDTO pageRequestDTO){
        log.info("list...............");

        // log.info(keyword);
        // log.info(type);

        // pageRequestDTO.setKeyword(keyword);
        // pageRequestDTO.setType(type);

        log.info("====================================================================");
        log.info(pageRequestDTO);
        log.info("====================================================================");

        PageResponseDTO<BoardListDTO> list = boardService.getList(pageRequestDTO);
        log.info("-------------------------");
        log.info(list);
        log.info(pageRequestDTO);

        model.addAttribute("list", list);
    }

    // 등록
    @PostMapping("regist")
    public String register(BoardRegisterDTO boardDTO, RedirectAttributes rttr){
        
        boardService.insert(boardDTO);

        rttr.addFlashAttribute("message", "게시물이 등록되었습니다.");

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
