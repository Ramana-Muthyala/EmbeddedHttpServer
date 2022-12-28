package ramana.example.httprestserver.embedded.servlet.impl;

import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.descriptor.JspPropertyGroupDescriptor;
import javax.servlet.descriptor.TaglibDescriptor;
import java.util.Collection;

public class JspConfigDescriptorImpl implements JspConfigDescriptor {
    @Override
    public Collection<TaglibDescriptor> getTaglibs() {
        return null;
    }

    @Override
    public Collection<JspPropertyGroupDescriptor> getJspPropertyGroups() {
        return null;
    }
}
