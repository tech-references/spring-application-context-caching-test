package me.bigfanoftim.cachingcontext.member.application;

import me.bigfanoftim.cachingcontext.base.BaseServiceTest;
import me.bigfanoftim.cachingcontext.common.model.Address;
import me.bigfanoftim.cachingcontext.common.model.Email;
import me.bigfanoftim.cachingcontext.member.NoMemberException;
import me.bigfanoftim.cachingcontext.member.domain.Member;
import me.bigfanoftim.cachingcontext.member.domain.MemberRepository;
import me.bigfanoftim.cachingcontext.member.domain.MembershipType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ChangeMembershipServiceTest extends BaseServiceTest {

    @InjectMocks
    private ChangeMembershipService changeMembershipService;

    @Mock
    private MemberRepository memberRepository;

    @Test
    public void upgradePremium_이메일에_매칭되는_Member가_조회되면_PREMIUM으로_업그레이드할_수_있다() throws Exception {
        // given
        Member member = createAssociateMember();
        Member spiedMember = Mockito.spy(member);
        when(memberRepository.findByEmail(spiedMember.getEmail()))
                .thenReturn(Optional.of(spiedMember));

        // when
        changeMembershipService.upgradePremium(spiedMember.getEmail());

        // then
        verify(spiedMember, Mockito.times(1)).upgradeToPremium();
        assertThat(spiedMember.getMembershipType()).isEqualTo(MembershipType.PREMIUM);
    }

    @Test
    public void upgradePremium_이메일에_매칭되는_Member가_조회되지_않으면_NoMemberException을_던진다() throws Exception {
        // given
        Email email = new Email("wrong");
        when(memberRepository.findByEmail(email))
                .thenReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> { changeMembershipService.upgradePremium(email); })
                .isInstanceOf(NoMemberException.class);
    }

    private Member createAssociateMember() {
        Email email = new Email("bigfanoftim@gmail.com");
        Address address = new Address("zipcode", "add1", "add2");
        return Member.builder()
                .email(email)
                .address(address)
                .membershipType(MembershipType.ASSOCIATE)
                .build();
    }
}