package com.hbo.blue_oasis.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.hbo.blue_oasis.controller.dto.GuestRequest;

import java.util.HashMap;

@Configuration
public class GuestProducerFactoryConfig {

    @Autowired
    private KafkaProperties kafkaProperties;

    // Configure producer properties
    @Bean
    public ProducerFactory<String, GuestRequest> producerGuestFactory() {
        var configs = new HashMap<String, Object>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configs);
    }

    // Add configurations to the producer temlate
    @Bean
    public KafkaTemplate<String, GuestRequest> kafkaGuestTemplate() {
        return new KafkaTemplate<>(producerGuestFactory());
    }
}
