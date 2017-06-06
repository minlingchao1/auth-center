package com.lingchaomin.auth.server.core.user.service;


import com.lingchaomin.auth.server.common.dto.OperateResultDto;
import com.lingchaomin.auth.server.core.user.entity.QQUserRef;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午3:57
 * @description qq user ref
 */
public interface IQQUserRefService {

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

    /**
     * 绑定qq
     * @param qqUserRef
     * @return
     */
    OperateResultDto bindQQ(QQUserRef qqUserRef);
}
