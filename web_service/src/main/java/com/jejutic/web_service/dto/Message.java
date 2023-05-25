package com.jejutic.web_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {

    private Long id;
    private String text;
    private String author;
    private Integer rating;
}
