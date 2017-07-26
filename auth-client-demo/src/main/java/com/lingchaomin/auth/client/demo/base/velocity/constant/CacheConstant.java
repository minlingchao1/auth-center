package com.lingchaomin.auth.client.demo.base.velocity.constant;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import com.lingchaomin.auth.core.dto.UserDto;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/21 下午8:03
 * @description 缓存配置
 */
public class CacheConstant {

    public static Cache<String,String> HTML_CACHE= CacheBuilder.newBuilder().build();

    public static Cache<String,UserDto> USER_CACHE=CacheBuilder.newBuilder().build();

}
