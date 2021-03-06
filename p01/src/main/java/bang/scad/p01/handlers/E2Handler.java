package bang.scad.p01.handlers;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;

public class E2Handler implements RequestHandler<Map<String, String>, String> {

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        return String.valueOf(fib(20));
    }

    private int fib(int n) {
        return n <= 1 ? n : fib(n - 1) + fib(n - 2);
    }
}
