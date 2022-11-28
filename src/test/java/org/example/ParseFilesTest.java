package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class ParseFilesTest {

    @ParameterizedTest
    @MethodSource("expectedAnswer")
    void parse() {
    }

    private static Stream<Arguments> expectedAnswer() {
        return Stream.of(
            Arguments.of(),
            Arguments.of(),
            Arguments.of()
        );
    }

    @Test
    void testParse() {
    }

    @Test
    void parseString() {
    }

    @Test
    void parseDate() {
    }
}