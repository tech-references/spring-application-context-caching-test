package me.bigfanoftim.cachingcontext.common.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EmailTest {

    @Test
    public void isGoogle_email이_구글형식이면_true를_리턴한다() throws Exception {
        final String GOOGLE_EMAIL = "bigfanoftim@gmail.com";
        Email email = new Email(GOOGLE_EMAIL);

        assertThat(email.isGoogle()).isTrue();
    }

    @Test
    public void isGoogle_email이_구글형식이_아니면_false를_리턴한다() throws Exception {
        final String GOOGLE_EMAIL = "bigfanoftim@naver.com";
        Email email = new Email(GOOGLE_EMAIL);

        assertThat(email.isGoogle()).isFalse();
    }
}