package com.jejutic.message_analyzer.controller;

import com.jejutic.message_analyzer.dto.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AnalyzeRequestController {

    @PostMapping("/analyze")
    public String randomEnding(@RequestBody Message message) {
        return "RandomEnding";
    }
}
