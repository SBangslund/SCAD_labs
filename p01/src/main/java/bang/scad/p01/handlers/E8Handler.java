package bang.scad.p01.handlers;

import java.util.Map;
import java.util.HashMap;	

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import bang.scad.p01.models.RuntimeInfo;

public class E8Handler implements RequestHandler<Map<String, String>, RuntimeInfo> {

    @Override
    public RuntimeInfo handleRequest(Map<String, String> input, Context context) {
        Runtime r = Runtime.getRuntime();
        Map<String, String> systemProperties = new HashMap<>();
        System.getProperties().forEach((key, value) -> {
            systemProperties.put(key.toString(), value.toString());
        });
        RuntimeInfo info = new RuntimeInfo(
            systemProperties,
            r.availableProcessors(),
            r.freeMemory(),
            r.maxMemory(),
            r.totalMemory(),
            r.version().toString()
        );
        return info;
    }
}
