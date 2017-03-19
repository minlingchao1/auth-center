package com.lingchaomin.auth.server.core.user.dao;



import com.lingchaomin.auth.server.core.base.dao.IDao;
import com.lingchaomin.auth.server.core.user.entity.QQUserRef;

import org.springframework.stereotype.Repository;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 上午9:37
 * @description qq用户相关
 */
@Repository
public interface QQUserRefDao extends IDao<QQUserRef> {

    /**
     * 根据openId查找
     * @param openId
     * @return
     */
    QQUserRef findQQUserByOpenId(String openId);

    /**
     * 根据用户id查找
     * @param userId
     * @return
     */
    QQUserRef findByUserId(Long userId);
}
