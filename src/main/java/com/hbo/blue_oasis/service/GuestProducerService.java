package com.hbo.blue_oasis.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.hbo.blue_oasis.controller.dto.GuestRequest;

@Service
@Slf4j
public class GuestProducerService {

    @Autowired
    private KafkaTemplate<String, GuestRequest> kafkaGuestTemplate;

    public void sendMessage(GuestRequest message) {
        kafkaGuestTemplate.send("guest-topic", message).whenComplete((result, e) -> {
            if (e != null) {
                log.error("Error, Error Message: {}", e.getMessage());
            }
            log.info("Message sended: {}", result.getProducerRecord().value());
            log.info("Particion {}, Offset {}", result.getRecordMetadata().partition(),
                    result.getRecordMetadata().offset());
        });
    }
}
