EmbeddedHttpServer - Based on NIOTcpServer [https://github.com/Ramana-Muthyala/niotcpserver]
============================================================================================

Note: Compile with Java 8. Run on Java 8 or above.

Notes:
    1. Primitive implementation just to demonstrate how NIOTcpServer can be embedded into spring boot
        application. Full-fledged servlet container is not implemented.
    2. Tested with spring @RestController only.
    3. @ComponentScan("ramana.example.httprestserver.embedded") must be added to class annotated
        with @SpringBootApplication.
        For example: src/test/java/ramana/example/httprestserver/embedded/example/Application.java
    4. Logging for NIOTcpServer is enabled to track any exceptions as it is not a full-fledged
        servlet container implementation.
        Logging can be disabled in
        src/main/java/ramana/example/httprestserver/embedded/EmbeddedWebServer.java

Examples:
    src/test/java/ramana/example/httprestserver/embedded/example

