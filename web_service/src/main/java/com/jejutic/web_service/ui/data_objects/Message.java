package com.jejutic.web_service.ui.data_objects;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    private String text;
    private String author;
}
