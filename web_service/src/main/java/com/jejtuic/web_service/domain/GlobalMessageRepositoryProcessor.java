package com.jejtuic.web_service.domain;

import com.jejtuic.web_service.ui.data_objects.Message;
import com.jejtuic.web_service.database.GlobalMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class GlobalMessageRepositoryProcessor {

    private final GlobalMessageRepository messageRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public GlobalMessageRepositoryProcessor(GlobalMessageRepository messageRepository, RestTemplate restTemplate) {
        this.messageRepository = messageRepository;
        this.restTemplate = restTemplate;
    }

    public List<Message> findAll() {
        List<com.jejtuic.web_service.database.data_objects.Message> databaseList = messageRepository.findAll();
        List<Message> uiList = new ArrayList<>(databaseList.size());
        for (var message : databaseList) {
            uiList.add(convertMessage(message));
        }
        return uiList;
    }

    public boolean create(Message message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(message, headers);
        String suffix = restTemplate.postForObject(
                "http://localhost:8090/api/v1/analyze", request, String.class);
        if (suffix != null) {
            message.setText(message.getText() + suffix);
        }
        return messageRepository.create(convertMessage(message));
    }

    Message convertMessage(com.jejtuic.web_service.database.data_objects.Message message) {
        return new Message(
                message.getText(),
                message.getAuthor()
        );
    }

    com.jejtuic.web_service.database.data_objects.Message convertMessage(Message message) {
        return new com.jejtuic.web_service.database.data_objects.Message(
                0L,
                message.getText(),
                message.getAuthor()
        );
    }
}
