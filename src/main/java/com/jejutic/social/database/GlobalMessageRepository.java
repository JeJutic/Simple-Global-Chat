package com.jejutic.social.database;

import com.jejutic.social.database.data_objects.Message;

import java.util.List;

public interface GlobalMessageRepository {

    List<Message> findAll();
    boolean create(Message message);
}
