package com.liukch.accp;

import com.liukch.accp.awssdk.VertxSdkClient;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import org.aeonbits.owner.ConfigFactory;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liukch on 2019-11-14
 */
    public class MainVerticle extends AbstractVerticle {

    Vertx vertx;

    DynamoDbAsyncClient client;

    DynamoDbConfig dynamoDbConfig;

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        dynamoDbConfig = ConfigFactory.create(DynamoDbConfig.class);
        initDynamoDbClient();
        getPlayer();
        startPromise.complete();
    }

    @Override
    public void stop(Promise<Void> stopPromise) throws Exception {
        stopPromise.complete();
    }

    private void getPlayer() {
        final int[] totalNum = {0};
        vertx.setPeriodic(1000, timerId -> {
            for (int i = 0; i < dynamoDbConfig.times(); i++) {
                client.getItem(getItemRequest()).whenComplete((response, err) -> {
                    if (response != null) {
                        System.out.println(totalNum[0]++);
                    }
                });
            }
        });

    }

    private GetItemRequest getItemRequest() {
        Map<String, AttributeValue> keyToGet = new HashMap<>();
        keyToGet.put("id", AttributeValue.builder().n("1000").build());
        return GetItemRequest.builder()
                .tableName("liukch.test")
                .key(keyToGet)
                .build();
    }


    private void initDynamoDbClient() {
        vertx = context.owner();

        var builder = DynamoDbAsyncClient.builder()
                .region(Region.of(dynamoDbConfig.region()));

        if (dynamoDbConfig.overrideEndpoint()) {
            try {
                builder.endpointOverride(new URI(dynamoDbConfig.endpointUrl()));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        }
        client = VertxSdkClient.withVertx(builder, context).build();
    }

}
