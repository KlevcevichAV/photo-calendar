package com.klevtcevichav.photocalendar.auth.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("auth")
public interface AuthClientApi {
}
