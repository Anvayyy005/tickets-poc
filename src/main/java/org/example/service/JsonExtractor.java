package org.example.service;

public class JsonExtractor {

    public String extract(String text) {
        int start = text.indexOf("{");
        int end = text.lastIndexOf("}");
        return (start >= 0 && end > start)
                ? text.substring(start, end + 1)
                : text;
    }
}
