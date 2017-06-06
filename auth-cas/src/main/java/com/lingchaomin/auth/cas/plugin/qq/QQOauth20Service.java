package com.lingchaomin.auth.cas.plugin.qq;

import org.apache.commons.lang3.StringUtils;
import org.scribe.builder.api.DefaultApi20;
import org.scribe.exceptions.OAuthException;
import org.scribe.model.OAuthConfig;
import org.scribe.model.OAuthConstants;
import org.scribe.model.OAuthRequest;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.ProxyOAuth20ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午6:05
 * @description 用于添加获取access_token与用户信息添加参数并请求qq
 */
public class QQOauth20Service extends ProxyOAuth20ServiceImpl {

    private static final Logger log= LoggerFactory.getLogger(QQOauth20Service.class);

    private static Pattern acceccTokenPattern = Pattern.compile("\"access_token\":\\s*\"(\\S*?)\"");

    private static Pattern openIdPattern = Pattern.compile("\"openid\":\\s*\"(\\S*?)\"");
    private String OPEN_ID_URL = "https://graph.qq.com/oauth2.0/me";


    public QQOauth20Service(DefaultApi20 api, OAuthConfig config, int connectTimeout, int readTimeout, String proxyHost, int proxyPort) {
        super(api, config, connectTimeout, readTimeout, proxyHost, proxyPort);
    }

    /**
     * 获取account_token的http请求参数添加
     */
    @Override
    public Token getAccessToken(Token requestToken, Verifier verifier) {
        final OAuthRequest request = new ProxyOAuthRequest(this.api.getAccessTokenVerb(),
                this.api.getAccessTokenEndpoint(), this.connectTimeout,
                this.readTimeout, this.proxyHost, this.proxyPort);
        request.addQuerystringParameter("client_id", this.config.getApiKey());
        request.addQuerystringParameter("client_secret", this.config.getApiSecret());
        request.addQuerystringParameter(OAuthConstants.CODE, verifier.getValue());
        request.addQuerystringParameter(OAuthConstants.REDIRECT_URI, this.config.getCallback());
        request.addQuerystringParameter("grant_type", "authorization_code");
        final Response response = request.send();
        return this.api.getAccessTokenExtractor().extract(response.getBody());
    }

    @Override
    public void signRequest(final Token accessToken, final OAuthRequest request) {
        request.addQuerystringParameter(OAuthConstants.ACCESS_TOKEN, accessToken.getToken());
        String response = accessToken.getRawResponse();
        String at=parseAcceccToken(response);
        if(StringUtils.isNotEmpty(at)){
            String openId=getOpenID(at);
            request.addQuerystringParameter("openid", openId);
            request.addOAuthParameter("oauth_openid",openId);
        }
        else{
            throw new OAuthException("qq接口返回数据miss access token: " + response);
        }
    }



    private String parseAcceccToken(String response){
        String[] respArr=response.split("&");
        String[] accArr=respArr[0].split("=");
        return accArr[1];
    }

    /**
     * 获取openid
     * @param accessToken
     * @return
     */
    private String getOpenID(String accessToken) {

        final OAuthRequest request = new ProxyOAuthRequest(this.api.getAccessTokenVerb(),
                OPEN_ID_URL, this.connectTimeout,
                this.readTimeout, this.proxyHost, this.proxyPort);

        request.addQuerystringParameter("access_token",accessToken);
        final Response response = request.send();

        Matcher matcher = openIdPattern.matcher(response.getBody());

        if(matcher.find()){
            String openId=matcher.group(1);
            //log.info("get openid:{}",openId);
            return openId;
        }else {
            throw new OAuthException("qq接口返回数据miss openId: " + response);
        }
    }
}
