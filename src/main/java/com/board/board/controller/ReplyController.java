package com.board.board.controller;

import com.board.board.dto.PageResponseDTO;
import com.board.board.service.reply.ReplyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.reply.ReplyDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/replies/")
@Log4j2
public class ReplyController {
	
	private final ReplyService replyService;

	// list
	@GetMapping(value = "{bno}/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public PageResponseDTO<ReplyDTO> getList(@PathVariable("bno") Integer bno, PageRequestDTO requestDTO){
		log.info("get Reply List............");
		return replyService.getList(bno, requestDTO);
	}

}
