package io.forloop.proxy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proxy {

    private String id;

    private String host;

    private int port;

    private boolean validated;

    private boolean inUse;

    @Override
    public String toString() {
        return "%s:%d".formatted(this.host, this.port);
    }
}
