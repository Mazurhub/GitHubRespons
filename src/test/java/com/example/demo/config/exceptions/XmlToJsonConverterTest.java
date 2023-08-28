package com.example.demo.config.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlToJsonConverterTest {

    @Test
    void testErrorResponseToJsonConversion() throws Exception {
        // Arrange
        ErrorResponse errorResponse = new ErrorResponse("406", "Not Acceptable - Unsupported Media Type");
        String expectedJsonOutput = "{\"status\":\"406\",\"message\":\"Not Acceptable - Unsupported Media Type\"}";


        XmlToJsonConverter converter = new XmlToJsonConverter();
        String jsonOutput = converter.convertToJson(errorResponse);
        assertEquals(expectedJsonOutput, jsonOutput);
    }

    @Test
    void testInvalidXmlConversion() throws Exception {
        // Arrange
        String invalidXmlInput = "<root><name>Not Acceptable - Unsupported Media Type</root>"; // Invalid XML
        ErrorResponse errorResponse = new ErrorResponse("406", invalidXmlInput);
        String expectedOutput = "{\"status\":\"406\",\"message\":\"<root><name>Not Acceptable - Unsupported Media Type</root>\"}";

        XmlToJsonConverter converter = new XmlToJsonConverter();

        // Act
        String output = converter.convertToJson(errorResponse);

        // Assert
        assertEquals(expectedOutput, output);
    }
}




