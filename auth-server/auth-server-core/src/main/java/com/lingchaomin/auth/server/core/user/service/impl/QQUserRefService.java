package com.lingchaomin.auth.server.core.user.service.impl;

import com.lingchaomin.auth.server.core.user.dao.QQUserRefDao;
import com.lingchaomin.auth.server.core.user.entity.QQUserRef;
import com.lingchaomin.auth.server.core.user.service.IQQUserRefService;
import com.yunbeitech.auth.common.dto.OperErrorCode;
import com.yunbeitech.auth.common.dto.OperateResultDto;
import com.yunbeitech.auth.common.handler.ReqResultFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午4:02
 * @description
 */
@Service
public class QQUserRefService implements IQQUserRefService {

    @Autowired
    private QQUserRefDao qqUserRefDao;

    /**
     * 根据openId查找
     */
    public QQUserRef findQQUserByOpenId(String openId) {
        return qqUserRefDao.findQQUserByOpenId(openId);
    }

    /**
     * 根据用户id查找
     */
    public QQUserRef findByUserId(Long userId) {
        return qqUserRefDao.findByUserId(userId);
    }

    /**
     * 绑定qq
     */
    public OperateResultDto bindQQ(QQUserRef qqUserRef) {
        QQUserRef qqUserRef1=(QQUserRef)qqUserRef;
        long ret= qqUserRefDao.insert(qqUserRef1);

        if(ret>0){
            return ReqResultFormatter.formatOperSuccessDto(ret);
        }
        return ReqResultFormatter.formatFailDto(OperErrorCode.FAIL);
    }
}
