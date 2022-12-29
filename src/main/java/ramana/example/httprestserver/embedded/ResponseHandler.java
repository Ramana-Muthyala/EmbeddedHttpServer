package ramana.example.httprestserver.embedded;

import ramana.example.httprestserver.embedded.servlet.impl.HttpServletResponseImpl;
import ramana.example.httprestserver.embedded.servlet.impl.Util;
import ramana.example.niotcpserver.codec.http.response.ResponseMessage;

import java.util.ArrayList;

public class ResponseHandler {
    public void handle(HttpServletResponseImpl servletResponse) {
        ResponseMessage responseMessage = servletResponse.responseMessage;
        if(responseMessage.statusCode == 0) responseMessage.statusCode = ramana.example.niotcpserver.codec.http.Util.STATUS_OK;
        responseMessage.headers = servletResponse.headers;

        if(servletResponse.contentType != null) {
            ArrayList<String> values = new ArrayList<>(1);
            String tmp = servletResponse.charEncoding == null
                    ? servletResponse.contentType : servletResponse.contentType + "; " + servletResponse.charEncoding;
            values.add(tmp);
            responseMessage.headers.put(Util.CONTENT_TYPE, values);
        }

        responseMessage.body = servletResponse.out.toByteArray();
        ArrayList<String> values = new ArrayList<>(1);
        values.add(String.valueOf(responseMessage.body.length));
        responseMessage.headers.put(ramana.example.niotcpserver.codec.http.Util.REQ_HEADER_CONTENT_LENGTH, values);
    }
}
