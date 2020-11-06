package bang.scad.p01.handlers;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import bang.scad.p01.handlers.interfaces.Invoker;

public class HttpInvoker implements Invoker {

    @Override
    public String invoke(String endpoint) {
        String[] result = new String[1];

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint))
            .GET()
            .build();
        client.sendAsync(request, BodyHandlers.ofString())
            .thenApply(HttpResponse::body)
            .thenAccept(s -> result[0] = s)
            .join();

        return result[0];
    }
}
