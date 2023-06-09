package com.jejutic.web_service.domain;

import com.jejutic.web_service.ui.data_objects.Message;

import java.util.List;

public interface GlobalMessageRepositoryProcessor {
    List<Message> findAll();

    void create(Message message);
}
