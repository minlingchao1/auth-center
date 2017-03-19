package com.lingchaomin.auth.server.core.user.dao;


import com.lingchaomin.auth.server.core.base.dao.IDao;
import com.lingchaomin.auth.server.core.user.entity.QQUser;

import org.springframework.stereotype.Repository;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 上午9:36
 * @description  qq用户相关
 */
@Repository
public interface QQUserDao extends IDao<QQUser> {

    /**
     * 根据userId查找
     * @param userId
     * @return
     */
    QQUser findByUserId(Long userId);
}
