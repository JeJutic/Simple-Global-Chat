package com.jejutic.message_analyzer.controller;

import com.jejutic.message_analyzer.domain.FullMessageAnalyzer;
import com.jejutic.message_analyzer.dto.Message;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
    public ResponseEntity<Integer> randomEnding(@Valid @RequestBody Message message, Errors errors) {
        log.info("received message for analyzing: {}", message);
        if (errors.hasErrors()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Received Message object is invalid: " + errors);
        }
        return ResponseEntity.ok().body(messageAnalyzer.analyze(message));
    }
}
