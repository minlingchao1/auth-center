package com.lingchaomin.auth.server.core.user.service.impl;


import com.lingchaomin.auth.server.core.user.dao.QQUserDao;
import com.lingchaomin.auth.server.core.user.entity.QQUser;
import com.lingchaomin.auth.server.core.user.service.IQQUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/23 上午10:37
 * @description
 */
@Service
public class QQUserService implements IQQUserService {

    @Autowired
    private QQUserDao qqUserDao;
    /**
     * 根据userId查找
     */
    public QQUser getByUserId(Long userId) {
        return qqUserDao.findByUserId(userId);
    }
}
