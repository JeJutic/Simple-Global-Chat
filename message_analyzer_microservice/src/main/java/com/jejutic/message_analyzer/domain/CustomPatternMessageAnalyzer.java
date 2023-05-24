package com.jejutic.message_analyzer.domain;

import com.jejutic.message_analyzer.dto.CustomPattern;
import com.jejutic.message_analyzer.dto.Message;
import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

@AllArgsConstructor
public class CustomPatternMessageAnalyzer implements MessageAnalyzer {

    private final CustomPattern pattern;

    @Override
    public int analyze(Message message) {
        return Pattern.matches(
                pattern.getRegexp(), message.getText()
        ) ? 1 : 0;
    }
}
