package com.jey.demo.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jey.demo.dto.NumberDTO;
import com.jey.demo.service.DemoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AdditionController {

	private final DemoService demoService;
	
	@PostMapping("/addition")
	public ResponseEntity<?> addition(@RequestBody NumberDTO numberDTO, HttpServletRequest request) {
		String sum = demoService.add("/api/addition", request.getRemoteAddr(), numberDTO.getNumbers());
		return ResponseEntity.ok(sum);
	}
}
