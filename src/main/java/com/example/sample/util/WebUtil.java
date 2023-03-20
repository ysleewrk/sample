package com.example.sample.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class WebUtil {

    public static String encode(String text) {
        String result = "";
        try {
            result = URLEncoder.encode(text, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            result = "";
        }
        return result;
    }

    public static String decode(String text) {
        String result = "";
        try {
            result = URLDecoder.decode(text, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            result = "";
        }
        return result;
    }
}
