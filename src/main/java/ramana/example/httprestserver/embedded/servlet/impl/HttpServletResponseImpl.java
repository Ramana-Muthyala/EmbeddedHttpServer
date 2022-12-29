package ramana.example.httprestserver.embedded.servlet.impl;

import ramana.example.niotcpserver.codec.http.response.ResponseMessage;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class HttpServletResponseImpl implements HttpServletResponse {

    public final ResponseMessage responseMessage;
    public final Map<String, ArrayList<String>> headers = new HashMap<>();
    public String charEncoding;
    public String contentType;
    private int contentLength;
    private int bufferSize;
    public ByteArrayOutputStream out;
    private Locale locale;

    public HttpServletResponseImpl(ResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
        out = new ByteArrayOutputStream(1024 * 8);
    }

    @Override
    public void addCookie(Cookie cookie) {

    }

    @Override
    public boolean containsHeader(String s) {
        return responseMessage.headers.containsKey(s);
    }

    @Override
    public String encodeURL(String s) {
        return s;
    }

    @Override
    public String encodeRedirectURL(String s) {
        return s;
    }

    @Override
    public String encodeUrl(String s) {
        return s;
    }

    @Override
    public String encodeRedirectUrl(String s) {
        return s;
    }

    @Override
    public void sendError(int i, String s) throws IOException {
        sendError(i);
    }

    @Override
    public void sendError(int i) throws IOException {
        responseMessage.statusCode = i;
    }

    @Override
    public void sendRedirect(String s) throws IOException {

    }

    @Override
    public void setDateHeader(String s, long l) {
        ArrayList<String> value = headers.get(s);
        if(value == null) {
            value = new ArrayList<>();
            headers.put(s, value);
        } else {
            value.clear();
        }
        value.add(new Date(l).toGMTString());
    }

    @Override
    public void addDateHeader(String s, long l) {
        ArrayList<String> value = headers.computeIfAbsent(s, k -> new ArrayList<>());
        value.add(new Date(l).toGMTString());
    }

    @Override
    public void setHeader(String s, String s1) {
        ArrayList<String> value = headers.get(s);
        if(value == null) {
            value = new ArrayList<>();
            headers.put(s, value);
        } else {
            value.clear();
        }
        value.add(s1);
    }

    @Override
    public void addHeader(String s, String s1) {
        ArrayList<String> value = headers.computeIfAbsent(s, k -> new ArrayList<>());
        value.add(s1);
    }

    @Override
    public void setIntHeader(String s, int i) {
        ArrayList<String> value = headers.get(s);
        if(value == null) {
            value = new ArrayList<>();
            headers.put(s, value);
        } else {
            value.clear();
        }
        value.add(String.valueOf(i));
    }

    @Override
    public void addIntHeader(String s, int i) {
        ArrayList<String> value = headers.computeIfAbsent(s, k -> new ArrayList<>());
        value.add(String.valueOf(i));
    }

    @Override
    public void setStatus(int i) {
        responseMessage.statusCode = i;
    }

    @Override
    public void setStatus(int i, String s) {
        setStatus(i);
    }

    @Override
    public int getStatus() {
        return responseMessage.statusCode;
    }

    @Override
    public String getHeader(String s) {
        List<String> value = headers.get(s);
        if(value == null || value.size() == 0) return null;
        return value.get(0);
    }

    @Override
    public Collection<String> getHeaders(String s) {
        return headers.get(s) == null ? Collections.emptyList() : headers.get(s);
    }

    @Override
    public Collection<String> getHeaderNames() {
        return headers.keySet();
    }

    @Override
    public String getCharacterEncoding() {
        return charEncoding;
    }

    @Override
    public String getContentType() {
        return contentType;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        return new ServletOutputStreamImpl(out);
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return new PrintWriter(out);
    }

    @Override
    public void setCharacterEncoding(String s) {
        charEncoding = s;
    }

    @Override
    public void setContentLength(int i) {
        contentLength = i;
    }

    @Override
    public void setContentLengthLong(long l) {
        setContentLength((int) l);
    }

    @Override
    public void setContentType(String s) {
        contentType = s;
    }

    @Override
    public void setBufferSize(int i) {
        bufferSize = i;
    }

    @Override
    public int getBufferSize() {
        return bufferSize;
    }

    @Override
    public void flushBuffer() throws IOException {

    }

    @Override
    public void resetBuffer() {

    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {

    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }
}
