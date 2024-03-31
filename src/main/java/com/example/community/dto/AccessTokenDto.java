package com.example.community.dto;

import lombok.Data;

/**
 * Github AccessToken 访问参数
 */
@Data
public class AccessTokenDto {

    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
