package com.example.demo.config.exceptions;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

class XmlToJsonConverter {

    String convertToJson(ErrorResponse errorResponse) throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);
        return jsonMapper.writeValueAsString(errorResponse);
    }
}





