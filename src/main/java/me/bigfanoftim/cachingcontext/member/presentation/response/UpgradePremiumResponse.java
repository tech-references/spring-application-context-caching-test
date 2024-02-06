package me.bigfanoftim.cachingcontext.member.presentation.response;

import lombok.Getter;

@Getter
public class UpgradePremiumResponse {

    private Long memberId;
    private String email;

    public UpgradePremiumResponse(Long memberId, String email) {
        this.memberId = memberId;
        this.email = email;
    }
}
