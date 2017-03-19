package com.lingchaomin.auth.server.core.user.service;


import com.lingchaomin.auth.server.core.user.entity.QQUser;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午10:37
 * @description 用户qq关联信息
 */
public interface IQQUserService {
    /**
     * 根据userId查找
     * @param userId
     * @return
     */
    QQUser getByUserId(Long userId);
}
