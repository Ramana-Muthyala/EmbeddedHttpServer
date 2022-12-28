package ramana.example.httprestserver.embedded.servlet.impl;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServletOutputStreamImpl extends ServletOutputStream {
    private final ByteArrayOutputStream out;
    private WriteListener writeListener;

    public ServletOutputStreamImpl(ByteArrayOutputStream out) {
        this.out = out;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setWriteListener(WriteListener writeListener) {
        this.writeListener = writeListener;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }
}
