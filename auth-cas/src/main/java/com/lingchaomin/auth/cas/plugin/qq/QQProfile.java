package com.lingchaomin.auth.cas.plugin.qq;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.OAuth20Profile;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午5:42
 * @description 添加返回用户信息
 */
public class QQProfile extends OAuth20Profile{

    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return new QQAttributeDefinition();
    }
}
