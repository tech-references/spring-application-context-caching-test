package me.bigfanoftim.cachingcontext.member.application;

import lombok.RequiredArgsConstructor;
import me.bigfanoftim.cachingcontext.common.model.Email;
import me.bigfanoftim.cachingcontext.member.NoMemberException;
import me.bigfanoftim.cachingcontext.member.domain.Member;
import me.bigfanoftim.cachingcontext.member.domain.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeMembershipService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member upgradePremium(Email email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(NoMemberException::new);
        member.upgradeToPremium();

        return member;
    }
}
