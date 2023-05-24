package com.jejtuic.web_service.database;

import com.jejtuic.web_service.dto.Message;

import java.util.List;

public interface GlobalMessageRepository {

    List<Message> findAll();
    void create(Message message);
}
