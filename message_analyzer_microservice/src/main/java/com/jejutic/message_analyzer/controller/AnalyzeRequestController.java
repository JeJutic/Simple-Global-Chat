package com.jejutic.message_analyzer.controller;

import com.jejutic.message_analyzer.domain.FullMessageAnalyzer;
import com.jejutic.message_analyzer.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Slf4j
public class AnalyzeRequestController {

    private final FullMessageAnalyzer messageAnalyzer;

    @Autowired
    public AnalyzeRequestController(FullMessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    @PostMapping("/analyze")
    public Integer randomEnding(@RequestBody Message message) {
        log.info("received message for analyzing: {}", message);
        return messageAnalyzer.analyze(message);
    }
}
