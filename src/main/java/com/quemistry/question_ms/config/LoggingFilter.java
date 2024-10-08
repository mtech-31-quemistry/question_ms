package com.quemistry.question_ms.config;

import com.quemistry.question_ms.util.MaskUtil;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        BufferedRequestWrapper bufferedRequest = new BufferedRequestWrapper(request);
//        BufferedResponseWrapper bufferedResponse = new BufferedResponseWrapper(response);

        // Now you can read the buffered request in the filter
        String requestBody = extractRequestBody(bufferedRequest);
        Map<String, String> headers = extractHeaders(request);
        String url = request.getRequestURL().toString();
        boolean isHealthCheck = (url.contains("health") || url.contains("actuator"));

//        // log information from the httpServletRequest like url, params, etc
        if (!isHealthCheck) {
            log.info("REQUEST RECEIVED, path: {}, method: {}, headers: {}, body: {},",request.getRequestURL(), request.getMethod(), headers, requestBody);
        }
        filterChain.doFilter(bufferedRequest, servletResponse);
        // log information regarding the httpServletResponse like status code, etc
        Map<String, String> responseHeaders = extractResponseHeaders(response);
//        String responseBody = extractResponseBody(bufferedResponse); // not working yet

        if (!isHealthCheck) {
            log.info("RESPONSE, path: {}, method: {}, headers: {}, status: {}", request.getRequestURL(), request.getMethod(), responseHeaders, response.getStatus());
        }
        //        log.info("RESPONSE, path: {}, method: {}, headers: {}, status: {}, response: {}",request.getRequestURL(), request.getMethod(), responseHeaders, response.getStatus(), responseBody);
        // Ensure the response body is written back to the client
//        bufferedResponse.copyBodyToResponse();
    }

    private Map<String, String> extractResponseHeaders(HttpServletResponse response) {
        Map<String, String> headers = new HashMap<>();
        for (String headerName : response.getHeaderNames()) {
            headers.put(headerName, response.getHeader(headerName));
        }
        return headers;
    }

    private Map<String, String> extractHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headers = new HashMap<>();

        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            headers.put(headerName, headerValue);
        }

        return Collections.unmodifiableMap(headers);
    }

    private String extractRequestBody(HttpServletRequest request) {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            bufferedReader = request.getReader();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            // Handle IOException
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    // Handle IOException
                }
            }
        }
        return MaskUtil.applyMask(stringBuilder.toString());
    }

    // Wrapper class to buffer the request body
    private static class BufferedRequestWrapper extends HttpServletRequestWrapper {
        private final byte[] buffer;

        public BufferedRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            buffer = ServletUtils.toByteArray(request.getInputStream());
        }

        @Override
        public ServletInputStream getInputStream() {
            return new BufferedServletInputStream(buffer);
        }

        @Override
        public BufferedReader getReader() {
            ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
            return new BufferedReader(new InputStreamReader(bais));
        }
    }

    // Custom ServletInputStream implementation to wrap the buffered request body
    private static class BufferedServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream bais;

        public BufferedServletInputStream(byte[] buffer) {
            this.bais = new ByteArrayInputStream(buffer);
        }

        @Override
        public int read() {
            return bais.read();
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public boolean isReady() {
            return false;
        }

        @Override
        public void setReadListener(ReadListener listener) {
            // nothing needed
        }

    }

//    private String extractResponseBody(BufferedResponseWrapper response) {
//        byte[] buf = response.getContentAsByteArray();
//        return MaskUtil.applyMask(new String(buf));
//    }
//
//    private static class BufferedResponseWrapper extends HttpServletResponseWrapper {
//        private final ByteArrayOutputStream buffer = new ByteArrayOutputStream();
//        private ServletOutputStream outputStream;
//        private PrintWriter writer;
//
//        public BufferedResponseWrapper(HttpServletResponse response) {
//            super(response);
//        }
//
//        @Override
//        public ServletOutputStream getOutputStream() {
//            if (outputStream == null) {
//                outputStream = new BufferedServletOutputStream(buffer);
//            }
//            return outputStream;
//        }
//
//        @Override
//        public PrintWriter getWriter() {
//            if (writer == null) {
//                writer = new PrintWriter(buffer);
//            }
//            return writer;
//        }
//
//        public byte[] getContentAsByteArray() {
//            if (writer != null) {
//                writer.flush();
//            } else if (outputStream != null) {
//                try {
//                    outputStream.flush();
//                } catch (IOException e) {
//                    // Handle IOException
//                }
//            }
//            return buffer.toByteArray();
//        }
//
//        public void copyBodyToResponse() throws IOException {
//            byte[] bytes = getContentAsByteArray();
//            ServletOutputStream out = getResponse().getOutputStream();
//            out.write(bytes);
//            out.flush();
//        }
//    }
//
//    private static class BufferedServletOutputStream extends ServletOutputStream {
//        private final ByteArrayOutputStream baos;
//
//        public BufferedServletOutputStream(ByteArrayOutputStream baos) {
//            this.baos = baos;
//        }
//
//        @Override
//        public void write(int b) throws IOException {
//            baos.write(b);
//        }
//
//        @Override
//        public boolean isReady() {
//            return true;
//        }
//
//        @Override
//        public void setWriteListener(WriteListener listener) {
//            // Nothing needed
//        }
//    }


    // Utility class to convert InputStream to byte[]
    private static class ServletUtils {
        public static byte[] toByteArray(java.io.InputStream is) throws IOException {
            try (java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream()) {
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, length);
                }
                return baos.toByteArray();
            }
        }
    }
}
