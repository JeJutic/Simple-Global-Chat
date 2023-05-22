package com.jejtuic.web_service.database.data_objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    private Long id;
    private String text;
    private String author;
}