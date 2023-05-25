package com.jejutic.message_analyzer.controller;

import com.jejutic.message_analyzer.dto.Message;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AnalyzeRequestControllerTest {

    private final Errors errors = Mockito.mock(Errors.class);

    @Autowired
    private AnalyzeRequestController controller;

    @ParameterizedTest
    @MethodSource
    void ratingValue(Message message, int expectedRating) {
        var response = controller.findRating(message, errors);

        assertFalse(response.getStatusCode().isError());
        assertEquals(expectedRating, response.getBody());
    }

    static Stream<Arguments> ratingValue() {
        return Stream.of(
                Arguments.arguments(new Message("mymail@mymail.com"), 3),
                Arguments.arguments(new Message("hawa u doing"), 0)
        );
    }
}