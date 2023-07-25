package com.board.board.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.board.board.dto.PageRequestDTO;
import com.board.board.dto.PageResponseDTO;
import com.board.board.dto.reply.ReplyDTO;
import com.board.board.service.reply.ReplyService;

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

	// regist
	// @RequestBody: 클라이언트가 보낸 JSON 데이터를 ReplyDTO 객체로 변환
	@PostMapping("{bno}/regist")
	public Map<String, Integer> regist(@PathVariable("bno") Integer bno, @RequestBody ReplyDTO replyDTO){

		log.info("Post Reply Regist..................");

		replyDTO.setBno(bno);	// 안전장치

		Integer rno = replyService.regist(replyDTO);

		return Map.of("result", rno);
	}

	// read
	@GetMapping("read/{rno}")
	public ReplyDTO read(@PathVariable("rno") Integer rno){
		log.info("Get Read Reply...................");

		return replyService.read(rno);
	}

	// delete
	@DeleteMapping("delete/{rno}")
	public Map<String, Integer> delete(@PathVariable("rno") Integer rno){
		log.info("Reply Delete................");

		replyService.delete(rno);

		return Map.of("result", rno);
	}

	// modify
	@PutMapping("modify/{rno}")
	public Map<String, Integer> modify(@RequestBody ReplyDTO dto){
		
		log.info("Reply Modify.............");
		log.info(dto);

		replyService.modify(dto);

		return Map.of("result", dto.getRno());
	}

}
