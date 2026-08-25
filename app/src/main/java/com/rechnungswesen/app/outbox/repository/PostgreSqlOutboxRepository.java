package com.rechnungswesen.app.outbox.repository;

import java.util.List;

import com.rechnungswesen.app.outbox.entity.OutboxEvent;
import com.rechnungswesen.app.outbox.model.OutboxEventModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostgreSqlOutboxRepository implements OutboxRepository {

	private final JpaOutboxEventRepository jpaOutboxEventRepository;
	private final ModelMapper modelMapper;

	@Override
	public void save(OutboxEvent event) {
		OutboxEventModel model = modelMapper.map(event, OutboxEventModel.class);
		jpaOutboxEventRepository.save(model);
	}

	@Override
	public List<OutboxEvent> findByPublishedAtIsNull() {
		return jpaOutboxEventRepository
				.findByPublishedAtIsNull()
				.stream()
				.map(outboxEventModel -> OutboxEvent.builder()
						.id(outboxEventModel.getId())
						.aggregateId(outboxEventModel.getAggregateId())
						.eventType(outboxEventModel.getEventType())
						.payload(outboxEventModel.getPayload())
						.createdAt(outboxEventModel.getCreatedAt())
						.publishedAt(outboxEventModel.getPublishedAt())
						.build())
				.toList();
	}
}
