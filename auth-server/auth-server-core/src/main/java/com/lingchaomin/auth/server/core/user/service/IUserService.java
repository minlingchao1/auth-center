package com.lingchaomin.auth.server.core.user.service;


import com.lingchaomin.auth.server.core.user.dto.UserSelectDto;
import com.lingchaomin.auth.server.core.user.entity.User;
import com.yunbeitech.auth.common.dto.OperateResultDto;

import java.util.List;

/**
 * @author minlingchao
 * @version 1.0
 * @date 2017/2/20 下午4:06
 * @description
 */
public interface IUserService {

    /**
     * 根据用户id和status查找
     * @return
     */
    User findByUserIdAndStatus(Long userId, Boolean status);

    /**
     *
     * @param mobile
     * @return
     */
    User findByMobile(String mobile);


    /**
     * 列表
     * @return
     */
    List<User> list(String searchValue);

    /**
     * 统计数量
     * @return
     */
    OperateResultDto count(String searchValue);

    /**
     * 删除
     * @param id
     * @return
     */
    OperateResultDto remove(Long id);


    /**
     * 修改
     * @return
     */
    OperateResultDto modify(Long id, String name, String value);

    /**
     * 根据id查找
     * @param id
     * @return
     */
    User findById(Long id);

    /**
     * 注册
     * @param mobile
     * @param nick
     * @param descr
     * @param password
     * @param openId
     * @return
     */
    OperateResultDto register(String mobile, String nick, String descr, String password, String openId);

    /**
     * 绑定qq
     * @param openId
     * @param mobile
     * @return
     */
    OperateResultDto bindQQ(String openId, String mobile);

    /**
     * 检测用户
     * @param mobile
     * @return
     */
    OperateResultDto check(String mobile);

    /**
     * 锁定用户
     * @param id
     * @return
     */
    OperateResultDto modifyLock(Long id);

    /**
     * 解锁用户
     * @param id
     * @return
     */
    OperateResultDto modifyUnLock(Long id);

    /**
     * 获取用户
     * @return
     */
    List<UserSelectDto> select4Auth();

    /**
     * 获取角色
     * @param appKey
     * @param username
     * @return
     */
    List<String> getRole4QQLogin(String appKey, String username);

    /**
     * 获取权限
     * @param appKey
     * @param username
     * @return
     */
    List<String> getPermission4QQLogin(String appKey, String username);

    /**
     * 检测用户是否合法
     * @param s
     * @param s1
     */
    User checkUserIsLegal(String s, String s1);
}
