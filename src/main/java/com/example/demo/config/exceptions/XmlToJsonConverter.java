package com.example.demo.config.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

class XmlToJsonConverter {

    String convert(String xml) {
        try {
            return new ObjectMapper().writeValueAsString(new XmlMapper().readTree(xml));
        } catch (Exception e) {
            e.printStackTrace();
            return xml;
        }
    }
}