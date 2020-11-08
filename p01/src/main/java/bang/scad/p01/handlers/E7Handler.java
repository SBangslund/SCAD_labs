package bang.scad.p01.handlers;

import java.io.IOException;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class E7Handler implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        StringBuilder builder = new StringBuilder();
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/").get();
            for (Element element : doc.select("a[href]")) {
                builder.append(element.attr("href"));
            }
        } catch (IOException e) {}
        return builder.toString();
    }
}
