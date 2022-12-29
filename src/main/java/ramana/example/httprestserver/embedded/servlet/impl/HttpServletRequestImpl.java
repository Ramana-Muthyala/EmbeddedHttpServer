package ramana.example.httprestserver.embedded.servlet.impl;

import ramana.example.niotcpserver.codec.http.request.v1.RequestMessage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.security.Principal;
import java.util.*;

public class HttpServletRequestImpl implements HttpServletRequest {
    private final RequestMessage requestMessage;
    private final ServletContextImpl servletContext;
    private final Map<String, ArrayList<String>> headers;
    private final int port;
    private final HashMap<String, Object> attributes = new HashMap<>();
    private String encoding;

    public HttpServletRequestImpl(int port, RequestMessage requestMessage, ServletContextImpl servletContext) {
        this.port = port;
        this.requestMessage = requestMessage;
        this.servletContext = servletContext;
        headers = requestMessage.headers;
    }

    @Override
    public String getAuthType() {
        return null;
    }

    @Override
    public Cookie[] getCookies() {
        return new Cookie[0];
    }

    @Override
    public long getDateHeader(String s) {
        return 0;
    }

    @Override
    public String getHeader(String s) {
        List<String> header = headers.get(s);
        if(header == null) return null;
        if(header.size() > 0) return header.get(0);
        return null;
    }

    @Override
    public Enumeration<String> getHeaders(String s) {
        List<String> header = headers.get(s);
        if(header == null) return null;
        return Collections.enumeration(header);
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        return Collections.enumeration(headers.keySet());
    }

    @Override
    public int getIntHeader(String s) {
        List<String> header = headers.get(s);
        if(header == null) return -1;
        if(header.size() > 0) {
            return Integer.parseInt(header.get(0));
        }
        return -1;
    }

    @Override
    public String getMethod() {
        return requestMessage.method;
    }

    @Override
    public String getPathInfo() {
        return "/" + requestMessage.path;
    }

    @Override
    public String getPathTranslated() {
        return "/" + requestMessage.path;
    }

    @Override
    public String getContextPath() {
        return "/";
    }

    @Override
    public String getQueryString() {
        return Util.getQueryString(requestMessage.queryParameters);
    }

    @Override
    public String getRemoteUser() {
        return null;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public Principal getUserPrincipal() {
        return null;
    }

    @Override
    public String getRequestedSessionId() {
        return null;
    }

    @Override
    public String getRequestURI() {
        return "/" + requestMessage.path;
    }

    @Override
    public StringBuffer getRequestURL() {
        return new StringBuffer().append("/").append(requestMessage.path);
    }

    @Override
    public String getServletPath() {
        return "/";
    }

    @Override
    public HttpSession getSession(boolean b) {
        return null;
    }

    @Override
    public HttpSession getSession() {
        return null;
    }

    @Override
    public String changeSessionId() {
        return null;
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        return false;
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        return false;
    }

    @Override
    public boolean authenticate(HttpServletResponse httpServletResponse) throws IOException, ServletException {
        return false;
    }

    @Override
    public void login(String s, String s1) throws ServletException {

    }

    @Override
    public void logout() throws ServletException {

    }

    @Override
    public Collection<Part> getParts() throws IOException, ServletException {
        return null;
    }

    @Override
    public Part getPart(String s) throws IOException, ServletException {
        return null;
    }

    @Override
    public <T extends HttpUpgradeHandler> T upgrade(Class<T> aClass) throws IOException, ServletException {
        return null;
    }

    @Override
    public Object getAttribute(String s) {
        return attributes.get(s);
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return Collections.enumeration(attributes.keySet());
    }

    @Override
    public String getCharacterEncoding() {
        return encoding;
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {
        encoding = s;
    }

    @Override
    public int getContentLength() {
        return requestMessage.body == null ? 0 : requestMessage.body.length;
    }

    @Override
    public long getContentLengthLong() {
        return getContentLength();
    }

    @Override
    public String getContentType() {
        List<String> values = headers.get("Content-Type");
        if(values == null) return null;
        if(values.size() > 0) return values.get(0);
        return null;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new ServletInputStreamImpl(requestMessage.body);
    }

    @Override
    public String getParameter(String s) {
        return requestMessage.queryParameters == null ? null : requestMessage.queryParameters.get(s);
    }

    @Override
    public Enumeration<String> getParameterNames() {
        return requestMessage.queryParameters == null ? null : Collections.enumeration(requestMessage.queryParameters.keySet());
    }

    @Override
    public String[] getParameterValues(String s) {
        if(requestMessage.queryParameters == null) return null;
        String value = requestMessage.queryParameters.get(s);
        if(value == null) return null;
        return new String[] {value};
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return Util.getQueryParameterMap(requestMessage.queryParameters);
    }

    @Override
    public String getProtocol() {
        return "HTTP/1.1";
    }

    @Override
    public String getScheme() {
        return "http";
    }

    @Override
    public String getServerName() {
        return null;
    }

    @Override
    public int getServerPort() {
        return port;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        byte[] body = requestMessage.body == null ? new byte[0] : requestMessage.body;
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(body)));
    }

    @Override
    public String getRemoteAddr() {
        return null;
    }

    @Override
    public String getRemoteHost() {
        return null;
    }

    @Override
    public void setAttribute(String s, Object o) {
        attributes.put(s, o);
    }

    @Override
    public void removeAttribute(String s) {
        attributes.remove(s);
    }

    @Override
    public Locale getLocale() {
        return null;
    }

    @Override
    public Enumeration<Locale> getLocales() {
        return null;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public int getRemotePort() {
        return 0;
    }

    @Override
    public String getLocalName() {
        return null;
    }

    @Override
    public String getLocalAddr() {
        return null;
    }

    @Override
    public int getLocalPort() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public AsyncContext startAsync() throws IllegalStateException {
        return null;
    }

    @Override
    public AsyncContext startAsync(ServletRequest servletRequest, ServletResponse servletResponse) throws IllegalStateException {
        return null;
    }

    @Override
    public boolean isAsyncStarted() {
        return false;
    }

    @Override
    public boolean isAsyncSupported() {
        return false;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return null;
    }

    @Override
    public DispatcherType getDispatcherType() {
        return null;
    }
}
