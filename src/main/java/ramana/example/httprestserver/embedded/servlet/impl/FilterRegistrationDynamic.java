package ramana.example.httprestserver.embedded.servlet.impl;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class FilterRegistrationDynamic implements FilterRegistration.Dynamic {
    public FilterRegistrationDynamic(ServletContextImpl servletContext, String s, Filter filter) {
    }

    @Override
    public void addMappingForServletNames(EnumSet<DispatcherType> enumSet, boolean b, String... strings) {

    }

    @Override
    public Collection<String> getServletNameMappings() {
        return null;
    }

    @Override
    public void addMappingForUrlPatterns(EnumSet<DispatcherType> enumSet, boolean b, String... strings) {

    }

    @Override
    public Collection<String> getUrlPatternMappings() {
        return null;
    }

    @Override
    public void setAsyncSupported(boolean b) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getClassName() {
        return null;
    }

    @Override
    public boolean setInitParameter(String s, String s1) {
        return false;
    }

    @Override
    public String getInitParameter(String s) {
        return null;
    }

    @Override
    public Set<String> setInitParameters(Map<String, String> map) {
        return null;
    }

    @Override
    public Map<String, String> getInitParameters() {
        return null;
    }
}
