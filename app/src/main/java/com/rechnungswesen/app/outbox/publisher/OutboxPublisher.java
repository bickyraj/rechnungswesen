package com.rechnungswesen.app.outbox.publisher;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rechnungswesen.app.messaging.event.PaymentCompletedEvent;
import com.rechnungswesen.app.outbox.entity.OutboxEvent;
import com.rechnungswesen.app.outbox.repository.OutboxRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {

	private final OutboxRepository outboxRepository;
	private final KafkaTemplate<String, Object> kafkaTemplate;
	private final ObjectMapper objectMapper;

	@Scheduled(fixedDelay = 10000)
	public void publish() throws JsonProcessingException {

		List<OutboxEvent> events =
				outboxRepository.findByPublishedAtIsNull();

		for (OutboxEvent event : events) {

			PaymentCompletedEvent paymentEvent =
					objectMapper.readValue(
							event.getPayload(),
							PaymentCompletedEvent.class
					);
			kafkaTemplate.send(
					"payment-events",
					event.getAggregateId().toString(),
					paymentEvent
			).whenComplete((result, exception) -> {

				if (exception == null) {
					event.setPublishedAt(Instant.now());
					outboxRepository.save(event);
				} else {
					System.err.println(
							"Failed to publish outbox event "
									+ event.getId()
					);
				}
			});
		}
	}
}
