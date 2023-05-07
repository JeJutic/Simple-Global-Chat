package com.jejutic.social.domain;

import com.jejutic.social.database.GlobalMessageRepository;
//import com.jejutic.social.ui.data_objects.Message;
//import com.jejutic.social.database.data_objects.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GlobalMessageRepositoryDelegator {

    private final GlobalMessageRepository messageRepository;

    @Autowired
    public GlobalMessageRepositoryDelegator(GlobalMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<com.jejutic.social.ui.data_objects.Message> findAll() {
        List<com.jejutic.social.database.data_objects.Message> databaseList = messageRepository.findAll();
        List<com.jejutic.social.ui.data_objects.Message> uiList = new ArrayList<>(databaseList.size());
        for (var message : databaseList) {
            uiList.add(convertMessage(message));
        }
        return uiList;
    }

    public boolean create(com.jejutic.social.ui.data_objects.Message message) {
        return messageRepository.create(convertMessage(message));
    }

    com.jejutic.social.ui.data_objects.Message convertMessage(com.jejutic.social.database.data_objects.Message message) {
        return new com.jejutic.social.ui.data_objects.Message(
                message.getText(),
                message.getAuthor()
        );
    }

    com.jejutic.social.database.data_objects.Message convertMessage(com.jejutic.social.ui.data_objects.Message message) {
        return new com.jejutic.social.database.data_objects.Message(
                0L,
                message.getText(),
                message.getAuthor()
        );
    }
}
