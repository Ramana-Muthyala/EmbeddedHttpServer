package ramana.example.httprestserver.embedded.servlet.impl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import java.util.Collections;
import java.util.Enumeration;

public class ServletConfigImpl implements ServletConfig {
    private final ServletContext servletContext;
    private final String name;

    public ServletConfigImpl(String name, ServletContext servletContext) {
        this.servletContext = servletContext;
        this.name = name;
    }

    @Override
    public String getServletName() {
        return name;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    @Override
    public String getInitParameter(String s) {
        return null;
    }

    @Override
    public Enumeration<String> getInitParameterNames() {
        return Collections.emptyEnumeration();
    }
}
