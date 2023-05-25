package com.jejutic.message_analyzer.domain;

import com.jejutic.message_analyzer.dto.Message;

public interface MessageAnalyzer {

    int analyze(Message message);
}
