package com.rechnungswesen.app.outbox.repository;

import java.util.List;

import com.rechnungswesen.app.outbox.entity.OutboxEvent;

public interface OutboxRepository {
	void save(OutboxEvent event);
	List<OutboxEvent> findByPublishedAtIsNull();
}
