package ramana.example.httprestserver.embedded.servlet.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Util {
    public static final String CONTENT_TYPE = "Content-Type";

    public static String getQueryString(Map<String, String> queryParameters) {
        if(queryParameters == null || queryParameters.size() == 0) return null;
        StringBuilder queryString = new StringBuilder();
        Iterator<String> keys = queryParameters.keySet().iterator();
        while (keys.hasNext()) {
            String key = keys.next();
            queryString.append(key).append("=").append(queryParameters.get(key));
            if(keys.hasNext()) queryString.append("&");
        }
        return queryString.toString();
    }

    public static Map<String, String[]> getQueryParameterMap(Map<String, String> queryParameters) {
        if(queryParameters == null) return null;
        Map<String, String[]> params = new HashMap<>();
        for (Map.Entry<String, String> entry: queryParameters.entrySet()) {
            params.put(entry.getKey(), new String[] {entry.getValue()});
        }
        return params;
    }
}
