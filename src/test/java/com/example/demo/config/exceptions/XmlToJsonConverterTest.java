package com.example.demo.config.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class XmlToJsonConverterTest {

    @Test
    void testValidXmlConversion() {
        String xmlInput = "<root><name>John</name></root>";
        String expectedJsonOutput = "{\"name\":\"John\"}";

        XmlToJsonConverter converter = new XmlToJsonConverter();
        String jsonOutput = converter.convert(xmlInput);

        assertEquals(expectedJsonOutput, jsonOutput);
    }

    @Test
    void testInvalidXmlConversion() {
        String invalidXmlInput = "<root><name>John</root>"; // Invalid XML
        String expectedOutput = invalidXmlInput; // Should return the same invalid XML

        XmlToJsonConverter converter = new XmlToJsonConverter();
        String output = converter.convert(invalidXmlInput);

        assertEquals(expectedOutput, output);
    }
}