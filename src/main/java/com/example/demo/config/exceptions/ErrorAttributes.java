package com.example.demo.config.exceptions;


import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);

        Map<String, Object> simplifiedErrorAttributes = new LinkedHashMap<>();
        simplifiedErrorAttributes.put("status", errorAttributes.get("status"));
        simplifiedErrorAttributes.put("message", errorAttributes.get("message"));

        return simplifiedErrorAttributes;
    }
}





