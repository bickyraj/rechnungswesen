package com.rechnungswesen.app.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

import com.rechnungswesen.app.controllers.response.ArbeitNowResponse;
import com.rechnungswesen.app.controllers.response.FreeApiResponse;
import com.rechnungswesen.app.xmlprocessor.JsonData;
import com.rechnungswesen.app.xmlprocessor.JsonToXmlAdaptor;
import com.rechnungswesen.app.xmlprocessor.XmlProcessor;
import com.rechnungswesen.app.xmlprocessor.XmlProcessorService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class AppController {

	private final PractiseService practiseService;
	private final ExecutorService executorService;
	private final XmlProcessorService xmlProcessorService;

	@GetMapping("/home")
	public ResponseEntity<Map<String, Object>> home() {
		CompletableFuture<ArbeitNowResponse> futureA = CompletableFuture
				.supplyAsync(() -> {
					System.out.println("started task Arbeit job list");
					return practiseService.getArbeitNowJobList();
				}, executorService)
				.whenComplete((result, exception) -> {
					System.out.println("finished arbeit job.");
				});
		CompletableFuture<List<FreeApiResponse>> futureB = CompletableFuture
				.supplyAsync(() -> {
					System.out.println("started task Free api job list");
					return practiseService.getFreePublicApi();
				}, executorService)
				.whenComplete((result, exception) -> {
					System.out.println("finished free public job.");
				});

		ArbeitNowResponse responseA = futureA.join();
		List<FreeApiResponse> responseB = futureB.join();

		Map<String, Object> response = new HashMap<>();
		response.put("result", responseA);
		response.put("freePublicApi", responseB);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/test-n8n")
	public ResponseEntity<Boolean> testn8n() {
		throw new IllegalStateException("not working");
	}

	public ResponseEntity<Boolean> downloadXml(JsonData jsonData) {
		XmlProcessor xmlProcessor = new JsonToXmlAdaptor(jsonData);
		xmlProcessorService.download(xmlProcessor);
		return ResponseEntity.ok(true);
	}
}
