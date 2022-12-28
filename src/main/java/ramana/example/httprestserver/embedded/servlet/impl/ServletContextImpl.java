package ramana.example.httprestserver.embedded.servlet.impl;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class ServletContextImpl implements ServletContext {
    private Enumeration emptyEnumeration = Collections.emptyEnumeration();
    private HashMap<String, String> initParams = new HashMap<>();
    private HashMap attributes = new HashMap();
    private HashMap<String, Servlet> servlets = new HashMap<>();
    private HashMap<String, ServletRegistration> servletRegistrations = new HashMap<>();
    private HashMap<String, FilterRegistrationDynamic> filterRegistrations = new HashMap<String, FilterRegistrationDynamic>();
    private SessionCookieConfig sessionCookieConfig = new SessionCookieConfigImpl();
    private Set<SessionTrackingMode> sessionTrackingModes = new HashSet<>();
    private JspConfigDescriptor jspConfigDescriptor = new JspConfigDescriptorImpl();
    private int sessionTimeout;
    private String reqCharEncoding;
    private String resCharEncoding;

    @Override
    public String getContextPath() {
        return "/";
    }

    @Override
    public ServletContext getContext(String s) {
        return this;
    }

    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 0;
    }

    @Override
    public int getEffectiveMajorVersion() {
        return 0;
    }

    @Override
    public int getEffectiveMinorVersion() {
        return 0;
    }

    @Override
    public String getMimeType(String s) {
        return null;
    }

    @Override
    public Set<String> getResourcePaths(String s) {
        return null;
    }

    @Override
    public URL getResource(String s) throws MalformedURLException {
        return null;
    }

    @Override
    public InputStream getResourceAsStream(String s) {
        return null;
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        return null;
    }

    @Override
    public RequestDispatcher getNamedDispatcher(String s) {
        return null;
    }

    @Override
    public Servlet getServlet(String s) throws ServletException {
        return servlets.get(s);
    }

    @Override
    public Enumeration<Servlet> getServlets() {
        return null;
    }

    @Override
    public Enumeration<String> getServletNames() {
        return emptyEnumeration;
    }

    @Override
    public void log(String s) {

    }

    @Override
    public void log(Exception e, String s) {

    }

    @Override
    public void log(String s, Throwable throwable) {

    }

    @Override
    public String getRealPath(String s) {
        return null;
    }

    @Override
    public String getServerInfo() {
        return "EmbeddedWebServer";
    }

    @Override
    public String getInitParameter(String s) {
        return initParams.get(s);
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return Collections.enumeration(initParams.keySet());
    }

    @Override
    public boolean setInitParameter(String s, String s1) {
        if(initParams.get(s) == null) {
            initParams.put(s, s1);
            return true;
        }
        return false;
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
    public void setAttribute(String s, Object o) {
        if(o == null) removeAttribute(s);
        attributes.put(s, o);
    }

    @Override
    public void removeAttribute(String s) {
        attributes.remove(s);
    }

    @Override
    public String getServletContextName() {
        return null;
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String s, String s1) {
        try {
            return addServlet(s, (Class<? extends Servlet>) Class.forName(s1));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String s, Servlet servlet) {
        servlets.put(s, servlet);
        try {
            servlet.init(new ServletConfigImpl(s, this));
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        return new ServletRegistrationDynamicImpl(this, s, servlet);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String s, Class<? extends Servlet> aClass) {
        try {
            return addServlet(s, aClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ServletRegistration.Dynamic addJspFile(String s, String s1) {
        return new ServletRegistrationDynamicImpl(this, s, s1);
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> aClass) throws ServletException {
        try {
            return aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public ServletRegistration getServletRegistration(String s) {
        return servletRegistrations.get(s);
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        return servletRegistrations;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String s, String s1) {
        try {
            return addFilter(s, (Class<? extends Filter>) Class.forName(s1));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String s, Filter filter) {
        if(filterRegistrations.get(s) != null) {
            FilterRegistrationDynamic filterReg = new FilterRegistrationDynamic(this, s, filter);
            filterRegistrations.put(s, filterReg);
            return filterReg;
        }
        return null;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String s, Class<? extends Filter> aClass) {
        try {
            return addFilter(s, aClass.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> aClass) throws ServletException {
        try {
            return aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public FilterRegistration getFilterRegistration(String s) {
        return filterRegistrations.get(s);
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        return filterRegistrations;
    }

    @Override
    public SessionCookieConfig getSessionCookieConfig() {
        return sessionCookieConfig;
    }

    @Override
    public void setSessionTrackingModes(Set<SessionTrackingMode> set) {
        sessionTrackingModes.addAll(set);
    }

    @Override
    public Set<SessionTrackingMode> getDefaultSessionTrackingModes() {
        return sessionTrackingModes;
    }

    @Override
    public Set<SessionTrackingMode> getEffectiveSessionTrackingModes() {
        return sessionTrackingModes;
    }

    @Override
    public void addListener(String s) {

    }

    @Override
    public <T extends EventListener> void addListener(T t) {

    }

    @Override
    public void addListener(Class<? extends EventListener> aClass) {

    }

    @Override
    public <T extends EventListener> T createListener(Class<T> aClass) throws ServletException {
        try {
            return aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new ServletException(e);
        }
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return jspConfigDescriptor;
    }

    @Override
    public ClassLoader getClassLoader() {
        return getClass().getClassLoader();
    }

    @Override
    public void declareRoles(String... strings) {

    }

    @Override
    public String getVirtualServerName() {
        return "EmbeddedWebServer";
    }

    @Override
    public int getSessionTimeout() {
        return sessionTimeout;
    }

    @Override
    public void setSessionTimeout(int i) {
        sessionTimeout = i;
    }

    @Override
    public String getRequestCharacterEncoding() {
        return reqCharEncoding;
    }

    @Override
    public void setRequestCharacterEncoding(String s) {
        reqCharEncoding = s;
    }

    @Override
    public String getResponseCharacterEncoding() {
        return resCharEncoding;
    }

    @Override
    public void setResponseCharacterEncoding(String s) {
        resCharEncoding = s;
    }

    @Override
    public String toString() {
        return "ServletContextImpl{" +
                "initParams=" + initParams +
                ", attributes=" + attributes +
                ", servlets=" + servlets +
                ", servletRegistrations=" + servletRegistrations +
                ", filterRegistrations=" + filterRegistrations +
                ", sessionCookieConfig=" + sessionCookieConfig +
                ", sessionTrackingModes=" + sessionTrackingModes +
                ", jspConfigDescriptor=" + jspConfigDescriptor +
                ", sessionTimeout=" + sessionTimeout +
                ", reqCharEncoding='" + reqCharEncoding + '\'' +
                ", resCharEncoding='" + resCharEncoding + '\'' +
                '}';
    }
}
