package com.lingchaomin.auth.cas.plugin.wechat;

import com.fasterxml.jackson.databind.JsonNode;
import com.lingchaomin.auth.cas.authentication.CombinedAccountVO;
import com.lingchaomin.auth.cas.authentication.LoginKeyType;

import org.pac4j.core.client.BaseClient;
import org.pac4j.core.context.WebContext;
import org.pac4j.core.exception.HttpCommunicationException;
import org.pac4j.oauth.client.BaseOAuth20Client;
import org.pac4j.oauth.credentials.OAuthCredentials;
import org.pac4j.oauth.profile.JsonHelper;
import org.scribe.model.OAuthConfig;
import org.scribe.model.ProxyOAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午2:30
 * @description 用于与微信通信
 */
public class WechatClient extends BaseOAuth20Client<WechatProfile>{

    private static WechatAttributeDefinition WECHAT_ATTRIBUTES=new WechatAttributeDefinition();


    public WechatClient() {
    }

    public WechatClient(String key,String secret){
        setKey(key);
        setSecret(secret);
    }

    /**
     *
     * @return
     */
    @Override
    protected BaseClient<OAuthCredentials, WechatProfile> newClient() {
        WechatClient wechatClient=new WechatClient();
        return wechatClient;
    }

    @Override
    protected void internalInit() {
        super.internalInit();
        WechatApi20 wechatApi20=new WechatApi20();
        this.service = new WechatOauth20Serveice(wechatApi20, new OAuthConfig(this.key, this.secret, this.callbackUrl, SignatureType.Header, null, null),
                this.connectTimeout, this.readTimeout, this.proxyHost,this.proxyPort);
    }


    @Override
    protected String getProfileUrl() {
        return "https://api.weixin.qq.com/sns/userinfo";
    }

    @Override
    protected WechatProfile extractUserProfile(String body) {
        WechatProfile weiXinProfile = new WechatProfile();
        final JsonNode json = JsonHelper.getFirstNode(body);
        if (null != json) {
            for(final String attribute : WECHAT_ATTRIBUTES.getPrincipalAttributes()){
                weiXinProfile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }

            /** 绑定账号到系统 */
            String openId = (String) weiXinProfile.getAttributes().get("openid");
            String nickName = (String) weiXinProfile.getAttributes().get("nickname");
            CombinedAccountVO combinedAccount = generateAccount(openId, LoginKeyType.WECHAT, nickName);
            //Pair<Long,String> suidAndLoginName = customerManager.bindAccount(combinedAccount);
//            weiXinProfile.addAttribute("suid", suidAndLoginName.getFirst());
//            weiXinProfile.setId(suidAndLoginName.getSecond());
        }
        return weiXinProfile;
    }

    @Override
    protected boolean hasBeenCancelled(WebContext webContext) {
        return false;
    }

    @Override
    protected boolean requiresStateParameter() {
        return false;
    }


    @Override
    protected String sendRequestForData(final Token accessToken, final String dataUrl) {
        logger.debug("accessToken : {} / dataUrl : {}", accessToken, dataUrl);
        final long t0 = System.currentTimeMillis();
        final ProxyOAuthRequest request = createProxyRequest(dataUrl);
        this.service.signRequest(accessToken, request);

        final Response response = request.send();
        final int code = response.getCode();
        final String body = response.getBody();
        final long t1 = System.currentTimeMillis();
        logger.debug("Request took : " + (t1 - t0) + " ms for : " + dataUrl);
        logger.debug("response code : {} / response body : {}", code, body);
        if (code != 200) {
            logger.error("Failed to get data, code : " + code + " / body : " + body);
            throw new HttpCommunicationException(code, body);
        }
        return body;
    }

    private CombinedAccountVO generateAccount(String openId, LoginKeyType keyType, String nickName){
        CombinedAccountVO vo = new CombinedAccountVO();
        vo.setLoginKey(openId);
        vo.setKeyType(keyType);
        vo.setNickName(nickName);
        return vo;
    }




}
