package by.logonuk.util;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class RequestBodyReader {

    public static StringBuilder readBody(HttpServletRequest request) throws IOException {

        BufferedReader reader = request.getReader();
        int intValueOfChar;
        StringBuilder result = new StringBuilder();

        while ((intValueOfChar = reader.read()) != -1) {
            result.append((char) intValueOfChar);
        }

        return result;
    }
}
