package com.example.HRM_practice.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.jetbrains.annotations.NotNull;

public class AccessTokenDTO {

    @NotNull
    @Schema(description = "jwt token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessTokenDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessTokenDTO(){}

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("{accessToken:'").append(accessToken).append('\'');
        sb.append("}");
        return sb.toString();
    }
}
