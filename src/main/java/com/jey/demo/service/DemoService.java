package com.jey.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jey.demo.entities.Log;
import com.jey.demo.repository.LogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DemoService {

	private final LogRepository logRepository;

	@Transactional(rollbackFor = Exception.class)
	public String add(String apiName, String ip, int... nums) {
		if (nums.length < 1) {
			return "No numbers entered";
		}
		
		int sum = Arrays.stream(nums).sum();
		String total = "sum of " + Arrays.toString(nums) + " is " + sum;
		
		Log log = new Log();
		log.setApiName(apiName);
		log.setData(total);
		log.setIp(ip);
		
		logRepository.save(log);
		
		return total;
	}
	
	public List<Log> retrieveLogs() {
		return logRepository.findAll();
	}
}
