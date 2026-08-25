package com.rechnungswesen.app.outbox.entity;

import java.time.Instant;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
@Setter
public class OutboxEvent {

	private final UUID id;
	private final String eventType;
	private final UUID aggregateId;
	private final String payload;
	private final Instant createdAt;
	private Instant publishedAt;
}