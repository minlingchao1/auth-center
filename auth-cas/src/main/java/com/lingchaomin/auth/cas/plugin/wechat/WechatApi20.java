package com.lingchaomin.auth.cas.plugin.wechat;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.extractors.AccessTokenExtractor;
import org.scribe.extractors.JsonTokenExtractor;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午2:42
 * @description 用于定义获取微信返回的CODE与ACCESS_TOKEN
 */
public class WechatApi20 extends DefaultApi20{

    private static final String WEICHAT_AUTHORIZE_URL = "https://open.weixin.qq.com/connect/qrconnect?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_login#wechat_redirect";

    @Override
    public AccessTokenExtractor getAccessTokenExtractor() {
        return new JsonTokenExtractor();
    }

    @Override
    public Verb getAccessTokenVerb() {
        return Verb.POST;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://api.weixin.qq.com/sns/oauth2/access_token";
    }




    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {
        return String.format(WEICHAT_AUTHORIZE_URL, oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback()));

    }
}
