package me.bigfanoftim.cachingcontext.member.domain;

import me.bigfanoftim.cachingcontext.common.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(Email email);
}
