package com.rechnungswesen.app.configurations;

import java.util.HashMap;
import java.util.Map;

import com.rechnungswesen.app.messaging.event.PaymentCompletedEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

@Configuration
public class KafkaConfig {

	@Bean
	public ProducerFactory<String, Object> producerFactory(
			KafkaProperties properties) {

		Map<String, Object> config = new HashMap<>();

		config.put(
				ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
				properties.getBootstrapServers()
		);

		config.put(
				ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
				StringSerializer.class
		);

		config.put(
				ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				JsonSerializer.class
		);

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	public ConsumerFactory<String, PaymentCompletedEvent> consumerFactory(
			KafkaProperties properties) {

		Map<String, Object> config = new HashMap<>();

		config.put(
				ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
				properties.getBootstrapServers()
		);

		config.put(
				ConsumerConfig.GROUP_ID_CONFIG,
				"payment-processing-group"
		);

		config.put(
				ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
				StringDeserializer.class
		);

		config.put(
				ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
				JsonDeserializer.class
		);

		JsonDeserializer<PaymentCompletedEvent> deserializer =
				new JsonDeserializer<>(PaymentCompletedEvent.class);

		deserializer.addTrustedPackages("com.rechnungswesen.app");

		return new DefaultKafkaConsumerFactory<>(
				config,
				new StringDeserializer(),
				deserializer
		);
	}

	@Bean
	public KafkaTemplate<String, Object> kafkaTemplate(
			ProducerFactory<String, Object> producerFactory) {

		return new KafkaTemplate<>(producerFactory);
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, PaymentCompletedEvent>
	kafkaListenerContainerFactory(
			ConsumerFactory<String, PaymentCompletedEvent> consumerFactory) {

		ConcurrentKafkaListenerContainerFactory<String, PaymentCompletedEvent>
				factory = new ConcurrentKafkaListenerContainerFactory<>();

		factory.setConsumerFactory(consumerFactory);

		return factory;
	}
}
