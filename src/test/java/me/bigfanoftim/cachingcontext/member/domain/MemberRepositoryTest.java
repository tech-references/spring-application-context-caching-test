package me.bigfanoftim.cachingcontext.member.domain;

import me.bigfanoftim.cachingcontext.base.BaseRepositoryTest;
import me.bigfanoftim.cachingcontext.common.model.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemberRepositoryTest extends BaseRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void findByEmail_email에_매칭되는_Member가_존재하면_정상적으로_조회된다() throws Exception {
        // given
        final String GOOGLE_EMAIL = "bigfanoftim@gmail.com";
        Email email = new Email(GOOGLE_EMAIL);
        Member member = Member.builder()
                .email(email)
                .build();
        memberRepository.save(member);

        // when
        Optional<Member> memberOpt = memberRepository.findByEmail(email);

        // then
        assertThat(memberOpt).isNotEmpty();
        assertThat(memberOpt.get().getEmail()).isEqualTo(email);
    }

    @Test
    public void findByEmail_email에_매칭되는_Member가_존재하지_않으면_빈_Optional을_리턴한다() throws Exception {
        // given
        final String GOOGLE_EMAIL = "bigfanoftim@gmail.com";
        Email email = new Email(GOOGLE_EMAIL);

        // when
        Optional<Member> memberOpt = memberRepository.findByEmail(email);

        // then
        assertThat(memberOpt).isEmpty();
    }
}