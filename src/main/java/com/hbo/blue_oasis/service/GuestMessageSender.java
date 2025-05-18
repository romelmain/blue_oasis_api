package com.hbo.blue_oasis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.hbo.blue_oasis.controller.dto.GuestRequest;

import io.awspring.cloud.sqs.operations.SqsTemplate;

@Service
public class GuestMessageSender {

    @Autowired
    private SqsTemplate sqsTemplate;

    @Value("${sqs.queue.name}")
    private String queueName;

    public void sendMessage(GuestRequest message) {
        sqsTemplate.send(sqsSendOptions -> sqsSendOptions.queue(queueName).payload(message));
        System.out.println(message + " = sent successfully to the " + queueName);
    }
}
