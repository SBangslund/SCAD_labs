package bang.scad.libaws.invokers;

import java.nio.charset.StandardCharsets;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

import bang.scad.libaws.invokers.interfaces.Invoker;

public class AWSInvoker implements Invoker {

    private final String accessKey, secretKey;
    private boolean withKeys = true;

    public AWSInvoker(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public AWSInvoker() {
        this("", "");
        withKeys = false;
    }

    @Override
    public String invoke(String endpoint) {
        return invokeRequest(RequestType.GET, endpoint, null);
    }
    
    @Override
    public String invoke(String endpoint, String payload) {
        return invokeRequest(RequestType.POST, endpoint, payload);
    }

    private InvokeRequest buildRequest(RequestType type, String endpoint, String payload){
        switch(type) {
            case GET:
                return new InvokeRequest()
                    .withFunctionName(endpoint);
            case POST:
                return new InvokeRequest()
                    .withFunctionName(endpoint)
                    .withPayload(payload);
        }
        return null;
    }

    private String invokeRequest(RequestType type, String endpoint, String payload) {
        AWSCredentials credentials ;
        if (withKeys) {
            credentials = new BasicAWSCredentials(accessKey, secretKey);
        } else {
            ProfileCredentialsProvider provider = new ProfileCredentialsProvider();
            credentials = provider.getCredentials();
        }
        AWSLambda client = AWSLambdaClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_1)
            .build();
        InvokeRequest request = buildRequest(type, endpoint, payload);
        InvokeResult result = client.invoke(request);
        return new String(result.getPayload().array(), StandardCharsets.UTF_8);
    }
}
