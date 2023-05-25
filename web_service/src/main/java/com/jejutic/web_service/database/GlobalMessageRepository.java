package com.jejutic.web_service.database;

import com.jejutic.web_service.dto.Message;

import java.util.List;

public interface GlobalMessageRepository {

    List<Message> findAll();
    void create(Message message);
}
