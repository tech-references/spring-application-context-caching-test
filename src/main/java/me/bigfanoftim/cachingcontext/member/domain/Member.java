package me.bigfanoftim.cachingcontext.member.domain;

import jakarta.persistence.*;
import lombok.*;
import me.bigfanoftim.cachingcontext.common.model.Address;
import me.bigfanoftim.cachingcontext.common.model.Email;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "members")
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Email email;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)
    private MembershipType membershipType;

    /**
     * 요구사항1: Google 이메일 형식이며, 주소가 전부 등록되어 있는 멤버는 멤버쉽을 PREMIUM으로 업그레이드할 수 있다.
     */
    public void upgradeToPremium() {
        verifyPremiumMembershipEligibility();
        this.membershipType = MembershipType.PREMIUM;
    }

    private void verifyPremiumMembershipEligibility() {
        if (membershipType == MembershipType.PREMIUM) {
            throw new IllegalStateException("already premium");
        }

        if (!email.isGoogle() || !address.isComplete()) {
            throw new IllegalStateException("not qualified");
        }
    }
}
