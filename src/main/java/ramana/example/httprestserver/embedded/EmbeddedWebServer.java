package ramana.example.httprestserver.embedded;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.server.WebServerException;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import ramana.example.httprestserver.embedded.servlet.impl.ServletContextImpl;
import ramana.example.niotcpserver.Bootstrap;
import ramana.example.niotcpserver.Server;
import ramana.example.niotcpserver.codec.http.handler.v1.CodecChannelHandler;
import ramana.example.niotcpserver.codec.http.handler.v1.ProcessorChannelHandler;
import ramana.example.niotcpserver.codec.http.v1.Processor;
import ramana.example.niotcpserver.log.LogFactory;
import ramana.example.niotcpserver.util.CompletionSignal;
import ramana.example.niotcpserver.util.Util;

import javax.servlet.ServletException;
import java.util.logging.Logger;

public class EmbeddedWebServer implements WebServer {
    private static final Logger logger = LogFactory.getLogger();
    private static final int port = 8080;
    private final Server server;
    private final Bootstrap bootStrap;
    private boolean startInvoked;
    private static final ServletContextImpl servletContext = new ServletContextImpl();

    public EmbeddedWebServer(ServletContextInitializer[] initializers) {
        bootStrap = new Bootstrap().listen(port)
                .enableDefaultRead()
                .enableLogging()
                .numOfWorkers(4)
                .channelHandler(CodecChannelHandler.class)
                .channelHandler(ChannelHandler.class);
        server = new Server(bootStrap);
        for (ServletContextInitializer initializer: initializers) {
            try {
                initializer.onStartup(servletContext);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void start() throws WebServerException {
        long beginTime = System.currentTimeMillis();
        logger.info("NioTcpServer: Workers: " + bootStrap.getNumWorkers());
        logger.info("Channel Handlers: " + Util.normalizeClassName(bootStrap.getChannelHandlers()));
        try {
            startInvoked = true;
            server.start();
            logger.info("Listening on port: " + port + ", Time taken: " + ((double)(System.currentTimeMillis() - beginTime) / 1000) + " sec");
        } catch (CompletionSignal.CompletionSignalException | InterruptedException e) {
            throw new WebServerException(e.getMessage(), e);
        }
    }

    @Override
    public void stop() throws WebServerException {
        try {
            if(startInvoked) server.shutDown(false);
        } catch (IllegalStateException exception) {
            // ignore
        }
    }

    @Override
    public int getPort() {
        return port;
    }

    public static class ChannelHandler extends ProcessorChannelHandler {
        private static final RequestProcessor requestProcessor = new RequestProcessor(port, servletContext);
        @Override
        protected Processor create() {
            return requestProcessor;
        }
    }
}
