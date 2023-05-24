package com.jejutic.message_analyzer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Message {

    @NotNull
    private String text;
}
