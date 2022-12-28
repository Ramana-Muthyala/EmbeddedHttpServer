package ramana.example.httprestserver.embedded.servlet.impl;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class ServletInputStreamImpl extends ServletInputStream {
    private final ByteArrayInputStream in;
    private ReadListener readListener;

    public ServletInputStreamImpl(byte[] body) {
        if(body == null) body = new byte[0];
        in = new ByteArrayInputStream(body);
    }

    @Override
    public boolean isFinished() {
        return in.available() == 0;
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    public void setReadListener(ReadListener readListener) {
        this.readListener = readListener;
    }

    @Override
    public int read() throws IOException {
        int data = in.read();
        if(data == -1) {
            if(readListener != null) readListener.onAllDataRead();
        }
        return data;
    }
}
