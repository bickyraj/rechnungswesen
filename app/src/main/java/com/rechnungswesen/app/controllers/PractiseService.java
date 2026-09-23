package com.rechnungswesen.app.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import com.rechnungswesen.app.controllers.response.ArbeitNowResponse;
import com.rechnungswesen.app.controllers.response.FreeApiResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class PractiseService {
	private Long counter;
	private final ReentrantLock paymentLock = new ReentrantLock();
	private final RestTemplate restTemplate;

	@PostConstruct
	private void init() {
		counter = 0L;
	}

	public ArbeitNowResponse getArbeitNowJobList() {
		try {
			Thread.sleep(20000);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		return restTemplate.getForObject(
				"https://www.arbeitnow.com/api/job-board-api",
				ArbeitNowResponse.class
				);
	}

	public List<FreeApiResponse> getFreePublicApi() {
		try {
			Thread.sleep(20000);
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
		FreeApiResponse[] response = restTemplate.getForObject(
				"https://www.freepublicapis.com/api/apis?limit=10&sort=best",
				FreeApiResponse[].class
		);
		assert response != null;
		return Arrays.asList(response);
	}

	public void pay() {
		if (!paymentLock.tryLock()) {
			throw new IllegalStateException("Payment is already being processed.");
		}
		try {
			counter++;
			Thread.sleep(5000);
			System.out.println("payment started.");
		}
		catch (InterruptedException e) {
			throw new RuntimeException(e);
		} finally {
			paymentLock.unlock();
			System.out.println("payment done");
		}
	}
}
