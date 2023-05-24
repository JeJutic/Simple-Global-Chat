package com.jejtuic.web_service.domain;

import com.jejtuic.web_service.ui.data_objects.Message;

import java.util.List;

public interface GlobalMessageRepositoryProcessor {
    List<Message> findAll();

    boolean create(Message message);
}
