package com.green.greengram.application.pay.medel;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KakaoPaySessionDto {
    private String tid;
    private String partnerOrderId;
    private String partnerUserId;
}
