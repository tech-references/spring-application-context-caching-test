package me.bigfanoftim.cachingcontext.member.integration;

import me.bigfanoftim.cachingcontext.base.BaseIntegrationTest;
import me.bigfanoftim.cachingcontext.common.model.Address;
import me.bigfanoftim.cachingcontext.common.model.Email;
import me.bigfanoftim.cachingcontext.member.domain.Member;
import me.bigfanoftim.cachingcontext.member.domain.MemberRepository;
import me.bigfanoftim.cachingcontext.member.domain.MembershipType;
import me.bigfanoftim.cachingcontext.member.presentation.request.UpgradePremiumRequest;
import me.bigfanoftim.cachingcontext.member.presentation.response.UpgradePremiumResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

public class ChangeMemberShipIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void upgradePremium_주어진_email에_매칭되는_Member를_PREMIUM으로_업그레이드할_수_있다() throws Exception {
        // given
        final String emailStr = "bigfanoftim@gmail.com";
        createAssociateMember(emailStr);
        UpgradePremiumRequest upgradePremiumRequest = new UpgradePremiumRequest(emailStr);

        // when
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<UpgradePremiumRequest> httpEntity = new HttpEntity<>(upgradePremiumRequest, httpHeaders);
        ResponseEntity<UpgradePremiumResponse> response = restTemplate.exchange(defaultUrl + "/membership/premium", HttpMethod.POST, httpEntity, UpgradePremiumResponse.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        // PREMIUM으로 업그레이드 되었는지 검증
        Long memberId = response.getBody().getMemberId();
        Member member = memberRepository.findById(memberId).get();
        assertThat(member.getMembershipType()).isEqualTo(MembershipType.PREMIUM);
    }

    private void createAssociateMember(String emailStr) {
        Email email = new Email(emailStr);
        Address address = new Address("zipcode", "addr1", "addr2");
        Member member = Member.builder()
                .email(email)
                .address(address)
                .membershipType(MembershipType.ASSOCIATE)
                .build();
        memberRepository.save(member);
    }
}
