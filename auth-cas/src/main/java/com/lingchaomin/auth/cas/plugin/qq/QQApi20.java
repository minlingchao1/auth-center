package com.lingchaomin.auth.cas.plugin.qq;

import org.scribe.builder.api.DefaultApi20;
import org.scribe.model.OAuthConfig;
import org.scribe.model.Verb;
import org.scribe.utils.OAuthEncoder;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午5:54
 * @description 用于定义获取微信返回的CODE与ACCESS_TOKEN
 */
public class QQApi20 extends DefaultApi20{

    public static final String QQ_AUTHORIZE_URL="https://graph.qq.com/oauth2.0/authorize?client_id=%s&redirect_uri=%s&response_type=code&state=qqlogin";

    @Override
    public String getAccessTokenEndpoint() {
        return "https://graph.qq.com/oauth2.0/token";
    }




    @Override
    public Verb getAccessTokenVerb() {
        return Verb.GET;
    }

    @Override
    public String getAuthorizationUrl(OAuthConfig oAuthConfig) {
        return String.format(QQ_AUTHORIZE_URL, oAuthConfig.getApiKey(), OAuthEncoder.encode(oAuthConfig.getCallback()));

    }
}
