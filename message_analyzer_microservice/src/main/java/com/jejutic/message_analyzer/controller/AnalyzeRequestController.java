package com.jejutic.message_analyzer.controller;
import com.jejutic.message_analyzer.dto.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public interface AnalyzeRequestController {

    ResponseEntity<Integer> findRating(Message message, Errors errors);

}
