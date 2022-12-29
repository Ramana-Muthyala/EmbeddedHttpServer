package ramana.example.httprestserver.embedded;

import ramana.example.httprestserver.embedded.servlet.impl.HttpServletRequestImpl;
import ramana.example.httprestserver.embedded.servlet.impl.HttpServletResponseImpl;
import ramana.example.httprestserver.embedded.servlet.impl.ServletContextImpl;
import ramana.example.niotcpserver.codec.http.Util;
import ramana.example.niotcpserver.codec.http.request.v1.RequestMessage;
import ramana.example.niotcpserver.codec.http.response.ResponseMessage;
import ramana.example.niotcpserver.codec.http.v1.Processor;
import ramana.example.niotcpserver.log.LogFactory;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RequestProcessor implements Processor {
    private static final Logger logger = LogFactory.getLogger();
    private final Servlet dispatcherServlet;
    private final ServletContextImpl servletContext;

    private static final ArrayList<String> values = new ArrayList<>(1);
    static {
        values.add(String.valueOf(0));
    }

    private final ResponseHandler responseHandler = new ResponseHandler();
    private final int port;

    public RequestProcessor(int port, ServletContextImpl servletContext) {
        try {
            this.port = port;
            this.servletContext = servletContext;
            this.dispatcherServlet = servletContext.getServlet("dispatcherServlet");
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void process(RequestMessage requestMessage, ResponseMessage responseMessage) {
        logger.info("requestMessage: " + requestMessage.method + ": " + requestMessage.path);

        HttpServletRequestImpl servletRequest = new HttpServletRequestImpl(port, requestMessage, servletContext);
        HttpServletResponseImpl servletResponse = new HttpServletResponseImpl(responseMessage);
        try {
            dispatcherServlet.service(servletRequest, servletResponse);
            responseHandler.handle(servletResponse);
        } catch (ServletException | IOException | RuntimeException e) {
            logger.log(Level.INFO, e.getMessage(), e);
            internalServerError(responseMessage);
        }
    }

    private void internalServerError(ResponseMessage responseMessage) {
        responseMessage.statusCode = Util.STATUS_INTERNAL_SERVER_ERROR;
        responseMessage.headers.clear();
        responseMessage.body = null;
        responseMessage.headers.put(Util.REQ_HEADER_CONTENT_LENGTH, values);
    }
}
