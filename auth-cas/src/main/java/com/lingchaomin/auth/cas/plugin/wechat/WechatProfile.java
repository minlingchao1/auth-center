package com.lingchaomin.auth.cas.plugin.wechat;

import org.pac4j.core.profile.AttributesDefinition;
import org.pac4j.oauth.profile.OAuth20Profile;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/13 下午2:30
 * @description 添加返回用户信息
 */
public class WechatProfile extends OAuth20Profile{

    @Override
    protected AttributesDefinition getAttributesDefinition() {
        return new WechatAttributeDefinition();
    }
}
