package bang.scad.libaws.invokers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import bang.scad.libaws.invokers.interfaces.Invoker;


public class HttpInvoker implements Invoker {

    @Override
    public String invoke(String endpoint) {
        return invokeRequest(RequestType.GET, endpoint, null);
    }

    @Override
    public String invoke(String endpoint, String payload) {
        return invokeRequest(RequestType.POST, endpoint, payload);
    }

    private HttpRequest buildRequest(RequestType type, String endpoint, String payload) {
        switch(type) {
            case GET:
                return HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .GET()
                    .build();
            case POST:
                return HttpRequest.newBuilder()
                    .uri(URI.create(endpoint))
                    .POST(BodyPublishers.ofString(payload))
                    .build();
        }
        return null;
    }

    private String invokeRequest(RequestType type, String endpoint, String payload) {
        String[] result = new String[1];
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = buildRequest(type, endpoint, payload);
        client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(s -> result[0] = s)
            .join();
        return result[0];
    }
}
