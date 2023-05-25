package com.jejtuic.web_service.domain;

import com.jejtuic.web_service.ui.data_objects.Message;
import com.jejtuic.web_service.database.GlobalMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class GlobalMessageRepositoryProcessorImpl implements GlobalMessageRepositoryProcessor {

    private final GlobalMessageRepository messageRepository;
    private final RestTemplate restTemplate;

    @Value("${spring.application.microservice_analyzer.url}")
    private String messageAnalyzerURL;

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
    public void create(Message message) {
        messageRepository.create(convertMessage(message));
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

        try {
            var response = restTemplate.postForEntity(
                    messageAnalyzerURL + "/analyze", request, Integer.class);
            if (response.getStatusCode().isError()) {
                throw new RestClientException(
                        "Error status code from message analyzer microservice: " +
                                response);
            }
            message.setRating(response.getBody());
        } catch (RestClientException e) {
            log.error("Error in connection with message analyzer microservice: " +
                    e.getMessage());
        }
        // FIXME?: still sending if not handled, mb should add broker
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
