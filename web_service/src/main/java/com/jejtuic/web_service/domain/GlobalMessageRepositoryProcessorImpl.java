package com.jejtuic.web_service.domain;

import com.jejtuic.web_service.ui.data_objects.Message;
import com.jejtuic.web_service.database.GlobalMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GlobalMessageRepositoryProcessorImpl implements GlobalMessageRepositoryProcessor {

    private final GlobalMessageRepository messageRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public GlobalMessageRepositoryProcessorImpl(GlobalMessageRepository messageRepository, RestTemplate restTemplate) {
        this.messageRepository = messageRepository;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Message> findAll() {
        List<com.jejtuic.web_service.dto.Message> databaseList = messageRepository.findAll();
        List<Message> uiList = new ArrayList<>(databaseList.size());
        for (var message : databaseList) {
            uiList.add(convertMessage(message));
        }
        return uiList;
    }

    @Override
    public boolean create(Message message) {
        return messageRepository.create(convertMessage(message));
    }

    private void handleProUsers(Message message, int rating) {
        message.setText(message.getText() + "\r\nrating: " + rating);
    }

    private Message convertMessage(com.jejtuic.web_service.dto.Message message) {
        var result = new Message(
                message.getText(),
                message.getAuthor()
        );
        if (message.getRating() != null) {
            handleProUsers(result, message.getRating());
        }
        return result;
    }

    private void handleProUsers(com.jejtuic.web_service.dto.Message message) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(message, headers);
        var response = restTemplate.postForEntity(
                "http://localhost:8090/api/v1/analyze", request, Integer.class);

        if (!response.getStatusCode().isError()) {
            message.setRating(response.getBody());
        } else {
            log.error("Message analyzer microservice is unavailable, response code is: " +
                    response.getStatusCode().value());
        }
    }

    private com.jejtuic.web_service.dto.Message convertMessage(Message message) {
        var result = new com.jejtuic.web_service.dto.Message(
                0L,
                message.getText(),
                message.getAuthor(),
                null
        );
        if (message.getAuthor().endsWith("_pro")) {
            handleProUsers(result);
        }
        return result;
    }
}
