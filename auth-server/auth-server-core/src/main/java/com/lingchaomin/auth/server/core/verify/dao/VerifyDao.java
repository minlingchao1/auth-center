package com.lingchaomin.auth.server.core.verify.dao;


import com.lingchaomin.auth.server.core.base.dao.IDao;
import com.lingchaomin.auth.server.core.verify.entity.VerifyCode;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/3/13 下午1:40
 * @description 验证码相关
 */
@Repository
public interface VerifyDao extends IDao<VerifyCode> {

    /**
     * 根据手机号查找
     * @param mobile
     * @return
     */
    long selectByMobileAndSendTs(@Param("mobile") String mobile, @Param("beginTs") Long beginTs, @Param("endTs") Long endTs);

    /**
     * 根据手机号查找
     * @param mobile
     * @return
     */
    String selectByMobile(@Param("mobile") String mobile);
}
