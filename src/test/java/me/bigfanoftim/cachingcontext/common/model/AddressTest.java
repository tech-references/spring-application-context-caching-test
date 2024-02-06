package me.bigfanoftim.cachingcontext.common.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AddressTest {

    @Test
    public void isComplete_주소가_모두_빈_스트링과_null이_아니면_true를_리턴한다() throws Exception {
        // given
        Address address = new Address("ZIP", "주소1", "주소2");

        // when & then
        assertThat(address.isComplete()).isTrue();
    }

    @Test
    public void isComplete_주소중_하나라도_null이_있으면_false를_리턴한다() throws Exception {
        // given
        Address addressWithNull = new Address(null, "주소1", "주소2");

        // when & then
        assertThat(addressWithNull.isComplete()).isFalse();
    }

    @Test
    public void isComplete_주소중_하나라도_빈_스트링이_있으면_false를_리턴한다() throws Exception {
        // given
        Address addressWithNull = new Address("", "주소1", "주소2");

        // when & then
        assertThat(addressWithNull.isComplete()).isFalse();
    }
}