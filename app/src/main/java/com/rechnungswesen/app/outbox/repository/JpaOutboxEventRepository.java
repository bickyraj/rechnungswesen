package com.rechnungswesen.app.outbox.repository;

import java.util.List;
import java.util.UUID;

import com.rechnungswesen.app.outbox.model.OutboxEventModel;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOutboxEventRepository extends JpaRepository<OutboxEventModel, UUID> {
	List<OutboxEventModel> findByPublishedAtIsNull();
}
