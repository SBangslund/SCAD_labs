package bang.scad.p01.handlers;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.AWSLambda;
import com.amazonaws.services.lambda.AWSLambdaClientBuilder;
import com.amazonaws.services.lambda.model.InvokeRequest;
import com.amazonaws.services.lambda.model.InvokeResult;

import bang.scad.p01.handlers.interfaces.Invoker;

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
        AWSCredentials credentials ;
        if (withKeys) {
            credentials = new BasicAWSCredentials(accessKey, secretKey);
        } else {
            ProfileCredentialsProvider provider = new ProfileCredentialsProvider("vscode_aws");
            credentials = provider.getCredentials();
        }
        AWSLambdaClientBuilder builder = AWSLambdaClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1);
        AWSLambda client = builder.build();
        InvokeRequest request = new InvokeRequest().withFunctionName(endpoint);
        InvokeResult result = client.invoke(request);
        return result.toString();
    }
}
