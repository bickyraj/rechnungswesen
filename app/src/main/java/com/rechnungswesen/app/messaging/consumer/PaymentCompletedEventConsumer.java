package com.rechnungswesen.app.messaging.consumer;

import com.rechnungswesen.app.messaging.event.PaymentCompletedEvent;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentCompletedEventConsumer {

	@KafkaListener(
			topics = "payment-events",
			groupId = "payment-processing-group"
	)
	public void handle(PaymentCompletedEvent event) {

		System.out.println(
				"Payment completed: " + event.paymentId()
		);
	}
}
