package ramana.example.httprestserver.embedded;

import ramana.example.httprestserver.embedded.servlet.impl.HttpServletResponseImpl;
import ramana.example.httprestserver.embedded.servlet.impl.Util;
import ramana.example.niotcpserver.codec.http.request.Field;
import ramana.example.niotcpserver.codec.http.response.ResponseMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ResponseHandler {
    public void handle(HttpServletResponseImpl servletResponse) {
        ResponseMessage responseMessage = servletResponse.responseMessage;
        if(responseMessage.statusCode == 0) responseMessage.statusCode = ramana.example.niotcpserver.codec.http.Util.STATUS_OK;
        for (Map.Entry<String, List<String>> entry: servletResponse.headers.entrySet()) {
            responseMessage.headers.add(new Field(entry.getKey(), (ArrayList<String>) entry.getValue()));
        }

        if(servletResponse.contentType != null) {
            responseMessage.headers = responseMessage.headers.stream().filter(field -> !Util.CONTENT_TYPE.equals(field.name)).collect(Collectors.toList());
            ArrayList<String> values = new ArrayList<>(1);
            String tmp = servletResponse.charEncoding == null
                    ? servletResponse.contentType : servletResponse.contentType + "; " + servletResponse.charEncoding;
            values.add(tmp);
            responseMessage.headers.add(new Field(Util.CONTENT_TYPE, values));
        }

        responseMessage.body = servletResponse.out.toByteArray();
        ArrayList<String> values = new ArrayList<>(1);
        values.add(String.valueOf(responseMessage.body.length));
        responseMessage.headers = responseMessage.headers.stream().filter(field -> !ramana.example.niotcpserver.codec.http.Util.REQ_HEADER_CONTENT_LENGTH.equals(field.name)).collect(Collectors.toList());
        responseMessage.headers.add(new Field(ramana.example.niotcpserver.codec.http.Util.REQ_HEADER_CONTENT_LENGTH, values));
    }
}
