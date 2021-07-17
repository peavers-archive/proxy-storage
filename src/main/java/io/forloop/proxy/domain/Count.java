package io.forloop.proxy.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Count {

    private long validatedTrue;

    private long validatedFalse;

    private long total;

    public long getTotal() {
        return this.validatedTrue + this.validatedFalse;
    }
}
