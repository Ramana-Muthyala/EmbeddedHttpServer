package ramana.example.httprestserver.embedded;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmbeddedWebServerFactory implements ServletWebServerFactory {
    @Override
    public WebServer getWebServer(ServletContextInitializer... initializers) {
        return new EmbeddedWebServer(initializers);
    }
}
