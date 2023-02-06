package com.daengnyangffojjak.dailydaengnyang.controller;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/utils")
public class UtilController {

	private final Environment env;

	@GetMapping("/profile")
	public String getProfile() {
		return Arrays.stream(env.getActiveProfiles()).findFirst().orElse("");
	}
}