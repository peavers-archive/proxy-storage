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

    private String value;

    private boolean validated;

    private boolean inUse;

    public String host() {
        return this.value.split(":")[0];
    }

    public int port() {
        return Integer.parseInt(this.value.split(":")[1]);
    }
}
