package com.example.community.provider;

import com.example.community.dto.AccessTokenDto;
import com.example.community.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSON;
import java.io.IOException;

@Component
public class GitHubProvider {

    /**
     * 获取AccessToken
     * @param accessTokenDto
     * @return
     */
    public String getAccessToken(AccessTokenDto accessTokenDto) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String[] res = string.split("&");
            if (res.length > 1) {
                String[] token = res[0].split("=");
                if (token.length > 1) {
                    return token[1];
                }
            }
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * 获取Github用户消息
     * @param accessToken
     * @return
     */
    public GithubUser getGithubUser(String accessToken) {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                //Token必须放在header中
                //.header("Authorization", "token " + accessToken)
                .header("Authorization", "Bearer " + accessToken)
                .url("https://api.github.com/user")
                .build();
        try (Response response = client.newCall(request).execute()) {
            return JSON.parseObject(response.body().string(), GithubUser.class);
        } catch (IOException e) {
        }
        return null;
    }
}
