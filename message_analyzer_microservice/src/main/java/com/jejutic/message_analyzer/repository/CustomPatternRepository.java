package com.jejutic.message_analyzer.repository;

import com.jejutic.message_analyzer.dto.CustomPattern;

import java.util.List;

public interface CustomPatternRepository {

    List<CustomPattern> findAll();
}
