package com.liukch.accp;

import org.aeonbits.owner.Config;


@Config.Sources({
    "file:accp.properties",
})
public interface DynamoDbConfig extends Config {

    @Key("times")
    @DefaultValue("100")
    int times();

    @Key("region")
    @DefaultValue("cn-north-1")
    String region();

    @Key("overrideEndpoint")
    @DefaultValue("true")
    boolean overrideEndpoint();

    @Key("endpointUrl")
    @DefaultValue("http://localhost:8000")
    String endpointUrl();

}
