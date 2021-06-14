package com.myorg;

import software.amazon.awscdk.core.App;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class CdkWorkshopStackTest {
    private final static ObjectMapper JSON =
        new ObjectMapper().configure(SerializationFeature.INDENT_OUTPUT, true);

    @Test
    public void testStack() throws IOException {
        App app = new App();
        CdkWorkshopStack stack = new CdkWorkshopStack(app, "test");

        JsonNode actual = JSON.valueToTree(app.synth().getStackArtifact(stack.getArtifactId()).getTemplate());

        assertThat(actual.toString())
            .contains("AWS::DynamoDB::Table")
            .contains("AWS::ApiGateway::Resource")
            .contains("AWS::ApiGateway::Method")
            .contains("AWS::ApiGateway::RestApi")
            .contains("AWS::ApiGateway::Account")
            .contains("AWS::ApiGateway::Stage")
            .contains("AWS::ApiGateway::Deployment")
            .contains("AWS::IAM::Role")
            .contains("AWS::IAM::Policy")
            .contains("AWS::Lambda::Permission")
            .contains("AWS::Lambda::Function");
            //.contains("AWS::SQS::Queue")
            //.contains("AWS::SNS::Topic");
    }
}
