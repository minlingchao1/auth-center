package com.lingchaomin.auth.server.core.user.dao;

import com.lingchaomin.auth.core.dto.QQUserDto;
import com.lingchaomin.auth.server.core.base.dao.IDao;
import com.lingchaomin.auth.server.core.user.dto.UserSelectDto;
import com.lingchaomin.auth.server.core.user.entity.User;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 上午9:34
 * @description 用户相关
 */
@Repository
public interface UserDao extends IDao<User> {

    /**
     * 根据用户id和状态查找
     * @param userId
     * @param status
     * @return
     */
    User findByUserIdAndStatus(@Param("userId") Long userId, @Param("status") Boolean status);

    /**
     * 根据手机号查找
     * @param mobile
     * @return
     */
    User findByMobile(@Param("mobile") String mobile);

    /**
     * 查找所有
     * @return
     */
    List<User> selectAll(@Param("searchValue") String searchValue);

    /**
     * 更新状态
     * @param id
     * @param locked
     * @return
     */
    long updateStatus(@Param("id") Long id, @Param("status") Boolean locked);

    /**
     * 更新
     * @param user
     * @return
     */
    long update(User user);

    /**
     * 统计
     * @param searchValue
     * @return
     */
    long count(@Param("searchValue") String searchValue);

    /**
     * 动态更新
     * @param id
     * @param name
     * @param value
     * @return
     */
    long updateByParams(@Param("id") Long id, @Param("name") String name, @Param("value") String value);

    /**
     * 获取用户
     * @return
     */
    List<UserSelectDto> select4Auth();

    /**
     * 获取客服部门人员
     * @param appId
     * @param roleIds
     * @return
     */
    List<QQUserDto> selectCustomerService(@Param("appId") Long appId, @Param("roleIds") List<Long> roleIds);
}
