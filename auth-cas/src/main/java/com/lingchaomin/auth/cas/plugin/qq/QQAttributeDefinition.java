package com.lingchaomin.auth.cas.plugin.qq;

import org.pac4j.core.profile.converter.Converters;
import org.pac4j.oauth.profile.OAuthAttributesDefinition;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午5:43
 * @description
 */
public class QQAttributeDefinition extends OAuthAttributesDefinition {

    public static final String OPEN_ID="openid";

    public static final String NICK_NAME="nickname";

    public static final String FIGURE_URL="figureurl";

    public static final String FIGURE_URL_1="figureurl_1";

    public static final String FIGURE_URL_2="figureurl_2";

    public static final String GENDER="gender";

    public static final String OAUTH_CLIENT="oauth_client";

    public QQAttributeDefinition() {
        addAttribute(OPEN_ID, Converters.stringConverter);
        addAttribute(NICK_NAME, Converters.stringConverter);
        addAttribute(FIGURE_URL, Converters.stringConverter);
        addAttribute(FIGURE_URL_1, Converters.stringConverter);
        addAttribute(FIGURE_URL_2, Converters.stringConverter);
        addAttribute(GENDER, Converters.stringConverter);
        addAttribute(OAUTH_CLIENT,Converters.stringConverter);
    }
}
