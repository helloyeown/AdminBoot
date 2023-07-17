package org.zerock.board.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.board.dto.PageRequestDTO;
import org.zerock.board.dto.PageResponseDTO;
import org.zerock.board.dto.reply.ReplyDTO;
import org.zerock.board.service.reply.ReplyService;

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