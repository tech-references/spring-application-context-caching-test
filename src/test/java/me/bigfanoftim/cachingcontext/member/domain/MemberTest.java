package me.bigfanoftim.cachingcontext.member.domain;

import me.bigfanoftim.cachingcontext.common.model.Address;
import me.bigfanoftim.cachingcontext.common.model.Email;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MemberTest {

    @Test
    public void upgradeToPremium_현재_준회원이고_email형식이_구글이고_주소가_전부_등록되어_있으면_PREMIUM으로_업그레이드할_수_있다() throws Exception {
        // given
        Email email = new Email("bigfanoftim@gmail.com");
        Address address = new Address("zipcode1", "add1", "add2");
        Member member = Member.builder()
                .email(email)
                .address(address)
                .membershipType(MembershipType.ASSOCIATE)
                .build();

        // when
        member.upgradeToPremium();

        // then
        assertThat(member.getMembershipType()).isEqualTo(MembershipType.PREMIUM);
    }

    @Test
    public void upgradeToPremium_이미_프리미엄_회원이면_PERMIUM으로_업그레이드_시도할_시_예외가_발생한다() throws Exception {
        // given
        Email email = new Email("bigfanoftim@gmail.com");
        Address address = new Address("zipcode1", "add1", "add2");
        Member member = Member.builder()
                .email(email)
                .address(address)
                .membershipType(MembershipType.PREMIUM)
                .build();

        // when & then
        assertThatThrownBy(member::upgradeToPremium)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("already premium");
    }
}