package me.bigfanoftim.cachingcontext.common.model;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Address {

    private String zipCode;
    private String address1;
    private String address2;

    public boolean isComplete() {
        return zipCode != null && !zipCode.isEmpty()
                && address1 != null && !address1.isEmpty()
                && address2 != null && !address2.isEmpty();
    }
}
