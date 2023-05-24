package com.jejutic.message_analyzer.domain;

import com.jejutic.message_analyzer.dto.CustomPattern;
import com.jejutic.message_analyzer.dto.Message;
import com.jejutic.message_analyzer.repository.CustomPatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FullMessageAnalyzer implements MessageAnalyzer {

    private final CustomPatternRepository customPatternRepository;

    @Autowired
    public FullMessageAnalyzer(CustomPatternRepository customPatternRepository) {
        this.customPatternRepository = customPatternRepository;
    }

    @Override
    public int analyze(Message message) {
        List<CustomPattern> customPatterns = customPatternRepository.findAll();
        int result = 0;
        for (CustomPattern pattern : customPatterns) {
            result += new CustomPatternMessageAnalyzer(pattern).analyze(message);
        }
        return result;
    }
}
