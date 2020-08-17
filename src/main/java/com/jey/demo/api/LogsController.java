package com.jey.demo.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jey.demo.service.DemoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LogsController {

	private final DemoService demoService;
	
	@GetMapping("/logs")
	public ResponseEntity<?> list() throws Exception {
		return ResponseEntity.ok(demoService.retrieveLogs());
		
	}
	
}
