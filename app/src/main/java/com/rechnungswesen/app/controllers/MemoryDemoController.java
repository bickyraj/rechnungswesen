package com.rechnungswesen.app.controllers;


import com.rechnungswesen.app.services.MemoryDemoService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/memory-demo")
public class MemoryDemoController {

	private final MemoryDemoService service;

	public MemoryDemoController(MemoryDemoService service) {
		this.service = service;
	}

	@PostMapping("/fill")
	public String fill(
			@RequestParam(defaultValue = "50000") int count) {
		service.fillMemory(count);
		return "Created objects: " + service.getObjectCount();
	}

	@DeleteMapping("/clear")
	public String clear() {
		service.clearMemory();
		return "Remaining objects: " + service.getObjectCount();
	}

	@GetMapping("/count")
	public int count() {
		return service.getObjectCount();
	}
}
