package io.forloop.proxy.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("proxy-storage")
public class Properties {

    private Feed feed;

}
