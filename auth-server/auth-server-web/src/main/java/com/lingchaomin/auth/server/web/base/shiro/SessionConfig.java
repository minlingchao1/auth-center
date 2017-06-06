package com.lingchaomin.auth.server.web.base.shiro;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.lingchaomin.auth.core.dto.UserDto;


/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/4/1 下午5:28
 * @description
 */
public class SessionConfig {

    public static Cache<String,UserDto> USER_CACHE= CacheBuilder.newBuilder().build();
}
