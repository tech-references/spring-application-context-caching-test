package me.bigfanoftim.cachingcontext.member.presentation;

import lombok.RequiredArgsConstructor;
import me.bigfanoftim.cachingcontext.common.model.Email;
import me.bigfanoftim.cachingcontext.member.application.ChangeMembershipService;
import me.bigfanoftim.cachingcontext.member.domain.Member;
import me.bigfanoftim.cachingcontext.member.presentation.request.UpgradePremiumRequest;
import me.bigfanoftim.cachingcontext.member.presentation.response.UpgradePremiumResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ChangeMembershipController {

    private final ChangeMembershipService changeMembershipService;

    @PostMapping("/membership/premium")
    public UpgradePremiumResponse upgradePremium(@RequestBody UpgradePremiumRequest upgradePremiumRequest) {
        Email email = new Email(upgradePremiumRequest.getEmail());
        Member member = changeMembershipService.upgradePremium(email);

        return new UpgradePremiumResponse(member.getId(), member.getEmail().getValue());
    }
}
