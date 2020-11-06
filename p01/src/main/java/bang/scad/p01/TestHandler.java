package bang.scad.p01;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

import java.util.Map;

import com.google.gson.Gson;

public class TestHandler implements RequestHandler<Map<String, String>, String>{

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Super interesting thing has happened!");
        TestModel model = new TestModel("Samuel", "Bangslund");
        Gson gson = new Gson();

        return gson.toJson(model);
    }
    
}
