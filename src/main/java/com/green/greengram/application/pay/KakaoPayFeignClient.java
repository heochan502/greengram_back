package com.green.greengram.application.pay;


import com.green.greengram.application.pay.medel.KakaoPayApproveFeignReq;
import com.green.greengram.application.pay.medel.KakaoPayApproveRes;
import com.green.greengram.application.pay.medel.KakaoPayReadyFeignReq;
import com.green.greengram.application.pay.medel.KakaoPayReadyRes;
import com.green.greengram.config.feignclient.KakaoPayClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kakaoPayApi"
        , url = "${constants.pay.kakao.base-url}"
        , configuration = { KakaoPayClientConfiguration.class })
public interface KakaoPayFeignClient {

    @PostMapping(value = "/ready")
    KakaoPayReadyRes postReady(@RequestBody KakaoPayReadyFeignReq req);

    @PostMapping(value = "/approve")
    KakaoPayApproveRes postApprove(KakaoPayApproveFeignReq req);
}
