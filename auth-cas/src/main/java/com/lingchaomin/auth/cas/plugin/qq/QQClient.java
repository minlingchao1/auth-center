package com.lingchaomin.auth.cas.plugin.qq;

import com.fasterxml.jackson.databind.JsonNode;
import com.lingchaomin.auth.cas.authentication.CombinedAccountVO;
import com.lingchaomin.auth.cas.authentication.LoginKeyType;
import com.lingchaomin.auth.cas.authentication.OauthClient;
import com.lingchaomin.auth.cas.service.QQUserService;

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

import java.util.Map;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午5:41
 * @description 用于与qq进行通信
 */
public class QQClient extends BaseOAuth20Client<QQProfile> {

    private static QQAttributeDefinition QQ_ATTRIBUTES=new QQAttributeDefinition();

    private static final String APP_ID="101290627";

    private static final String OPENID_SPILT="##yunbeu##";

    private QQUserService qqUserService;

    public QQClient() {
    }

    public QQClient(String key,String secret){
        setKey(key);
        setSecret(secret);
    }

    @Override
    protected BaseClient<OAuthCredentials, QQProfile> newClient() {
        QQClient qqClient=new QQClient();
        return qqClient;
    }

    @Override
    protected void internalInit() {
        super.internalInit();
        QQApi20 qqApi20=new QQApi20();
        this.service = new QQOauth20Service(qqApi20, new OAuthConfig(this.key, this.secret, this.callbackUrl, SignatureType.Header, null, null),
                this.connectTimeout, this.readTimeout, this.proxyHost,this.proxyPort);
    }

    @Override
    protected String getProfileUrl() {
        return "https://graph.qq.com/user/get_user_info";
    }


    @Override
    protected QQProfile extractUserProfile(String body) {

        logger.info(body);
        String[] bodys=body.split(OPENID_SPILT);

        QQProfile qqProfile=new QQProfile();
        final JsonNode json= JsonHelper.getFirstNode(bodys[1]);
        if(null!=json){
            for(final String attribute : QQ_ATTRIBUTES.getPrincipalAttributes()){
                qqProfile.addAttribute(attribute, JsonHelper.get(json, attribute));
            }
            qqProfile.addAttribute(QQAttributeDefinition.OPEN_ID,bodys[0]);
            qqProfile.addAttribute(QQAttributeDefinition.OAUTH_CLIENT, OauthClient.QQ);
            /** 绑定账号到系统 */
            String openId = (String) qqProfile.getAttributes().get("openid");
            String nickName = (String) qqProfile.getAttributes().get("nickname");
            String figureUrl=(String)qqProfile.getAttributes().get("figureurl");

            //qqUserService.insert(openId,nickName,figureUrl);

            CombinedAccountVO combinedAccount = generateAccount(openId, LoginKeyType.QQ, nickName);

           // Pair<Long,String> suidAndLoginName = customerManager.bindAccount(combinedAccount);
            qqProfile.addAttribute("suid", combinedAccount.getKeyType()+"#"+combinedAccount.getLoginKey());
            qqProfile.setId(combinedAccount.getLoginKey());
        }
        return qqProfile;
    }



    @Override
    protected boolean requiresStateParameter() {
        return false;
    }

    @Override
    protected boolean hasBeenCancelled(WebContext webContext) {
        return false;
    }

    @Override
    protected String sendRequestForData(final Token accessToken, final String dataUrl) {
        logger.info("accessToken : {} / dataUrl : {}", accessToken, dataUrl);
        final long t0 = System.currentTimeMillis();
        final ProxyOAuthRequest request = createProxyRequest(dataUrl);
        this.service.signRequest(accessToken, request);

        request.addQuerystringParameter("access_token",accessToken.getToken());
        request.addQuerystringParameter("oauth_consumer_key",APP_ID);


        Map<String,String> map=request.getOauthParameters();

        String openId=map.get("oauth_openid");

        final Response response = request.send();
        final int code = response.getCode();
        final String body = response.getBody();
        final long t1 = System.currentTimeMillis();
        logger.info("Request took : " + (t1 - t0) + " ms for : " + dataUrl);
        logger.info("response code : {} / response body : {}", code, body);
        if (code != 200) {
            logger.error("Failed to get data, code : " + code + " / body : " + body);
            throw new HttpCommunicationException(code, body);
        }

        String result=openId+OPENID_SPILT+body;
        return result;
    }

    private CombinedAccountVO generateAccount(String openId, LoginKeyType keyType, String nickName){
        CombinedAccountVO vo = new CombinedAccountVO();
        vo.setLoginKey(openId);
        vo.setKeyType(keyType);
        vo.setNickName(nickName);
        return vo;
    }



    public void setQqUserService(QQUserService qqUserService) {
        this.qqUserService = qqUserService;
    }
}
